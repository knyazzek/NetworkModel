package com.averin.networkModel;

import com.averin.networkModel.pathElements.passive.Hub;
import com.averin.networkModel.pathElements.active.PC;
import com.averin.networkModel.pathElements.active.Switch;
import com.averin.networkModel.pathElements.passive.Cable;
import com.averin.networkModel.routeProviders.RipRouteProvider;

public class Main {
    public static void main(String[] args) {
        IPv4 ip1 = new IPv4("122.122.122.1", "255.255.255.0");
        IPv4 ip2 = new IPv4("122.122.122.2", "255.255.255.0");
        IPv4 ip3 = new IPv4("122.122.122.3", "255.255.255.0");
        IPv4 ip4 = new IPv4("122.122.122.4", "255.255.255.0");
        IPv4 ip5 = new IPv4("122.122.122.5", "255.255.255.0");
        IPv4 ip6 = new IPv4("122.122.122.6", "255.255.255.0");

        MacAddress mac1 = new MacAddress("1A-1A-1A-1A-1A-1A");
        MacAddress mac2 = new MacAddress("1A-1A-1A-1A-1A-1B");
        MacAddress mac3 = new MacAddress("1A-1A-1A-1A-1A-1C");
        MacAddress mac4 =new MacAddress("1A-1A-1A-1A-1A-1D");
        MacAddress mac5 =new MacAddress("1A-1A-1A-1A-1A-1E");
        MacAddress mac6 =new MacAddress("1A-1A-1A-1A-1A-1F");
        PC pc1 = new PC(ip1,mac1);
        PC pc2 = new PC(ip2,mac2);

        Hub hub1 = new Hub();
        Hub hub2 = new Hub();

        PC pc3 = new PC(ip3, mac3);
        PC pc4 = new PC(ip4, mac4);

        Switch sw1 = new Switch(ip5, mac5);

        Cable cable1 = new Cable(pc1, hub1);
        Cable cable2 = new Cable(pc2, hub1);
        Cable cable3 = new Cable(hub1, sw1);
        Cable cable4 = new Cable(pc3, hub2);
        Cable cable5 = new Cable(pc4, hub2);
        Cable cable6 = new Cable(hub2, sw1);

        Network network = new Network();

        network.addPathElement(pc1);
        network.addPathElement(pc2);
        network.addPathElement(pc3);
        network.addPathElement(pc4);

        network.addPathElement(hub1);
        network.addPathElement(hub2);

        network.addPathElement(sw1);

        network.addPathElement(cable1);
        network.addPathElement(cable2);
        network.addPathElement(cable3);
        network.addPathElement(cable4);
        network.addPathElement(cable5);
        network.addPathElement(cable6);

        System.out.println(network.pathElements);

        NetworkTest networkTest = new NetworkTest();
        networkTest.networkList.add(network);
        networkTest.providerList.add(RipRouteProvider.class);

        networkTest.start();
    }
}