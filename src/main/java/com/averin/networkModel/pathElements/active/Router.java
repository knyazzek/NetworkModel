package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;

import java.util.*;

public class Router extends ActiveElement implements IArpDevice{
    private Set<RoutingTableRow> routingTable = new HashSet<>();
    private Map<IPv4, MacAddress> arpTable = new HashMap<>();

    public Router(IPv4 ip, MacAddress macAddress) {
        super(ip, macAddress);
    }

    @Override
    public MacAddress sendArpRequest(ArpRequest arpRequest, IPathElement lastSender) {
        if (!arpTable.containsKey(arpRequest.getSenderIp())) {
            arpTable.put(arpRequest.getSenderIp(), arpRequest.getSenderMacAddress());
        }

        if (arpTable.containsKey(arpRequest.getRecipientIp())) {
            return arpTable.get(arpRequest.getRecipientIp());
        }

        for (RoutingTableRow routingTableRow : routingTable) {
            if (routingTableRow.getConnection().equals(lastSender)
                    && routingTableRow.getInterf().equals(arpRequest.getRecipientIp())) {
                return getMacAddress();
            }
        }
        return null;
    }

    @Override
    public List<IPathElement> getRouteByMacAddress(MacAddress recipientMacAddress, IPathElement sender) {
        if (this.getMacAddress().equals(recipientMacAddress)) {
            List<IPathElement> route = new LinkedList<>();
            route.add(this);
            return route;
        }
        return IArpDevice.super.getRouteByMacAddress(recipientMacAddress, sender);
    }

    @Override
    public String getInfo() {
        return "";
    }

    private class RoutingTableRow {
        private int[] destination;
        private int[] netmask;
        private int[] gateway;
        private IPathElement connection;
        private IPv4 interf;
        private int metric;

        public RoutingTableRow(int[] destination,
                               int[] netmask,
                               int[] gateway,
                               IPathElement connection,
                               IPv4 interf,
                               int metric) {
            this.destination = destination;
            this.netmask = netmask;
            this.gateway = gateway;
            this.connection = connection;
            this.interf = interf;
            this.metric = metric;
        }

        public int[] getDestination() {
            return destination;
        }

        public void setDestination(int[] destination) {
            this.destination = destination;
        }

        public int[] getNetmask() {
            return netmask;
        }

        public void setNetmask(int[] netmask) {
            this.netmask = netmask;
        }

        public int[] getGateway() {
            return gateway;
        }

        public void setGateway(int[] gateway) {
            this.gateway = gateway;
        }

        public IPathElement getConnection() {
            return connection;
        }

        public void setConnection(IPathElement connection) {
            this.connection = connection;
        }

        public IPv4 getInterf() {
            return interf;
        }

        public void setInterf(IPv4 interf) {
            this.interf = interf;
        }

        public int getMetric() {
            return metric;
        }

        public void setMetric(int metric) {
            this.metric = metric;
        }
    }

    public void addRoutingTableRow(int[] destination,
                                   int[] netmask,
                                   int[] gateway,
                                   IPathElement connection,
                                   IPv4 interf,
                                   int metric) {
        routingTable.add(new RoutingTableRow(destination, netmask, gateway,connection, interf, metric));
    }

    @Override
    public void addConnection(IPathElement pathElement) {
        super.addConnection(pathElement);
    }

    @Override
    public String toString() {
        return "Router";
    }
}