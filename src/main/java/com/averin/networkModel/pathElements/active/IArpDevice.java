package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.List;

public interface IArpDevice extends IPathElement {

    default MacAddress sendArpRequest(ArpRequest arpRequest, IPathElement lastSender) {
        for (IPathElement connection : getConnections(lastSender)) {
            if (connection instanceof PassiveElement) {
                MacAddress result = ((PassiveElement)connection).sendAll(arpRequest, this);

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

    default List<IPathElement> getRouteByMacAddress(MacAddress recipientMacAddress, IPathElement sender) {
        for (IPathElement connection : getConnections(sender)) {
            if (connection instanceof PassiveElement) {
                List<IPathElement> route = ((PassiveElement)connection).sendAll(recipientMacAddress, this);

                if (route != null) {
                    route.add(0, this);
                    return route;
                }
            }
        }
        return null;
    }
}