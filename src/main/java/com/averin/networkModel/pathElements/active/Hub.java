package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.passive.PassiveElement;

public class Hub extends ArpDevice{
    private int maxConnectionsCount = 8;

    @Override
    public MacAddress respondArpRequest(ArpDevice sender, IPV4 ip) {
        return respondArpRequest(sender, sender, ip);
    }

    public MacAddress respondArpRequest(ArpDevice sender, ArpDevice intermediateDevice, IPV4 ip) {
        for (PassiveElement connection : this.getConnections()) {
            ActiveElement adjacentActiveElement = connection.getConnection(this);

            if (adjacentActiveElement == intermediateDevice) continue;

            if (adjacentActiveElement instanceof PC) {
                MacAddress response = ((PC) adjacentActiveElement).
                        respondArpRequest(sender, ip);
                if (response != null) {
                    return response;
                }
            } else if (adjacentActiveElement instanceof Switch) {
                MacAddress response = ((Switch) adjacentActiveElement).
                        respondArpRequest(sender,this, ip);
                if (response != null) {
                    return response;
                }
            }
        }
        return null;
    }

    @Override
    public String getInfo() {
            return null;
    }

    @Override
    public String toString() {
        return "Hub{";
    }
}

