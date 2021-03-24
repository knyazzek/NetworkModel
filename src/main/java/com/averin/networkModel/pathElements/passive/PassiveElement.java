package com.averin.networkModel.pathElements.passive;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.ActiveElement;
import java.util.HashSet;
import java.util.Set;

public abstract class PassiveElement implements IPathElement {

    private Set<ActiveElement> connections = new HashSet<>();

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
    public Set<ActiveElement> getConnections() {
        return new HashSet<>(connections);
    }

    public ActiveElement getConnection(ActiveElement sender) {
        Set<ActiveElement> connections = getConnections();
        connections.remove(sender);
        if (connections.size() == 1) {
            return connections.iterator().next();
        } else {
            System.out.println("Passive element has more than 2 connections");
            return null;
        }
    }

    public void addConnection(ActiveElement activeElement) {
        connections.add(activeElement);
        activeElement.addConnection(this);
    }

    @Override
    public String getInfo() {
        return null;
    }
}