package com.averin.networkModel;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.ActiveElement;
import java.util.HashMap;
import java.util.Map;

public class Network {
     private Map<Integer,IPathElement> pathElements = new HashMap<>();

    public ActiveElement findElementByIp(IPv4 ip) {
        for (IPathElement pathElement : pathElements.values()) {
            if (pathElement instanceof ActiveElement
                    && ((ActiveElement)pathElement).getIp().equals(ip)) {
                return (ActiveElement)pathElement;
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