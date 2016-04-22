package log;

/**
 * 日志记录器
 */
public interface Log {

    /**
     * 记录调试信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args    参数可变数组
     */
    void debug(String message, Object... args);

    /**
     * 记录调试信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param   参数对象
     */
    void debug(String message, ILogParam param);

    /**
     * 记录提示信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args    参数可变数组
     */
    void info(String message, Object... args);

    /**
     * 记录提示信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param   参数对象
     */
    void info(String message, ILogParam param);

    /**
     * 记录警告信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args    参数可变数组
     */
    void warn(String message, Object... args);

    /**
     * 记录警告信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param   参数对象
     */
    void warn(String message, ILogParam param);

    /**
     * 记录错误信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args    参数可变数组
     */
    void error(String message, Object... args);

    /**
     * 记录错误信息
     *
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param   参数对象
     */
    void error(String message, ILogParam param);

    /**
     * 记录错误信息
     *
     * @param e
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args    参数可变数组
     */
    void error(Throwable e, String message, Object... args);

    /**
     * 记录错误信息
     *
     * @param e 异常对象
     */
    void error(Throwable e);

    /**
     * 是否开启调试级别
     *
     * @return 是否开启调试级别
     */
    boolean isDebugEnabled();

    /**
     * 是否开提示级别
     *
     * @return 是否开提示级别
     */
    boolean isInfoEnabled();

    /**
     * 是否开启警告级别
     *
     * @return 是否开启警告级别
     */
    boolean isWarnEnabled();

    /**
     * 是否开启错误级别
     *
     * @return 是否开启错误级别
     */
    boolean isErrorEnabled();
}
