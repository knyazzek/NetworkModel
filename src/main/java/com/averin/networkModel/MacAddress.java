package com.averin.networkModel;

import java.util.Arrays;

public class MacAddress {

    private String[] macAddress;

    //TODO macAddress validate
    public MacAddress(String str) {
        if (isMacAddress(str)) {
            setMacAddress(str);
        } else {
            System.out.println("Incorrect Mac");
        }
    }

    //TODO macAddress validate
    public MacAddress(String[] macAddress) {
        this.macAddress = Arrays.copyOf(macAddress, macAddress.length);
    }

    public MacAddress(MacAddress macAddress) {
        this(macAddress.getMacAddress());
    }

    public static boolean isMacAddress(String str) {

        String[] octets = str.split("-");

        if (octets.length != 6)
            return false;

        for (String octet : octets) {
            if (octet.length() != 2 || !octet.matches("^[0-9a-fA-F]+$"))
                return false;
        }

        return true;
    }

    public String[] getMacAddress() {
        return Arrays.copyOf(macAddress, macAddress.length);
    }

    //TODO macAddress validate
    private void setMacAddress(String macAddress) {
        this.macAddress = macAddress.split("-");
    }

    @Override
    public String toString() {
        return "MacAddress{" +
                macAddress[0] + "-" +
                macAddress[1] + "-" +
                macAddress[2] + "-" +
                macAddress[3] + "-" +
                macAddress[4] + "-" +
                macAddress[5] +
                '}';
    }
}
