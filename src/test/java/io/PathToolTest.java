package io;

import org.junit.Test;

public class PathToolTest {

    @Test
    public void demo() {
        System.out.println(PathTool.getClassesPath());
        System.out.println(PathTool.getWebInfPath());
        System.out.println(PathTool.getWebRootPath());
    }

}
