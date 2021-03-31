package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.Request;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.*;

public class Router extends L3Device{
    //TODO make normal RoutingTable
    private Set<RoutingTableRow> routingTable = new HashSet<>();

    public Router(IPv4 ip, MacAddress macAddress) {
        super(ip, macAddress);
    }

    @Override
    public MacAddress sendArpRequest(Request arpRequest, IPathElement lastSender) {
        //Adding a Sender Address
        if (!getArpTable().containsKey(arpRequest.getSenderIp())) {
            getArpTable().put(arpRequest.getSenderIp(), arpRequest.getSenderMacAddress());
        }

        //If the ARP-table already contains the recipient's mac.
        if (getArpTable().containsKey(arpRequest.getRecipientIp())) {
            return getArpTable().get(arpRequest.getRecipientIp());
        }

        //If recipient == this
        for (RoutingTableRow routingTableRow : routingTable) {
            if (routingTableRow.getConnection().equals(lastSender)
                    && routingTableRow.getInterf().equals(arpRequest.getRecipientIp())) {
                return getMacAddress();
            }
        }

        for (RoutingTableRow routingTableRow : routingTable) {
            //If this interface points to the recipient's network.
            if (Arrays.equals(arpRequest.getRecipientIp().getNetAddress(), routingTableRow.destination)) {
                MacAddress recipientMac =
                        ((PassiveElement)routingTableRow.connection).
                                sendAllArpRequest(arpRequest, this);

                if (recipientMac != null)
                    addArpTableRow(arpRequest.getRecipientIp(), recipientMac);

                return recipientMac;
            }
        }

        return null;
    }

    //TODO fix getRouteByMacAddress in Router.
    @Override
    public List<IPathElement> getRouteByMacAddress(MacAddress recipientMacAddress, IPathElement sender) {
        //If recipient == this
        if (this.getMacAddress().equals(recipientMacAddress)) {
            List<IPathElement> route = new LinkedList<>();
            route.add(this);
            return route;
        }

        IPv4 recipientIP = null;

        //May be an NPE here
        for (Map.Entry arpTableRow : getArpTable().entrySet()) {
            if (arpTableRow.getValue().equals(recipientMacAddress)) {
                recipientIP = (IPv4)arpTableRow.getKey();
            }
        }
        if (recipientIP == null) return null;

        List<IPathElement> route = null;

        for (RoutingTableRow routingTableRow : routingTable) {
            if (Arrays.equals(routingTableRow.getDestination(), recipientIP.getNetAddress())) {
                route = ((PassiveElement)routingTableRow.connection).
                        sendAll(recipientMacAddress, this);
            }
        }

        if (route == null) {
            System.out.println("");
            return null;
        } else {
            route.add(0, this);
            return route;
        }
    }

    public List<IPathElement> getRouteByRequest(Request request, IPathElement lastSender) {
        for (RoutingTableRow routingTableRow : routingTable) {
            if (Arrays.equals(request.getRecipientIp().getNetAddress(), routingTableRow.destination)) {

                //If this subnet is already connected to the router
                if (Arrays.equals(routingTableRow.getInterf().getNodeAddress(),
                        routingTableRow.gateway)) {
                    return getRouteFromConnectedSubnet(request.getRecipientIp(),
                            routingTableRow,
                            this);
                }

                List<IPathElement> route = null;
                route = ((PassiveElement)routingTableRow.connection).sendAllRequest(request, this);

                if (route == null) return null;

                route.add(0, this);
                return route;
            }
        }
        return null;
    }

    private List<IPathElement> getRouteFromConnectedSubnet(IPv4 recipientIp,
                                                           RoutingTableRow routingTableRow,
                                                           IPathElement lastSender) {
        MacAddress recipientMac = null;

        //We check whether the recipient's mac address is in the ARP-table.
        if (!getArpTable().containsKey(recipientIp)) {
            //TODO arpRequest should get gray router IP.
            Request arpRequest = new Request(this, recipientIp);
            recipientMac = ((PassiveElement)routingTableRow.getConnection()).sendAllArpRequest(arpRequest, this);
            addArpTableRow(recipientIp, recipientMac);
        }
        return getRouteByMacAddress(recipientMac, lastSender);
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
                                   IPv4 interf) {
        int metric;
        if (Arrays.equals(interf.getNetAddress(), destination)) {
            metric = 0;
        } else {
            metric = 1;
        }

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