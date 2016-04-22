package support;

import java.util.UUID;

/**
 * ID生成器
 */
public class IdGenerator {

    private IdGenerator() {
    }

    /**
     * 生成不带分隔符的32位UUID(大写)
     */
    public static String gen32Uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}
