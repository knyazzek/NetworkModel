package com.averin.networkModel.routeProviders;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.Network;
import java.util.List;

public interface IRouteProvider {
    public List<IPathElement> getRoute(int firstID, int secondID, Network net);

}