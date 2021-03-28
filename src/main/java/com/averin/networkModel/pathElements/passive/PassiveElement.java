package com.averin.networkModel.pathElements.passive;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.IArpDevice;
import com.averin.networkModel.pathElements.active.PC;
import com.averin.networkModel.pathElements.active.Switch;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class PassiveElement implements IPathElement {
    private int id;
    private int timeDelay;
    private int cost;
    private Set<IPathElement> connections = new HashSet<>();

    public MacAddress sendAll(ArpRequest arpRequest, IPathElement sender) {
        System.out.println(getId());
        for (IPathElement connection : getConnections()) {
            if (connection == sender) continue;

            MacAddress macAddress = null;

            if (connection instanceof PassiveElement) {
                macAddress = ((PassiveElement)connection).sendAll(arpRequest, this);
            }else if (connection instanceof PC) {
                macAddress = ((PC)connection).sendArpRequest(arpRequest, this);
            }else if (connection instanceof Switch) {
                macAddress = ((IArpDevice)connection).sendArpRequest(arpRequest, this);
            }

            if (macAddress != null)
                return macAddress;
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