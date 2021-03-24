package com.averin.networkModel.pathElements.passive;

import com.averin.networkModel.pathElements.active.ActiveElement;

public class Cable extends PassiveElement {
    final int MAX_CONNECTION_COUNT = 2;

    public Cable(ActiveElement activeElement1, ActiveElement activeElement2) {
        addConnection(activeElement1);
        addConnection(activeElement2);
    }

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
    public String getInfo() {
        return null;
    }
}
