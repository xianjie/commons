package query.sort;

import exception.SystemException;

import java.util.Locale;

public enum Direction {

    ASC, DESC;

    public static Direction fromString(String value) {
        try {
            return Direction.valueOf(value.toUpperCase(Locale.US));
        } catch (Exception e) {
            String msg = "非法排序值{0}！取值必须o 'desc' 或 'asc' (大小写不敏感)。";
            throw new SystemException(e, msg, value);
        }
    }

}
