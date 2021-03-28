package com.averin.networkModel.pathElements;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface IPathElement {
    public int getId();
    public void setId(int id);
    public int getTimeDelay();
    public int getCosts();
    public Set<IPathElement> getConnections();

    default public Set<IPathElement> getConnections(IPathElement sender) {
        Collection<IPathElement> connections = getConnections();
        connections.remove(sender);
        return new HashSet<>(connections);
    }

    public void addConnection(IPathElement pathElement);
    public String getInfo();
}