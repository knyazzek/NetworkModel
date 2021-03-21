package com.averin.networkModel;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.ActiveElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;

import java.util.Set;

public interface IChannelLayerDevice extends IPathElement{

    default MacAddress sendArpRequest(IPV4 ip) {
        for (IPathElement connection : this.getConnections()) {

            for (IPathElement activeElement : connection.getConnections(this)) {

                if (activeElement instanceof IChannelLayerDevice) {
                    MacAddress response =
                            ((IChannelLayerDevice) activeElement).respondArpRequest(this, ip);

                    if (response != null)
                        return response;
                }
            }
        }

        return null;
    }

    public abstract MacAddress respondArpRequest(IChannelLayerDevice sender, IPV4 ip);
}
