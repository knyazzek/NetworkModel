package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.HashMap;
import java.util.Map;

public abstract class ActiveElement implements IPathElement {

    private IPV4 ipV4;
    private MacAddress macAddress;
    private int id;
    private int timeDelay;
    private int cost;
    private Map<PassiveElement,ActiveElement> connections = new HashMap<>();

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

    public Map<PassiveElement, ActiveElement> getConnections() {
        return new HashMap<PassiveElement, ActiveElement>(connections);
    }

    public void addConnection(PassiveElement passiveElement, ActiveElement activeElement) {
            connections.put(passiveElement, activeElement);
    }

    public void removeConnection(PassiveElement passiveElement, ActiveElement activeElement) {
        connections.remove(passiveElement, activeElement);
    }

    public abstract String getInfo();
}