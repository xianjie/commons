package enums;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class EnumToolTest {

    private final Class<TimeUnit> enumClass = TimeUnit.class;
    private final String enumClassStr = TimeUnit.class.getName();

    @Test
    public void enumOf() {
        String code = "9";
        assertEquals(TimeUnit.MICROSECOND, EnumTool.enumOf(enumClass, code));

        assertNull(EnumTool.enumOf(YesNot.class, code));

        code = "would not find";
        assertNull(EnumTool.enumOf(enumClass, code));

        assertNull(EnumTool.enumOf(enumClass, null));

        try {
            assertNull(EnumTool.enumOf((Class<TimeUnit>) null, code));
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void enumOfStr() {
        String code = "1";
        assertEquals(TimeUnit.YEAR, EnumTool.enumOf(enumClassStr, code));

        try {
            EnumTool.enumOf("err.class", code);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        code = "would not find";
        assertNull(EnumTool.enumOf(enumClassStr, code));

        assertNull(EnumTool.enumOf(YesNot.class.getName(), code));

        assertNull(EnumTool.enumOf(enumClassStr, null));

        try {
            EnumTool.enumOf((String) null, code);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getCodeMap() {
        Map<String, String> codeMap = EnumTool.getCodeMap(enumClass);
        assertTrue(codeMap.size() >= 9);
        assertEquals(TimeUnit.YEAR.getTrans(), codeMap.get("1"));
        assertEquals(TimeUnit.MICROSECOND.getTrans(), codeMap.get("9"));

        try {
            EnumTool.getCodeMap((Class<TimeUnit>) null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getCodeMapStr() {
        Map<String, String> codeMap = EnumTool.getCodeMap(enumClassStr);
//		assertTrue(codeMap.size() >= 9);
        assertEquals(TimeUnit.YEAR.getTrans(), codeMap.get("1"));
        assertEquals(TimeUnit.MICROSECOND.getTrans(), codeMap.get("9"));

        try {
            EnumTool.getCodeMap((String) null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            EnumTool.getCodeMap("");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            EnumTool.getCodeMap("err.class");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            EnumTool.getCodeMap(getClass().getName());
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getCodeEnumClass() {
        Class<? extends ICodeEnum> codeEnumClass = EnumTool.getCodeEnumClass(enumClassStr);
        assertTrue(codeEnumClass == enumClass);

        try {
            EnumTool.getCodeEnumClass(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            EnumTool.getCodeEnumClass("");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            EnumTool.getCodeEnumClass("err.class");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            EnumTool.getCodeEnumClass(getClass().getName());
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

}
