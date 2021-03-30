package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;

import java.util.*;

public class PC extends ActiveElement implements IArpDevice{
    private Map<IPv4, MacAddress> arpTable = new HashMap<>();
    private IPv4 gateway;

    public PC(IPv4 ip, MacAddress macAddress) {
        super(ip,macAddress);
    }

    public MacAddress sendArpRequest(ArpRequest arpRequest) {
        return sendArpRequest(arpRequest, this);
    }

    @Override
    public MacAddress sendArpRequest(ArpRequest arpRequest, IPathElement lastSender) {
        if (this.getIp().equals(arpRequest.getRecipientIp())) {
            arpTable.put(arpRequest.getSenderIp(), arpRequest.getSenderMacAddress());
            return getMacAddress();
        }
        return IArpDevice.super.sendArpRequest(arpRequest, lastSender);
    }

    @Override
    public List<IPathElement> getRouteByMacAddress(MacAddress recipientMacAddress, IPathElement sender) {
        if (this.getMacAddress().equals(recipientMacAddress)) {
            List<IPathElement> route = new LinkedList<>();
            route.add(this);
            return route;
        }
        return IArpDevice.super.getRouteByMacAddress(recipientMacAddress, sender);
    }

    @Override
    public String getInfo() {
        return "";
    }

    @Override
    public String toString() {
        return "PC";
    }
}
