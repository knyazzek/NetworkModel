package com.averin.networkModel.pathElements.passive;

import com.averin.networkModel.pathElements.IPathElement;

public class Cable extends PassiveElement {
    final byte MAX_CONNECTION_COUNT = 2;

    public Cable() {

    }

    public Cable(IPathElement pe1, IPathElement pe2) {
        addConnection(pe1);
        addConnection(pe2);
    }

    public void connect(IPathElement pe1, IPathElement pe2) {
        addConnection(pe1);
        addConnection(pe2);
    }

    @Override
    public void addConnection(IPathElement pathElement) {
        if (getConnections().size() < MAX_CONNECTION_COUNT) {
            super.addConnection(pathElement);
            pathElement.addConnection(this);
        } else {
            System.out.println("Cable already connects 2 network elements");
        }
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

    @Override
    public String toString() {
        return "Cable ";
    }
}
