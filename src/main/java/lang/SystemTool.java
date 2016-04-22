package lang;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * 系统工具类
 */
public class SystemTool {

    private SystemTool() {
    }

    // ----------------------------------------------------------------------------
    // 封装SystemUtils
    // ----------------------------------------------------------------------------

    /**
     * 获取java home目录, 并以{@code File}返回
     *
     * @return 目录
     * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
     * @see System#getProperty(String)
     */
    public static File getJavaHome() {
        return SystemUtils.getJavaHome();
    }

    /**
     * 获取IO临时目录, 并以{@code File}返回
     *
     * @return 目录
     * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
     * @see System#getProperty(String)
     */
    public static File getJavaIoTmpDir() {
        return SystemUtils.getJavaIoTmpDir();
    }

    // -----------------------------------------------------------------------

    /**
     * 获取用户目录, 并以{@code File}返回
     *
     * @return 目录
     * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
     * @see System#getProperty(String)
     */
    public static File getUserDir() {
        return SystemUtils.getUserDir();
    }

    /**
     * 获取用户home目录, 并以{@code File}返回
     *
     * @return 目录
     * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
     * @see System#getProperty(String)
     */
    public static File getUserHome() {
        return SystemUtils.getUserHome();
    }

    /**
     * 检测 {@link # JAVA_AWT_HEADLESS} 値是否为 {@code true}.
     *
     * @return {@code true} 如果 {@code JAVA_AWT_HEADLESS} 为 {@code "true"}, 否则返回 {@code false}.
     */
    public static boolean isJavaAwtHeadless() {
        return SystemUtils.isJavaAwtHeadless();
    }

    // ----------------------------------------------------------------------------
    // 封装SystemUtils
    // ----------------------------------------------------------------------------

}
