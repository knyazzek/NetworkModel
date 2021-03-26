package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;

import java.util.*;

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
    public List<IPathElement> getRouteByMacAddress(MacAddress macAddress, ArpDevice sender) {
        if (this.getMacAddress().equals(macAddress)) {
            List<IPathElement> route = new LinkedList<>();
            route.add(this);
            return route;
        }
        return super.getRouteByMacAddress(macAddress, sender);
    }

    @Override
    public String getInfo() {
        return "";
    }

    @Override
    public String toString() {
        return "PC ";
    }
}
