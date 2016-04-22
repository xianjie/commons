package lang.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringToolTest {

    @Test
    public void testReplaceEach() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "壹");
        map.put("2", "贰");
        map.put("3", "叁");
        map.put(null, "*");
        map.put("", "*");
        assertEquals("壹贰叁.壹", StringTool.replaceEach("123.1", map));
    }

    @Test
    public void testToHexStr() {
        String str = "Kevice";
        assertEquals("4b6576696365", StringTool.toHexStr(str));
    }

    @Test
    public void testDecodeHexStr() {
        String hexStr = "4b6576696365";
        assertEquals("Kevice", StringTool.decodeHexStr(hexStr));
    }

    @Test
    public void testDivideAverage() {
        assertEquals(0, StringTool.divideAverage(null, 3).length);
        assertEquals(0, StringTool.divideAverage("", 3).length);
        assertEquals(0, StringTool.divideAverage("ererr", 0).length);
        assertEquals(0, StringTool.divideAverage("ererr", -3).length);
        assertEquals(0, StringTool.divideAverage(null, 3).length);
        assertEquals(0, StringTool.divideAverage(null, 3).length);

        String[] arr = StringTool.divideAverage("123456", 3);
        assertEquals(3, arr.length);
        assertEquals("12", arr[0]);
        assertEquals("34", arr[1]);
        assertEquals("56", arr[2]);

        arr = StringTool.divideAverage("1234567", 3);
        assertEquals(3, arr.length);
        assertEquals("123", arr[0]);
        assertEquals("456", arr[1]);
        assertEquals("7", arr[2]);
    }

    @Test
    public void testHumpToUnderscore() {
        assertEquals("", StringTool.humpToUnderscore(null));
        assertEquals("", StringTool.humpToUnderscore(""));
        assertEquals("HUMP_TO_UNDERLINE", StringTool.humpToUnderscore("humpToUnderline"));
    }

    @Test
    public void testUnderscoreToHump() {
        assertEquals("", StringTool.underscoreToHump(null));
        assertEquals("", StringTool.underscoreToHump(""));
        assertEquals("humpToUnderline", StringTool.underscoreToHump("HUMP_TO_UNDERLINE"));
        assertFalse("HumpToUnderline".equals(StringTool.underscoreToHump("HUMP_TO_UNDERLINE")));
    }


    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.StringUtils
    // ----------------------------------------------------------------------------

    // 判空
    // -----------------------------------------------------------------------

    @Test
    public void testIsEmpty() {
        assertTrue(StringTool.isEmpty(null));
        assertTrue(StringTool.isEmpty(""));
        assertFalse(StringTool.isEmpty("null"));
        assertFalse(StringTool.isEmpty(" "));
        assertFalse(StringTool.isEmpty("kevice"));
        assertFalse(StringTool.isEmpty(" kevice "));
    }

    @Test
    public void testIsNotEmpty() {
        assertFalse(StringTool.isNotEmpty(null));
        assertFalse(StringTool.isNotEmpty(""));
        assertTrue(StringTool.isNotEmpty("null"));
        assertTrue(StringTool.isNotEmpty(" "));
        assertTrue(StringTool.isNotEmpty("kevice"));
        assertTrue(StringTool.isNotEmpty(" kevice "));
    }

    @Test
    public void testIsBlank() {
        assertTrue(StringTool.isBlank(null));
        assertTrue(StringTool.isBlank(""));
        assertTrue(StringTool.isBlank(" "));
        assertTrue(StringTool.isBlank("\t\n\r\f"));
        assertFalse(StringTool.isBlank("null"));
        assertFalse(StringTool.isBlank("kevice"));
        assertFalse(StringTool.isBlank(" kevice "));
    }

    @Test
    public void testIsNotBlank() {
        assertFalse(StringTool.isNotBlank(null));
        assertFalse(StringTool.isNotBlank(""));
        assertFalse(StringTool.isNotBlank(" "));
        assertFalse(StringTool.isNotBlank("\t\n\r\f"));
        assertTrue(StringTool.isNotBlank("null"));
        assertTrue(StringTool.isNotBlank("kevice"));
        assertTrue(StringTool.isNotBlank(" kevice "));
    }

    // Trim
    // -----------------------------------------------------------------------

    @Test
    public void testTrim() {
        assertEquals(null, StringTool.trim(null));
        assertEquals("", StringTool.trim(""));
        assertEquals("", StringTool.trim(" "));
        assertEquals("", StringTool.trim("\b\t\n\r\f"));
        assertEquals("null", StringTool.trim("null"));
        assertEquals("kevice", StringTool.trim("kevice"));
        assertEquals("k e v i c e", StringTool.trim(" k e v i c e "));
    }

    @Test
    public void testTrimToNull() {
        assertEquals(null, StringTool.trimToNull(null));
        assertEquals(null, StringTool.trimToNull(""));
        assertEquals(null, StringTool.trimToNull(" "));
        assertEquals(null, StringTool.trimToNull("\b\t\n\r\f"));
        assertEquals("null", StringTool.trimToNull("null"));
        assertEquals("kevice", StringTool.trimToNull("kevice"));
        assertEquals("k e v i c e", StringTool.trimToNull(" k e v i c e "));
    }

    @Test
    public void testTrimToEmpty() {
        assertEquals("", StringTool.trimToEmpty(null));
        assertEquals("", StringTool.trimToEmpty(""));
        assertEquals("", StringTool.trimToEmpty(" "));
        assertEquals("", StringTool.trimToEmpty("\b\t\n\r\f"));
        assertEquals("null", StringTool.trimToEmpty("null"));
        assertEquals("kevice", StringTool.trimToEmpty("kevice"));
        assertEquals("k e v i c e", StringTool.trimToEmpty(" k e v i c e "));
    }

    // Stripping
    // -----------------------------------------------------------------------

    @Test
    public void testStrip() {
        assertEquals(null, StringTool.strip(null));
        assertEquals("", StringTool.strip(""));
        assertEquals("", StringTool.strip(" "));
        assertEquals("\b", StringTool.strip("\b\t\n\r\f")); // 注意和trim的差别
        assertEquals("null", StringTool.strip("null"));
        assertEquals("kevice", StringTool.strip("kevice"));
        assertEquals("k e v i c e", StringTool.strip(" k e v i c e "));
    }

    @Test
    public void testStripToNull() {
        assertEquals(null, StringTool.stripToNull(null));
        assertEquals(null, StringTool.stripToNull(""));
        assertEquals(null, StringTool.stripToNull(" "));
        assertEquals("\b", StringTool.stripToNull("\b\t\n\r\f"));
        assertEquals("null", StringTool.stripToNull("null"));
        assertEquals("kevice", StringTool.stripToNull("kevice"));
        assertEquals("k e v i c e", StringTool.stripToNull(" k e v i c e "));
    }

    @Test
    public void testStripToEmpty() {
        assertEquals("", StringTool.stripToEmpty(null));
        assertEquals("", StringTool.stripToEmpty(""));
        assertEquals("", StringTool.stripToEmpty(" "));
        assertEquals("\b", StringTool.stripToEmpty("\b\t\n\r\f"));
        assertEquals("null", StringTool.stripToEmpty("null"));
        assertEquals("kevice", StringTool.stripToEmpty("kevice"));
        assertEquals("k e v i c e", StringTool.stripToEmpty(" k e v i c e "));
    }

    @Test
    public void testStripByStr() {
        assertEquals(null, StringTool.strip(null, "*"));
        assertEquals("k", StringTool.strip(" k ", null));
        assertEquals("\b", StringTool.strip("\b\t\n\r\f", null));
        assertEquals("null", StringTool.strip("null", "*"));
        assertEquals("  abc", StringTool.strip("yxzxy  abcyx", "xyz"));
    }

    @Test
    public void testStripStartByStr() {
        assertEquals(null, StringTool.stripStart(null, "*"));
        assertEquals("k ", StringTool.stripStart(" k ", null));
        assertEquals("\b", StringTool.stripStart("\t\n\r\f\b", null));
        assertEquals("null", StringTool.stripStart("null", "*"));
        assertEquals("  abcyx", StringTool.stripStart("yxzxy  abcyx", "xyz"));
    }

    @Test
    public void testStripEndByStr() {
        assertEquals(null, StringTool.stripEnd(null, "*"));
        assertEquals(" k", StringTool.stripEnd(" k ", null));
        assertEquals("\b", StringTool.stripEnd("\b\t\n\r\f", null));
        assertEquals("null", StringTool.stripEnd("null", "*"));
        assertEquals("yxzxy  abc", StringTool.stripEnd("yxzxy  abcyx", "xyz"));
        assertEquals("12", StringTool.stripEnd("120.00", ".0"));
    }

    @Test
    public void testStripAll() {
        String[] strs = {null, "", " ", "\b\t\n\r\f", "null", "kevice", " k e v i c e "};

        String[] results = StringTool.stripAll(strs);

        assertEquals(null, results[0]);
        assertEquals("", results[1]);
        assertEquals("", results[2]);
        assertEquals("\b", results[3]);
        assertEquals("null", results[4]);
        assertEquals("kevice", results[5]);
        assertEquals("k e v i c e", results[6]);
    }

    // @Test
    // public void testStripAccents() {
    // assertEquals(null, StringUtils.stripAccents(null));
    // assertEquals("", StringUtils.stripAccents(""));
    // assertEquals("control", StringUtils.stripAccents("control"));
    // assertEquals("eclair", StringUtils.stripAccents("&eacute;clair"));
    // assertEquals("a", StringUtils.stripAccents("&agrave;"));
    // }

    // Equals
    // -----------------------------------------------------------------------

    @Test
    public void testEquals() {
        assertTrue(StringTool.equals(null, null));
        assertTrue(StringTool.equals("abc", "abc"));
        assertFalse(StringTool.equals(null, "abc"));
        assertFalse(StringTool.equals("abc", null));
        assertFalse(StringTool.equals("abc", "ABC"));
    }

    @Test
    public void testEqualsIgnoreCase() {
        assertTrue(StringTool.equalsIgnoreCase(null, null));
        assertTrue(StringTool.equalsIgnoreCase("abc", "abc"));
        assertTrue(StringTool.equalsIgnoreCase("abc", "ABC"));
        assertFalse(StringTool.equalsIgnoreCase(null, "abc"));
        assertFalse(StringTool.equalsIgnoreCase("abc", null));
    }

    // IndexOf
    // -----------------------------------------------------------------------

    @Test
    public void testIndexOf() {
        assertEquals(-1, StringTool.indexOf(null, '*'));
        assertEquals(-1, StringTool.indexOf("", '*'));
        assertEquals(0, StringTool.indexOf("aabaabaa", 'a'));
        assertEquals(2, StringTool.indexOf("aabaabaa", 'b'));
    }

    @Test
    public void testIndexOfFrom() {
        assertEquals(-1, StringTool.indexOf(null, '*', 1));
        assertEquals(-1, StringTool.indexOf("", '*', 1));
        assertEquals(2, StringTool.indexOf("aabaabaa", 'b', 0));
        assertEquals(5, StringTool.indexOf("aabaabaa", 'b', 3));
        assertEquals(-1, StringTool.indexOf("aabaabaa", 'b', 9));
        assertEquals(2, StringTool.indexOf("aabaabaa", 'b', -1));
    }

    @Test
    public void testIndexOfString() {
        assertEquals(-1, StringTool.indexOf(null, "*"));
        assertEquals(-1, StringTool.indexOf("*", null));
        assertEquals(-1, StringTool.indexOf("", "*"));
        assertEquals(0, StringTool.indexOf("", ""));
        assertEquals(0, StringTool.indexOf("aabaabaa", "a"));
        assertEquals(2, StringTool.indexOf("aabaabaa", "b"));
        assertEquals(1, StringTool.indexOf("aabaabaa", "ab"));
        assertEquals(0, StringTool.indexOf("aabaabaa", ""));
    }

    @Test
    public void testIndexOfStringFrom() {
        assertEquals(-1, StringTool.indexOf(null, "*", 1));
        assertEquals(-1, StringTool.indexOf("*", null, 1));
        assertEquals(-1, StringTool.indexOf("", "*", 2));
        assertEquals(0, StringTool.indexOf("", "", 0));
        assertEquals(1, StringTool.indexOf("aabaabaa", "a", 1));
        assertEquals(5, StringTool.indexOf("aabaabaa", "b", 4));
        assertEquals(1, StringTool.indexOf("aabaabaa", "ab", 0));
        assertEquals(0, StringTool.indexOf("aabaabaa", "", -1));
    }

    @Test
    public void testOrdinalIndexOf() {
        assertEquals(-1, StringTool.ordinalIndexOf(null, "*", 1));
        assertEquals(-1, StringTool.ordinalIndexOf("*", null, 1));
        assertEquals(-1, StringTool.ordinalIndexOf("", "*", 2));
        assertEquals(0, StringTool.ordinalIndexOf("", "", 2));
        assertEquals(0, StringTool.ordinalIndexOf("aabaabaa", "a", 1));
        assertEquals(5, StringTool.ordinalIndexOf("aabaabaa", "b", 2));
        assertEquals(4, StringTool.ordinalIndexOf("aabaabaa", "ab", 2));
        assertEquals(0, StringTool.ordinalIndexOf("aabaabaa", "", 2));
    }

    @Test
    public void testIndexOfIgnoreCase() {
        assertEquals(-1, StringTool.indexOfIgnoreCase(null, "*"));
        assertEquals(-1, StringTool.indexOfIgnoreCase("", "*"));
        assertEquals(0, StringTool.indexOfIgnoreCase("", ""));
        assertEquals(0, StringTool.indexOfIgnoreCase("aabaabaa", "a"));
        assertEquals(2, StringTool.indexOfIgnoreCase("aabaaBaa", "B"));
        assertEquals(1, StringTool.indexOfIgnoreCase("aabaAbaa", "aB"));
    }

    @Test
    public void testIndexOfIgnoreCaseFrom() {
        assertEquals(-1, StringTool.indexOfIgnoreCase(null, "*", 1));
        assertEquals(-1, StringTool.indexOfIgnoreCase("*", null, 1));
        assertEquals(-1, StringTool.indexOfIgnoreCase("", "*", 2));
        assertEquals(0, StringTool.indexOfIgnoreCase("", "", 0));
        assertEquals(1, StringTool.indexOfIgnoreCase("aabaabaa", "A", 1));
        assertEquals(5, StringTool.indexOfIgnoreCase("aaBaaBaa", "B", 4));
        assertEquals(1, StringTool.indexOfIgnoreCase("aabaabaa", "Ab", 0));
        assertEquals(0, StringTool.indexOfIgnoreCase("aabaabaa", "", -1));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(-1, StringTool.lastIndexOf(null, '*'));
        assertEquals(-1, StringTool.lastIndexOf("", '*'));
        assertEquals(7, StringTool.lastIndexOf("aabaabaa", 'a'));
        assertEquals(5, StringTool.lastIndexOf("aabaabaa", 'b'));
    }

    @Test
    public void testLastIndexOfFrom() {
        assertEquals(-1, StringTool.lastIndexOf(null, '*', 0));
        assertEquals(-1, StringTool.lastIndexOf("", '*', 0));
        assertEquals(5, StringTool.lastIndexOf("aabaabaa", 'b', 8));
        assertEquals(2, StringTool.lastIndexOf("aabaabaa", 'b', 4));
        assertEquals(-1, StringTool.lastIndexOf("aabaabaa", 'b', 0));
        assertEquals(5, StringTool.lastIndexOf("aabaabaa", 'b', 9));
        assertEquals(-1, StringTool.lastIndexOf("aabaabaa", 'b', -1));
        assertEquals(0, StringTool.lastIndexOf("aabaabaa", 'a', 0));
    }

    @Test
    public void testLastIndexOfString() {
        assertEquals(-1, StringTool.lastIndexOf(null, "*"));
        assertEquals(-1, StringTool.lastIndexOf("*", null));
        assertEquals(0, StringTool.lastIndexOf("", ""));
        assertEquals(7, StringTool.lastIndexOf("aabaabaa", "a"));
        assertEquals(5, StringTool.lastIndexOf("aabaabaa", "b"));
        assertEquals(4, StringTool.lastIndexOf("aabaabaa", "ab"));
        assertEquals(8, StringTool.lastIndexOf("aabaabaa", ""));
    }

    @Test
    public void testLastOrdinalIndexOf() {
        assertEquals(-1, StringTool.lastOrdinalIndexOf(null, "*", 8));
        assertEquals(-1, StringTool.lastOrdinalIndexOf("*", null, 8));
        assertEquals(0, StringTool.lastOrdinalIndexOf("", "", 8));
        assertEquals(7, StringTool.lastOrdinalIndexOf("aabaabaa", "a", 1));
        assertEquals(6, StringTool.lastOrdinalIndexOf("aabaabaa", "a", 2));
        assertEquals(5, StringTool.lastOrdinalIndexOf("aabaabaa", "b", 1));
        assertEquals(2, StringTool.lastOrdinalIndexOf("aabaabaa", "b", 2));
        assertEquals(4, StringTool.lastOrdinalIndexOf("aabaabaa", "ab", 1));
        assertEquals(1, StringTool.lastOrdinalIndexOf("aabaabaa", "ab", 2));
        assertEquals(8, StringTool.lastOrdinalIndexOf("aabaabaa", "", 1));
        assertEquals(8, StringTool.lastOrdinalIndexOf("aabaabaa", "", 2));
    }

    @Test
    public void testLastIndexOfStringFrom() {
        assertEquals(-1, StringTool.lastIndexOf(null, "*", 8));
        assertEquals(-1, StringTool.lastIndexOf("*", null, 8));
        assertEquals(7, StringTool.lastIndexOf("aabaabaa", "a", 8));
        assertEquals(5, StringTool.lastIndexOf("aabaabaa", "b", 8));
        assertEquals(4, StringTool.lastIndexOf("aabaabaa", "ab", 8));
        assertEquals(5, StringTool.lastIndexOf("aabaabaa", "b", 9));
        assertEquals(-1, StringTool.lastIndexOf("aabaabaa", "b", -1));
        assertEquals(0, StringTool.lastIndexOf("aabaabaa", "a", 0));
        assertEquals(-1, StringTool.lastIndexOf("aabaabaa", "b", 0));
    }

    @Test
    public void testlastIndexOfIgnoreCase() {
        assertEquals(-1, StringTool.lastIndexOfIgnoreCase(null, "*"));
        assertEquals(-1, StringTool.lastIndexOfIgnoreCase("*", null));
        assertEquals(7, StringTool.lastIndexOfIgnoreCase("aabaabaa", "A"));
        assertEquals(5, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B"));
        assertEquals(4, StringTool.lastIndexOfIgnoreCase("aabaabaa", "AB"));
    }

    @Test
    public void testLastIndexOfIgnoreCase() {
        assertEquals(-1, StringTool.lastIndexOfIgnoreCase(null, "*", 8));
        assertEquals(-1, StringTool.lastIndexOfIgnoreCase("*", null, 8));
        assertEquals(7, StringTool.lastIndexOfIgnoreCase("aabaabaa", "A", 8));
        assertEquals(5, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", 8));
        assertEquals(4, StringTool.lastIndexOfIgnoreCase("aabaabaa", "AB", 8));
        assertEquals(5, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", 9));
        assertEquals(-1, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", -1));
        assertEquals(0, StringTool.lastIndexOfIgnoreCase("aabaabaa", "A", 0));
        assertEquals(-1, StringTool.lastIndexOfIgnoreCase("aabaabaa", "B", 0));
    }

    // Contains
    // -----------------------------------------------------------------------

    @Test
    public void testContains() {
        assertFalse(StringTool.contains(null, '*'));
        assertFalse(StringTool.contains("", '*'));
        assertTrue(StringTool.contains("abc", 'a'));
        assertFalse(StringTool.contains("abc", 'z'));
    }

}
