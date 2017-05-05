package qye;

import org.junit.Test;
import qye.IpAddressMatcher.IpMask;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yeqinyong on 17/5/5.
 */
public class IpAddressMatcherTest {
    static List<String> ipAddresses = Arrays.asList(
            "10.0.0.0/8",
            "172.16.0.0/12",
            "192.168.0.0/16",
            "100.64.0.0/10",
            "169.254.0.0/16",
            "127.0.0.1/8");

    static List<IpMask> ipMasks = Arrays.asList(
            new IpMask(0x0a000000, 8),
            new IpMask(0xac100000, 12),
            new IpMask(0xc0a80000, 16),
            new IpMask(0x64400000, 10),
            new IpMask(0xa9fe0000, 16),
            new IpMask(0x7f000001, 8)
            );
    @Test
    public void create() throws Exception {
        IpAddressMatcher matcher = IpAddressMatcher.create(ipAddresses);
        assertEquals(ipMasks, matcher.ipMasks);
    }

    @Test
    public void match() throws Exception {
        IpAddressMatcher matcher = IpAddressMatcher.create(ipAddresses);
        assertTrue(matcher.match("10.1.2.3"));
        assertTrue(matcher.match("172.16.1.0"));
        assertTrue(matcher.match("172.31.255.255"));
        assertTrue(matcher.match("192.168.1.0"));
        assertTrue(matcher.match("100.127.1.0"));
        assertTrue(matcher.match("127.0.0.1"));

        assertFalse(matcher.match("9.0.0.0"));
        assertFalse(matcher.match("172.15.0.0"));
        assertFalse(matcher.match("172.32.0.0"));
        assertFalse(matcher.match("192.169.0.0"));
        assertFalse(matcher.match("100.63.0.0"));
        assertFalse(matcher.match("100.128.0.0"));
    }

}
