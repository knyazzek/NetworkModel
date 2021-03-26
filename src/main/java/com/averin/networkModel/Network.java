package com.averin.networkModel;

import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.active.ActiveElement;
import com.averin.networkModel.pathElements.active.Hub;

import java.util.HashSet;
import java.util.Set;

public class Network {
    private Set<IPathElement> pathElements = new HashSet<>();

    public ActiveElement findByIP(IPV4 ip) {
        for (IPathElement pathElement : pathElements) {
            if (pathElement instanceof Hub) continue;
            if (pathElement instanceof ActiveElement
                    && ((ActiveElement)pathElement).getIP().equals(ip)) {
                return (ActiveElement)pathElement;
            }
        }
        return null;
    }

    public void addPathElement(IPathElement pathElement) {
        this.pathElements.add(pathElement);
    }

    public void addPathElements(Set<IPathElement> pathElements) {
        this.pathElements.addAll(pathElements);
    }

    /*
    Что нужно реализовать?

    1.Поиск элемента по ID
    2.Поиск элемента по IP
    */
}