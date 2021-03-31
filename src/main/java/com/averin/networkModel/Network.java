package com.averin.networkModel;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.L3Device;
import java.util.HashMap;
import java.util.Map;

public class Network {
     private Map<Integer,IPathElement> pathElements = new HashMap<>();

    public L3Device findElementByIp(IPv4 ip) {
        for (IPathElement pathElement : pathElements.values()) {
            if (pathElement instanceof L3Device
                    && ((L3Device)pathElement).getIp().equals(ip)) {
                return (L3Device) pathElement;
            }
        }
        return null;
    }

    public IPathElement findElementById(int id) {
        return pathElements.get(id);
    }

    public void addPathElement(IPathElement pathElement) {
        pathElement.setId(pathElements.size());
        pathElements.put(pathElement.getId(),pathElement);
    }

    public void addPathElements(IPathElement ... pathElements) {
        for (IPathElement pathElement : pathElements)
            addPathElement(pathElement);
    }

    public Map<Integer, IPathElement> getPathElements() {
        return new HashMap<>(pathElements);
    }
}