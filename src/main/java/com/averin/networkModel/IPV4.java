package com.averin.networkModel;

import java.util.Arrays;

public class IPV4 {
    private int[] ip = new int[4];
    private int[] netMask = new int[4];
    private int[] netAddress;

    //TODO IPV4 validation
    public IPV4(String ip) {
        if (IPV4.isIPV4(ip)) {
            setIp(ip);
        } else {
            System.out.println("Incorrect ip");
        }
    }

    public IPV4(String ip, String netMask) {
        this(ip);

        if (IPV4.isIPV4(netMask)) {
            setNetMask(netMask);
        } else {
            System.out.println("Incorrect ip");
        }
    }

    //TODO IPV4 validation
    public IPV4(int[] ip, int[] netMask) {
        this.ip = Arrays.copyOf(ip, ip.length);
        this.netMask = Arrays.copyOf(netMask, netMask.length);
    }

    public IPV4(IPV4 ipv4) {
        this(ipv4.getIp(), ipv4.getNetMask());
    }

    public static boolean isIPV4 (String ip) {
        String[] octets = ip.split("\\.");

        if (octets.length != 4)
            return false;

        for (String octet : octets) {
            if (!octet.matches("^[0-9]+$"))
                return false;

            int octetNumber = Integer.parseInt(octet);
            if (octetNumber > 255)
                return false;
        }

        return true;
    }

    public int[] getIp() {
        return Arrays.copyOf(ip, ip.length);
    }

    private void setIp(String ip) {
        String[] octets = ip.split("\\.");
        if (octets.length != 4) {
            //Throw Exception
        }

        for (int i = 0; i < octets.length; i++) {
            this.ip[i] = Integer.parseInt(octets[i]);
        }
    }

    public int[] getNetMask() {
        return netMask;
    }


    //TODO netMask validation
    private void setNetMask(String netMask) {
        String[] octets = netMask.split("\\.");

        if (octets.length != 4) {
            System.out.println("Incorrect netMask");
        }

        for (int i = 0; i < octets.length; i++) {
            this.netMask[i] = Integer.parseInt(octets[i]);
        }
    }

    public int[] getNetAddress() {
        return Arrays.copyOf(netAddress, netAddress.length);
    }

    //TODO setNetAddress
    private void setNetAddress() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPV4 ipv4 = (IPV4) o;
        return Arrays.equals(ip, ipv4.ip);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(ip);
        result = 31 * result + Arrays.hashCode(netMask);
        result = 31 * result + Arrays.hashCode(netAddress);
        return result;
    }

    @Override
    public String toString() {
        return "IPV4{" +
                "ip=" + Arrays.toString(ip) +
                ", netMask=" + Arrays.toString(netMask) +
                ", netAddress=" + Arrays.toString(netAddress) +
                '}';
    }
}
