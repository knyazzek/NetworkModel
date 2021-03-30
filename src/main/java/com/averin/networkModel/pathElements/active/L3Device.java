package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class L3Device extends ActiveElement{
    private IPv4 ip;
    private MacAddress macAddress;
    private Map<IPv4, MacAddress> arpTable = new HashMap<>();

    public L3Device(IPv4 ip, MacAddress macAddress) {
        setIp(ip);
        setMacAddress(macAddress);
    }

    @Override
    public List<IPathElement> getRouteByMacAddress(MacAddress recipientMacAddress, IPathElement sender) {
        if (this.getMacAddress().equals(recipientMacAddress)) {
            List<IPathElement> route = new LinkedList<>();
            route.add(this);
            return route;
        }
        return super.getRouteByMacAddress(recipientMacAddress, sender);
    }

    public IPv4 getIp() {
        return ip;
    }

    public void setIp(IPv4 ip) {
        this.ip = ip;
    }

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }

    public Map<IPv4, MacAddress> getArpTable() {
        return arpTable;
    }

    public void setArpTable(Map<IPv4, MacAddress> arpTable) {
        this.arpTable = arpTable;
    }
}
