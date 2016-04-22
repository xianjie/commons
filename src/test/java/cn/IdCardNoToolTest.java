package cn;

import enums.Sex;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

public class IdCardNoToolTest {

    private final String MAIN_F_18 = "210502198412020944"; // 大陆，辽宁，女, 18位
    private final String MAIN_F_15 = "210502841202094"; // 大陆，辽宁，女, 15位

    private final String HK = "R6728757";

    private final String MACAU = "5215299(8)";

    private final String TW_M = "J109830254";
    private final String TW_F = "T240298298";

    @Test
    public void conver15CardTo18() {
        String idCard18 = IdCardNoTool.convert15To18(MAIN_F_15);
        assertEquals(MAIN_F_18, idCard18);

        assertNull(IdCardNoTool.convert15To18(null));
        assertNull(IdCardNoTool.convert15To18(""));

        assertNull(IdCardNoTool.convert15To18(MAIN_F_18));
    }

    @Test
    public void isIdCardNo() {
        assertTrue(IdCardNoTool.isIdCardNo(MAIN_F_15));
        assertTrue(IdCardNoTool.isIdCardNo(MAIN_F_18));
        assertTrue(IdCardNoTool.isIdCardNo(HK));
        assertTrue(IdCardNoTool.isIdCardNo(MACAU));
        assertTrue(IdCardNoTool.isIdCardNo(TW_M));
        assertTrue(IdCardNoTool.isIdCardNo(TW_F));

        assertFalse(IdCardNoTool.isIdCardNo(null));
        assertFalse(IdCardNoTool.isIdCardNo(""));
        assertFalse(IdCardNoTool.isIdCardNo("1234556"));
    }

    @Test
    public void isIdCardNo18() {
        assertTrue(IdCardNoTool.isIdCardNo18(MAIN_F_18));

        assertFalse(IdCardNoTool.isIdCardNo18(MAIN_F_15));
        assertFalse(IdCardNoTool.isIdCardNo18(HK));
        assertFalse(IdCardNoTool.isIdCardNo18(MACAU));
        assertFalse(IdCardNoTool.isIdCardNo18(TW_M));
        assertFalse(IdCardNoTool.isIdCardNo18(TW_F));
        assertFalse(IdCardNoTool.isIdCardNo18(null));
        assertFalse(IdCardNoTool.isIdCardNo18(""));
        assertFalse(IdCardNoTool.isIdCardNo18("1234556"));
    }

    @Test
    public void isIdCardNo15() {
        assertTrue(IdCardNoTool.isIdCardNo15(MAIN_F_15));

        assertFalse(IdCardNoTool.isIdCardNo15(MAIN_F_18));
        assertFalse(IdCardNoTool.isIdCardNo15(HK));
        assertFalse(IdCardNoTool.isIdCardNo15(MACAU));
        assertFalse(IdCardNoTool.isIdCardNo15(TW_M));
        assertFalse(IdCardNoTool.isIdCardNo15(TW_F));
        assertFalse(IdCardNoTool.isIdCardNo15(null));
        assertFalse(IdCardNoTool.isIdCardNo15(""));
        assertFalse(IdCardNoTool.isIdCardNo15("1234556"));
    }

    @Test
    public void isTwIdCardNo() {
        assertTrue(IdCardNoTool.isTwIdCardNo(TW_M));
        assertTrue(IdCardNoTool.isTwIdCardNo(TW_F));

        assertFalse(IdCardNoTool.isTwIdCardNo(MAIN_F_15));
        assertFalse(IdCardNoTool.isTwIdCardNo(MAIN_F_18));
        assertFalse(IdCardNoTool.isTwIdCardNo(HK));
        assertFalse(IdCardNoTool.isTwIdCardNo(MACAU));
        assertFalse(IdCardNoTool.isTwIdCardNo(null));
        assertFalse(IdCardNoTool.isTwIdCardNo(""));
        assertFalse(IdCardNoTool.isTwIdCardNo("1234556"));
    }

    @Test
    public void isHkIdCardNo() {
        assertTrue(IdCardNoTool.isHkIdCardNo(HK));

        assertFalse(IdCardNoTool.isHkIdCardNo(MACAU));
        assertFalse(IdCardNoTool.isHkIdCardNo(TW_M));
        assertFalse(IdCardNoTool.isHkIdCardNo(TW_F));
        assertFalse(IdCardNoTool.isHkIdCardNo(MAIN_F_15));
        assertFalse(IdCardNoTool.isHkIdCardNo(MAIN_F_18));
        assertFalse(IdCardNoTool.isHkIdCardNo(null));
        assertFalse(IdCardNoTool.isHkIdCardNo(""));
        assertFalse(IdCardNoTool.isHkIdCardNo("1234556"));
    }

    @Test
    public void isMacauIdCardNo() {
        assertTrue(IdCardNoTool.isMacauIdCardNo(MACAU));

        assertFalse(IdCardNoTool.isMacauIdCardNo(HK));
        assertFalse(IdCardNoTool.isMacauIdCardNo(TW_M));
        assertFalse(IdCardNoTool.isMacauIdCardNo(TW_F));
        assertFalse(IdCardNoTool.isMacauIdCardNo(MAIN_F_15));
        assertFalse(IdCardNoTool.isMacauIdCardNo(MAIN_F_18));
        assertFalse(IdCardNoTool.isMacauIdCardNo(null));
        assertFalse(IdCardNoTool.isMacauIdCardNo(""));
        assertFalse(IdCardNoTool.isMacauIdCardNo("1234556"));
    }

    @Test
    public void getBirthday() {
        assertEquals("19841202", IdCardNoTool.getBirthday(MAIN_F_15));
        assertEquals("19841202", IdCardNoTool.getBirthday(MAIN_F_18));

        assertNull(IdCardNoTool.getBirthday(HK));
        assertNull(IdCardNoTool.getBirthday(MACAU));
        assertNull(IdCardNoTool.getBirthday(TW_M));
        assertNull(IdCardNoTool.getBirthday(TW_F));
        assertNull(IdCardNoTool.getBirthday(null));
        assertNull(IdCardNoTool.getBirthday(""));
        assertNull(IdCardNoTool.getBirthday("1234556"));
    }

    @Test
    public void getSex() {
        assertEquals(Sex.FEMALE, IdCardNoTool.getSex(MAIN_F_15));
        assertEquals(Sex.FEMALE, IdCardNoTool.getSex(MAIN_F_18));

        assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex(HK));
        assertEquals(Sex.MALE, IdCardNoTool.getSex(TW_M));
        assertEquals(Sex.FEMALE, IdCardNoTool.getSex(TW_F));
        assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex(null));
        assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex(""));
        assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex("1234556"));
    }

    @Test
    public void getProvince() {
        assertEquals(Province.LIAO_NING, IdCardNoTool.getProvince(MAIN_F_15));
        assertEquals(Province.LIAO_NING, IdCardNoTool.getProvince(MAIN_F_18));

        assertEquals(Province.XIANG_GANG, IdCardNoTool.getProvince(HK));
        assertEquals(Province.AO_MEN, IdCardNoTool.getProvince(MACAU));
        assertEquals(Province.TAI_WAN, IdCardNoTool.getProvince(TW_M));
        assertEquals(Province.TAI_WAN, IdCardNoTool.getProvince(TW_F));
        assertEquals(null, IdCardNoTool.getProvince(null));
        assertEquals(null, IdCardNoTool.getProvince(""));
        assertEquals(null, IdCardNoTool.getProvince("1234556"));
    }

}

