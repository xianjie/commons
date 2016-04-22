package log;

/**
 * 日志记录器创建者
 */
public interface LogCreator {

    /**
     * 创建日志记录器
     *
     * @param clazz 类
     * @return 日志记录器
     */
    Log createLogger(Class<?> clazz);
}
