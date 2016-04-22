package enums;

import org.apache.commons.io.IOCase;

/**
 * 大小写敏感性枚举
 */
public enum CaseSensitivity implements ICodeEnum {

    SENSITIVE("1", "敏感"),
    INSENSITIVE("2", "不敏感"),
    SYSTEM("3", "依赖于系统");

    private final String code;
    private final String desc;

    CaseSensitivity(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getTrans() {
        return desc;
    }

    public static CaseSensitivity enumOf(String code) {
        return EnumTool.enumOf(CaseSensitivity.class, code);
    }

    @Override
    public String toString() {
        return desc;
    }

    public static IOCase adaptApacheIoCase(CaseSensitivity joyCase) {
        if (joyCase == null) {
            joyCase = CaseSensitivity.SYSTEM;
        }
        IOCase ioCase = IOCase.SYSTEM;
        switch (joyCase) {
            case SENSITIVE:
                ioCase = IOCase.SENSITIVE;
                break;
            case INSENSITIVE:
                ioCase = IOCase.INSENSITIVE;
                break;
            case SYSTEM:
                ioCase = IOCase.SYSTEM;
                break;
        }
        return ioCase;
    }

}
