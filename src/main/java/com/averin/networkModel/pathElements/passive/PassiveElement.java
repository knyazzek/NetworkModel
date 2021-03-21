package com.averin.networkModel.pathElements.passive;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.ActiveElement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class PassiveElement implements IPathElement {

    private Set<IPathElement> connections = new HashSet<>();

    @Override
    public int getID() {
        return 0;
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
    public Set<IPathElement> getConnections(IPathElement sender) {
        Set<IPathElement> connections = getConnections();
        connections.remove(sender);

        return connections;
    }

    @Override
    public void addConnection(IPathElement activeElement) {
        connections.add(activeElement);
        activeElement.addConnection(this);
    }

    @Override
    public String getInfo() {
        return null;
    }
}