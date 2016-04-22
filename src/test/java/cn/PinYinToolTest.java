package cn;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PinYinToolTest {

    private String cnStr;

    @Before
    public void setUp() throws Exception {
        cnStr = "JOY是一个易用、灵活的javaEE快速开发平台！它采用微内核、插件、分层的架构。";
    }

    @Test
    public void getPinYin() {
        String pinYin = PinYinTool.getPinYin(cnStr);

        String expected = "JOYshiyigeyiyong、linghuodejavaEEkuaisukaifapingtai！" +
                "tacaiyongweineihe、chajian、fencengdejiagou。";
        assertEquals(expected, pinYin);
    }

    @Test
    public void getPinYinHeadChars() {
        String pinYinHeadChars = PinYinTool.getPinYinHeadChars(cnStr);

        String expected = "JOYsygyy、lhdjavaEEkskfpt！tcywnh、cj、fcdjg。";
        assertEquals(expected, pinYinHeadChars);
    }

    @Test
    public void getCnASCII() {
    }

}
