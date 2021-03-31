package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.Request;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.*;

public abstract class ActiveElement implements IPathElement {
    private int id;
    private int timeDelay;
    private int costs;
    private Set<IPathElement> connections = new HashSet<>();

    public MacAddress sendArpRequest(Request arpRequest, IPathElement lastSender) {
        for (IPathElement connection : getConnections(lastSender)) {
            if (connection instanceof PassiveElement) {
                MacAddress result = ((PassiveElement)connection).sendAllArpRequest(arpRequest, this);

                if (result != null) {
                    if (this instanceof Switch) {
                        Switch sw = (Switch)this;
                        if (!sw.getSwitchingTable().containsKey(result)){
                            sw.addSwitching(result, connection);
                        }
                    }
                    return result;
                }
            }
        }
        return null;
    }

    public List<IPathElement> getRouteByMacAddress(MacAddress recipientMacAddress, IPathElement sender) {
        for (IPathElement connection : getConnections(sender)) {
            if (connection instanceof PassiveElement) {
                List<IPathElement> route =
                        ((PassiveElement)connection).sendAll(recipientMacAddress, this);

                if (route != null) {
                    route.add(0, this);
                    return route;
                }
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