package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.Request;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;

public class PC extends L3Device{
    private IPv4 gateway;
    public PC(IPv4 ip, MacAddress macAddress, IPv4 gateway) {
        super(ip,macAddress);
        setGateway(gateway);
    }

    @Override
    public MacAddress sendArpRequest(Request arpRequest, IPathElement lastSender) {
        if (this.getIp().equals(arpRequest.getRecipientIp())) {
            getArpTable().put(arpRequest.getSenderIp(), arpRequest.getSenderMacAddress());
            return getMacAddress();
        }
        return super.sendArpRequest(arpRequest, lastSender);
    }

    public IPv4 getGateway() {
        return gateway;
    }

    public void setGateway(IPv4 gateway) {
        this.gateway = gateway;
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
