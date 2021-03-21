package com.averin.networkModel.pathElements.passive;

import com.averin.networkModel.IPathElement;

public class Cable extends PassiveElement {

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
    public void removeConnection(IPathElement pathElement) {

    }

    @Override
    public String getInfo() {
        return null;
    }
}
