package com.averin.networkModel.pathElements.passive;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.ActiveElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PassiveElement implements IPathElement {
    private int id;
    private int timeDelay;
    private int cost;
    private Set<IPathElement> connections = new HashSet<>();

    public MacAddress sendAll(ArpRequest arpRequest, IPathElement sender) {
        for (IPathElement connection : getConnections(sender)) {
            MacAddress macAddress = null;

            if (connection instanceof PassiveElement) {
                macAddress = ((PassiveElement)connection).sendAll(arpRequest, this);
            }else if (connection instanceof ActiveElement) {
                macAddress = ((ActiveElement)connection).sendArpRequest(arpRequest, this);
            }

            if (macAddress != null)
                return macAddress;
        }
        return null;
    }
    public List<IPathElement> sendAll(MacAddress recipientMacAddress, IPathElement sender) {
        for (IPathElement connection : getConnections(sender)) {
            List<IPathElement> route = null;

            if (connection instanceof PassiveElement) {
                route = ((PassiveElement)connection).sendAll(recipientMacAddress, this);
            }
            if (connection instanceof ActiveElement) {
                route = ((ActiveElement)connection).
                        getRouteByMacAddress(recipientMacAddress, this);
            }

            if (route != null) {
                route.add(0, this);
                return route;
            }
        }
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getTimeDelay() {
        return 0;
    }

    @Override
    public int getCosts() {
        return 0;
    }

    @Override
    public Set<IPathElement> getConnections() {
        return new HashSet<>(connections);
    }

    @Override
    public void addConnection(IPathElement pathElement) {
        connections.add(pathElement);
    }

    @Override
    public String getInfo() {
        return "";
    }
}