package com.averin.networkModel;

import com.averin.networkModel.pathElements.active.ActiveElement;

public interface IChannelLayerDevice extends com.averin.networkModel.IPathElement {

    default MacAddress sendArpRequest (ActiveElement sender, IPV4 ip) {
        for (ActiveElement connection : sender.getConnections().values()) {

            if (connection instanceof IChannelLayerDevice) {
                MacAddress response = ((IChannelLayerDevice) connection).respondArpRequest(sender, ip);

                if (!(response == null))
                    return response;
            }
        }

        return null;
    }

    public abstract MacAddress respondArpRequest(ActiveElement sender, IPV4 ip);
}
