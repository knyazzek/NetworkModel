package com.averin.networkModel.routeProviders;

import com.averin.networkModel.IPv4;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.Network;
import java.util.List;

public interface IRouteProvider {
    public List<IPathElement> getRoute(IPv4 senderIP, IPv4 recipientIP, Network net);

}