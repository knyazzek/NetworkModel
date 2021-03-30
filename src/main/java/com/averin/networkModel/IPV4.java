package com.averin.networkModel;

import java.util.Arrays;

public class IPv4 {
    private int[] nodeAddress = new int[4];
    private int[] netMask = new int[4];
    private int[] netAddress = new int[4];

    //TODO IPV4 validation
    public IPv4(String nodeAddress) {
        if (IPv4.isNodeAddress(nodeAddress)) {
            setNodeAddress(nodeAddress);
        } else {
            System.out.println("Incorrect ip");
        }
    }

    public IPv4(String nodeAddress, String netMask) {
        this(nodeAddress);

        if (IPv4.isNodeAddress(netMask)) {
            setNetMask(netMask);
            setNetAddress();
        } else {
            System.out.println("Incorrect ip");
        }
    }

    //TODO IPV4 validation
    public IPv4(int[] nodeAddress, int[] netMask) {
        this.nodeAddress = Arrays.copyOf(nodeAddress, nodeAddress.length);
        this.netMask = Arrays.copyOf(netMask, netMask.length);
        setNetAddress();
    }

    public IPv4(IPv4 ipv4) {
        this(ipv4.getNodeAddress(), ipv4.getNetMask());
    }

    public static boolean isNodeAddress (String nodeAddress) {
        String[] octets = nodeAddress.split("\\.");

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


    public int[] getNodeAddress() {
        return Arrays.copyOf(nodeAddress, nodeAddress.length);
    }

    private void setNodeAddress(String nodeAddress) {
        String[] octets = nodeAddress.split("\\.");
        if (octets.length != 4) {
            //Throw Exception
        }

        for (int i = 0; i < octets.length; i++) {
            this.nodeAddress[i] = Integer.parseInt(octets[i]);
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
        for (int i = 0; i < 4; i++) {
            netAddress[i] = nodeAddress[i] & netAddress[i];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPv4 ipv4 = (IPv4) o;
        return Arrays.equals(nodeAddress, ipv4.nodeAddress);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(nodeAddress);
        result = 31 * result + Arrays.hashCode(netMask);
        result = 31 * result + Arrays.hashCode(netAddress);
        return result;
    }

    @Override
    public String toString() {
        return "IPV4{" +
                "ip=" + Arrays.toString(nodeAddress) +
                ", netMask=" + Arrays.toString(netMask) +
                ", netAddress=" + Arrays.toString(netAddress) +
                '}';
    }
}
