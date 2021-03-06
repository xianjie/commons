package enums;

import java.util.Map;

/**
 * 性别枚举
 */
public enum Sex implements ICodeEnum {

    UNKNOWN("0", "未知的性别"),
    MALE("1", "男"),
    FEMALE("2", "女"),
    UNSPECIFIED("9", "未说明的性别");

    public static final String CODE_TABLE_ID = "time_unit";

    private final String code;
    private String desc;

    Sex(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static void initTrans(Map<String, String> map) {
        Sex[] values = Sex.values();
        for (Sex sex : values) {
            sex.desc = map.get(sex.getCode());
        }
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTrans() {
        return desc;
    }

    public static Sex enumOf(String code) {
        return EnumTool.enumOf(Sex.class, code);
    }

    @Override
    public String toString() {
        return desc;
    }

}