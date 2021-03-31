package com.averin.networkModel;

import com.averin.networkModel.pathElements.active.L3Device;

public class Request {
    private MacAddress senderMacAddress;
    private IPv4 senderIp;
    private IPv4 recipientIp;

    public Request(L3Device sender, IPv4 recipientIp) {
        this.senderMacAddress = sender.getMacAddress();
        this.senderIp = sender.getIp();
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
