package net;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IpToolTest {

    @Test
    public void isValidIpv4() {
        assertTrue(IpTool.isValidIpv4("192.168.0.123"));
        assertTrue(IpTool.isValidIpv4("127.0.0.1"));
        assertTrue(IpTool.isValidIpv4("127.0.0.01"));
        assertTrue(IpTool.isValidIpv4("0.0.0.0"));
        assertTrue(IpTool.isValidIpv4("255.255.255.255"));
        assertFalse(IpTool.isValidIpv4("0.0.0.0.0"));
        assertFalse(IpTool.isValidIpv4("192.168.0.256"));
        assertFalse(IpTool.isValidIpv4(null));
        assertFalse(IpTool.isValidIpv4(""));
        assertFalse(IpTool.isValidIpv4("invalid ip"));
        assertFalse(IpTool.isValidIpv4("..."));
    }

    @Test
    public void ipv4StringToLong() {
        assertTrue(2130706433L == IpTool.ipv4StringToLong("127.0.0.1"));
        assertTrue(2130706433L == IpTool.ipv4StringToLong("127.0.0.01"));
        assertTrue(-1 == IpTool.ipv4StringToLong("192.168.0.256"));
        assertTrue(-1 == IpTool.ipv4StringToLong(null));
        assertTrue(-1 == IpTool.ipv4StringToLong(""));
        assertTrue(-1 == IpTool.ipv4StringToLong("invalid ip"));
        assertTrue(-1 == IpTool.ipv4StringToLong("..."));
    }

    @Test
    public void ipv4LongToString() {
        assertEquals("127.0.0.1", IpTool.ipv4LongToString(2130706433L));
        assertNull(IpTool.ipv4LongToString(-1));
        assertNull(IpTool.ipv4LongToString(99999999999L));
        assertEquals("0.0.0.0", IpTool.ipv4LongToString(0));
    }

    @Test
    public void getIpv4sBetween() {
        List<String> ipv4s = IpTool.getIpv4sBetween("127.0.0.1", "127.0.0.3");
        assertEquals(3, ipv4s.size());
        assertEquals("127.0.0.1", ipv4s.get(0));
        assertEquals("127.0.0.2", ipv4s.get(1));
        assertEquals("127.0.0.3", ipv4s.get(2));

        ipv4s = IpTool.getIpv4sBetween("127.0.0.3", "127.0.0.1");
        assertEquals(3, ipv4s.size());
        assertEquals("127.0.0.1", ipv4s.get(0));
        assertEquals("127.0.0.2", ipv4s.get(1));
        assertEquals("127.0.0.3", ipv4s.get(2));

        ipv4s = IpTool.getIpv4sBetween("127.0.0.0", null);
        assertEquals(0, ipv4s.size());

        ipv4s = IpTool.getIpv4sBetween("127.0.0.0", "");
        assertEquals(0, ipv4s.size());

        ipv4s = IpTool.getIpv4sBetween(null, "127.0.0.3");
        assertEquals(0, ipv4s.size());

        ipv4s = IpTool.getIpv4sBetween("", "127.0.0.3");
        assertEquals(0, ipv4s.size());

        ipv4s = IpTool.getIpv4sBetween("invalid ip", "127.0.0.3");
        assertEquals(0, ipv4s.size());

        ipv4s = IpTool.getIpv4sBetween("127.0.0.0", "127.0.0.0");
        assertEquals(1, ipv4s.size());
    }

    @Test
    public void isSameIpv4Seg() {
        String[] ips = {"1.2.3.4", "1.2.3.5"};
        assertTrue(IpTool.isSameIpv4Seg("255.255.255.0", ips));

        String[] ips2 = {"1.2.4.5", "1.2.6.7"};
        assertTrue(IpTool.isSameIpv4Seg("255.255.0.0", ips2));

        String[] ips3 = {"1.3.4.5", "1.6.7.8"};
        assertTrue(IpTool.isSameIpv4Seg("255.0.0.0", ips3));

        String[] ips4 = {"1.2.3.4", "5.6.7.8"};
        assertTrue(IpTool.isSameIpv4Seg("0.0.0.0", ips4));

        String[] ips5 = {"1.2.3.4", "1.2.5.6"};
        assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", ips5));

        String[] ips6 = {"1.3.4.5", "1.6.7.8"};
        assertFalse(IpTool.isSameIpv4Seg("255.255.0.0", ips6));

        String[] ips7 = {"1.2.3.4", "5.6.7.8"};
        assertFalse(IpTool.isSameIpv4Seg("255.0.0.0", ips7));

        String[] ips8 = {"1.2.3.4", "5.6.7.888"};
        assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", ips8));

        String[] ips9 = {"1.2.3.4", null};
        assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", ips9));

        assertFalse(IpTool.isSameIpv4Seg("255.255.255.0"));
        assertFalse(IpTool.isSameIpv4Seg("255.255.255.0", new String[0]));
    }

    @Test
    public void getFixLengthIpv4() {
        String ip = IpTool.getFixLengthIpv4("1.2.13.224");
        assertEquals("001.002.013.224", ip);

        ip = IpTool.getFixLengthIpv4("1.2.13.256");
        assertEquals("1.2.13.256", ip);

        ip = IpTool.getFixLengthIpv4(null);
        assertEquals(null, ip);
    }

    @Test
    public void getNormalIpv4() {
        String ip = IpTool.getNormalIpv4("001.002.013.224");
        assertEquals("1.2.13.224", ip);

        ip = IpTool.getNormalIpv4("1.2.13.256");
        assertEquals("1.2.13.256", ip);

        ip = IpTool.getNormalIpv4(null);
        assertEquals(null, ip);
    }

    @Test
    public void isLocalIpv4() {
        assertTrue(IpTool.isLocalIpv4("127.0.0.1"));
        assertTrue(IpTool.isLocalIpv4("192.168.0.123"));
        assertFalse(IpTool.isLocalIpv4("220.160.156.242"));
    }


}

