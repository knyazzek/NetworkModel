package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.passive.PassiveElement;

import java.util.*;

public abstract class ActiveElement implements IPathElement {

    private IPV4 ipV4;
    private MacAddress macAddress;
    private int id;
    private int timeDelay;
    private int cost;
    private Set<PassiveElement> connections = new HashSet<>();

    public IPV4 getIP() {
        return new IPV4(ipV4);
    }

    public void setIpV4 (IPV4 ip) {
        this.ipV4 = new IPV4(ip);
    }

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress (MacAddress macAddress) {
        this.macAddress = new MacAddress(macAddress);
    }

    public int getID() {
        return id;
    }

    public int getTimeDelay() {
        return timeDelay;
    }

    public int getCosts() {
        return cost;
    }

    @Override
    public Set<PassiveElement> getConnections() {
        return new HashSet<PassiveElement>(connections);
    }

    public void addConnection(PassiveElement passiveElement) {
        connections.add(passiveElement);
    }

    public abstract String getInfo();
}