package com.averin.networkModel;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.ActiveElement;

public class ArpRequest {
    private MacAddress senderMacAddress;
    private IPv4 senderIp;
    private IPv4 recipientIp;

    public ArpRequest(ActiveElement pathElement, IPv4 recipientIp) {
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
