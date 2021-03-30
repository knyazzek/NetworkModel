package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;

public class PC extends L3Device{
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
            getArpTable().put(arpRequest.getSenderIp(), arpRequest.getSenderMacAddress());
            return getMacAddress();
        }
        return super.sendArpRequest(arpRequest, lastSender);
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
