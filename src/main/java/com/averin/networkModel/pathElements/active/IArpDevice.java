package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;

import javax.crypto.Mac;

public interface IArpDevice extends IPathElement {

    default MacAddress sendArpRequest(ArpRequest arpRequest, IPathElement lastSender) {
        System.out.println(getId());
        for (IPathElement connection : getConnections(lastSender)) {
            if (connection == lastSender) continue;

            if (connection instanceof PassiveElement) {
                MacAddress result = ((PassiveElement)connection).sendAll(arpRequest, this);

                if (result != null && this instanceof Switch) {
                    ((Switch)this).addSwitching(result, this);
                }
                return result;
            }
        }
        return null;
    }
}