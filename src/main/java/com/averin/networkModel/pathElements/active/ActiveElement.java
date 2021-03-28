package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import java.util.*;

public abstract class ActiveElement implements IPathElement {
    private int id;
    private IPv4 ip;
    private MacAddress macAddress;
    private int timeDelay;
    private int costs;
    private Set<IPathElement> connections = new HashSet<>();

    public ActiveElement(IPv4 ip, MacAddress macAddress) {
        setIp(ip);
        setMacAddress(macAddress);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public IPv4 getIp() {
        return new IPv4(ip);
    }

    public void setIp(IPv4 ip) {
        this.ip = ip;
    }

    public MacAddress getMacAddress() {
        return new MacAddress(macAddress);
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }

    public int getTimeDelay() {
        return timeDelay;
    }

    public int getCosts() {
        return costs;
    }

    @Override
    public Set<IPathElement> getConnections() {
        return new HashSet<IPathElement>(connections);
    }

    @Override
    public void addConnection(IPathElement pathElement) {
        connections.add(pathElement);
    }

    public abstract String getInfo();
}