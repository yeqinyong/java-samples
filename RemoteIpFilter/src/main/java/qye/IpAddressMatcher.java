package qye;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqinyong on 17/5/5.
 */
public class IpAddressMatcher {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class IpMask {
        int ip;
        int mask;
    }

    List<IpMask> ipMasks;

    public IpAddressMatcher(List<IpMask> ipMasks){
        this.ipMasks = ipMasks;
    }

    public static IpAddressMatcher create(List<String> ipAddresses) {
        List<IpMask> ipMasks = new ArrayList<>(ipAddresses.size());
        for(String ipAddress : ipAddresses) {
            ipMasks.add(createIpMask(ipAddress));
        }
        return new IpAddressMatcher(ipMasks);
    }

    private static IpMask createIpMask(String ipAddress) {
        IpMask ipMask = new IpMask();

        if (ipAddress.indexOf('/') > 0) {
            String[] addressAndMask = ipAddress.split("/");
            ipMask.ip = parseIp(addressAndMask[0]);
            ipMask.mask = Integer.parseInt(addressAndMask[1]);
        }
        else {
            ipMask.ip = parseIp(ipAddress);
            ipMask.mask = -1;
        }
        return ipMask;
    }

    private static int parseIp(String ipAddress)  {
        byte[] ipbytes = new byte[0];
        try {
            ipbytes = Inet4Address.getByName(ipAddress).getAddress();
        } catch (UnknownHostException e) {
            return 0;
        }
        return ((ipbytes[0] << 24)& 0xFF000000) | ((ipbytes[1] << 16)& 0xFF0000) | ((ipbytes[2] << 8)& 0xFF00) | (ipbytes[3]& 0xFF);
    }

    public boolean match(String ipAddress){
        int ip = parseIp(ipAddress);

        for(IpMask ipMask: ipMasks) {
            if(0 < ipMask.mask && ipMask.mask < 32){
                if((ipMask.ip ^ ip) >>> (32-ipMask.mask) == 0 ){
                    return true;
                }
            }else if(ipMask.ip == ip) {
                return true;
            }
        }
        return false;
    }
}
