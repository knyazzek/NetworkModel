package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.passive.PassiveElement;

public abstract class ArpDevice extends ActiveElement{

    public MacAddress sendArpRequest(IPV4 ip) {
        for (PassiveElement connection : this.getConnections()) {
            ActiveElement adjacentActiveElement = connection.getConnection(this);

            if (adjacentActiveElement instanceof ArpDevice) {
                MacAddress response =
                        ((ArpDevice) adjacentActiveElement).respondArpRequest(this, ip);
                if (response != null)
                    return response;
            }
        }
        return null;
    }

    public abstract MacAddress respondArpRequest(ArpDevice sender, IPV4 ip);
}
