package com.averin.networkModel.routeProviders;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.Network;
import java.util.List;

public interface IRouteProvider {
    public List<IPathElement> getRoute(IPV4 senderIP, IPV4 recipientIP, Network net);

}