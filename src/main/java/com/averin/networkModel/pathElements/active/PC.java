package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import java.util.HashMap;
import java.util.Map;

public class PC extends ArpDevice {

    /*
    Что нужно учесть?

    1.количество подключенных уствройств
    2.Gateway
    3.ArpTable
    4.При создании подкладывать сеть, к которой привязан
    */

    private Map<IPV4, MacAddress> arpTable = new HashMap<>();

    public PC(IPV4 ip, MacAddress macAddress) {
        this.setIpV4(ip);
        this.setMacAddress(macAddress);
    }

    @Override
    public MacAddress respondArpRequest(ArpDevice pathElement, IPV4 ip) {
        ActiveElement sender = (ActiveElement) pathElement;

        if (!arpTable.containsKey(sender.getIP()))
            arpTable.put(sender.getIP(), sender.getMacAddress());

        if (getIP().equals(ip))
            return getMacAddress();

        return null;
    }

    @Override
    public String getInfo() {
        return "";
    }
}
