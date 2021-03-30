package com.averin.networkModel;

import com.averin.networkModel.pathElements.active.L3Device;

public class ArpRequest {
    private MacAddress senderMacAddress;
    private IPv4 senderIp;
    private IPv4 recipientIp;

    public ArpRequest(L3Device pathElement, IPv4 recipientIp) {
        this.senderMacAddress = pathElement.getMacAddress();
        this.senderIp = pathElement.getIp();
        this.recipientIp = recipientIp;
    }

    public MacAddress getSenderMacAddress() {
        return new MacAddress(senderMacAddress);
    }

    public IPv4 getSenderIp() {
        return senderIp;
    }

    public IPv4 getRecipientIp() {
        return new IPv4(recipientIp);
    }
}
