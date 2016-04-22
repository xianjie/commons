package lang;

import log.Log;
import log.LogFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程相关工具类
 */
public class ThreadTool {

    protected static final Log logger = LogFactory.getLog(ThreadTool.class);

    private ThreadTool() {
    }

    /**
     * 让当前线程休眠指定的毫秒数, 并忽略InterruptedException.
     *
     * @param millis 休眠的毫秒数
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    /**
     * 让当前线程休眠指定的时间, 并忽略InterruptedException.
     *
     * @param duration 休眠的时间值
     * @param unit     休眠的时间单位
     */
    public static void sleep(long duration, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(duration));
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    /**
     * 按照ExecutorService JavaDoc示例代码编写的Graceful Shutdown方法. 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数. 如果仍超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     *
     * @param pool               线程池
     * @param shutdownTimeout    关闭超时时间
     * @param shutdownNowTimeout 现在关闭超时时间
     * @param timeUnit           时间单位
     */
    public static void gracefulShutdown(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout,
                                        TimeUnit timeUnit) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(shutdownTimeout, timeUnit)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(shutdownNowTimeout, timeUnit)) {
                    logger.warn("线程池未结束!");
                }
            }
        } catch (InterruptedException ie) {
            logger.error(ie);
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 直接调用shutdownNow的方法, 有timeout控制. 取消在workQueue中Pending的任务, 并中断所有阻塞函数.
     *
     * @param pool     线程池
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     */
    public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
        try {
            pool.shutdownNow();
            if (!pool.awaitTermination(timeout, timeUnit)) {
                logger.warn("线程池未结束!");
            }
        } catch (InterruptedException ie) {
            logger.error(ie);
            Thread.currentThread().interrupt();
        }
    }

}
