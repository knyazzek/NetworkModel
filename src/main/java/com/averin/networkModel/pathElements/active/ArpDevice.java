package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;

import java.util.List;

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

    public List<IPathElement> getRouteByMacAddress(MacAddress macAddress, ArpDevice sender) {
        for (PassiveElement connection : getConnections()) {
            ActiveElement adjacentActiveElement = connection.getConnection(this);

            if (adjacentActiveElement == sender) continue;

            if (adjacentActiveElement instanceof ArpDevice) {
                List<IPathElement> route=
                        ((ArpDevice)adjacentActiveElement).getRouteByMacAddress(macAddress, this);

                if (route != null) {
                    route.add(0,this);
                    route.add(1,connection);
                    return route;
                }
            }
        }
        return null;
    }
}
