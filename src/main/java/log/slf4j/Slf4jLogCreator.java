package log.slf4j;

import log.Log;
import log.LogCreator;
import org.slf4j.LoggerFactory;

/**
 * slf4j日志记录器创建者
 */
public class Slf4jLogCreator implements LogCreator {

    public Log createLogger(Class<?> clazz) {
        return new Slf4jLog(LoggerFactory.getLogger(clazz));
    }

}
