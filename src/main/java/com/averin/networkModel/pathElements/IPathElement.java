package com.averin.networkModel.pathElements;

import java.util.Set;

public interface IPathElement {
    public int getID();
    public int getTimeDelay();
    public int getCosts();

    public Set<IPathElement> getConnections();
    public Set<IPathElement> getConnections(IPathElement pathElement);

    public void addConnection(IPathElement pathElement);
    //public void removeConnection(IPathElement pathElement);

    public String getInfo();
}