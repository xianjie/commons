package io;

import java.net.URL;

/**
 * 路径工具类
 */
public class PathTool {

    private PathTool() {
    }

    /**
     * 获取类路径
     *
     * @return 类路径
     */
    public static String getClassesPath() {
        URL url = PathTool.class.getProtectionDomain().getCodeSource().getLocation();
        String path = url.toString();
        int index = path.indexOf("classes");
        if (index > 0) {
            return url.getPath();
        } else {
            return FilenameTool.normalize(url.getPath() + "/..");
        }
    }

    /**
     * 获取web工程的WEB-INF目录的路径,
     * 该实现假设WEB-INF目录是类路径的父目录，如果不是，请不要使用该方法
     *
     * @return web工程的WEB-INF目录的路径,
     */
    public static String getWebInfPath() {
        String path = getClassesPath();
        path += "..";
        return FilenameTool.normalize(path);
    }

    /**
     * 获取web工程的根目录的路径,
     * 该实现假设web工程根目录是WEB-INF目录的父目录，且WEB-INF目录是类路径的父目录，
     * 如果不是，请不要使用该方法
     *
     * @return web工程根目录的路径,
     */
    public static String getWebRootPath() {
        String path = getWebInfPath();
        path += "..";
        return FilenameTool.normalize(path);
    }

}