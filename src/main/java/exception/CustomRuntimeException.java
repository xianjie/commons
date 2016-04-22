package exception;

import log.Log;
import log.LogFactory;

import java.text.MessageFormat;

/**
 * 自定义的运行时异常
 */
public class CustomRuntimeException extends RuntimeException {

    protected String detailMessage; // 详细信息
    protected static final Log logger = LogFactory.getLog(CustomRuntimeException.class);

    public CustomRuntimeException(String message, Object... args) {
        fillInStackTrace();
        handleMessageWithoutLog(message, args);
        logger.error(detailMessage);
    }

    public CustomRuntimeException(Throwable ex) {
        this(ex, ex.getMessage());
    }

    public CustomRuntimeException(Throwable cause, String message, Object... args) {
        fillInStackTrace();
        handleMessageWithoutLog(message, args);
        logger.error(cause, detailMessage);
    }

    public CustomRuntimeException(Throwable cause, boolean log, String message, Object... args) {
        fillInStackTrace();
        handleMessageWithoutLog(message, args);
        if (log) {
            logger.error(cause, detailMessage);
        }
    }

    private void handleMessageWithoutLog(String pattern, Object... args) {
        detailMessage = MessageFormat.format(pattern, args);
    }

    @Override
    public String getMessage() {
        return detailMessage;
    }
}
