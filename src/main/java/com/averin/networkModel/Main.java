package com.averin.networkModel;

import com.averin.networkModel.pathElements.active.Hub;
import com.averin.networkModel.pathElements.active.PC;
import com.averin.networkModel.pathElements.active.Switch;
import com.averin.networkModel.pathElements.passive.Cable;
import com.averin.networkModel.routeProviders.RipRouteProvider;

public class Main {
    public static void main(String[] args) {

        IPV4 ip1 = new IPV4("122.122.122.1", "255.255.255.0");
        IPV4 ip2 = new IPV4("122.122.122.2", "255.255.255.0");
        IPV4 ip3 = new IPV4("122.122.122.3", "255.255.255.0");

        IPV4 ip4 = new IPV4("122.122.122.4", "255.255.255.0");

        IPV4 ip5 = new IPV4("122.122.122.5", "255.255.255.0");
        IPV4 ip6 = new IPV4("122.122.122.6", "255.255.255.0");

        MacAddress mac1 = new MacAddress("1A-1A-1A-1A-1A-1A");
        MacAddress mac2 = new MacAddress("1A-1A-1A-1A-1A-1B");
        MacAddress mac3 = new MacAddress("1A-1A-1A-1A-1A-1C");

        MacAddress mac4 =new MacAddress("1A-1A-1A-1A-1A-1D");

        Switch sw1 = new Switch();
        Switch sw2 = new Switch();

        Hub hub1 = new Hub();
        Hub hub2 = new Hub();

        sw1.setIpV4(ip5);
        sw2.setIpV4(ip6);

        PC pc1 = new PC(ip1, mac1);
        PC pc2 = new PC(ip2, mac2);
        PC pc3 = new PC(ip3, mac3);
        PC pc4 = new PC(ip4, mac4);

        Cable cable1 = new Cable(pc1, sw1);
        Cable cable2 = new Cable(pc2, sw1);
        Cable cable3 = new Cable(pc3, sw1);

        Cable cable4 = new Cable(sw1, sw2);
        Cable cable5 = new Cable(hub2, pc4);

        Cable cable6 = new Cable(sw2, hub1);
        Cable cable7 = new Cable(sw2, hub2);

        Network network = new Network();
        network.addPathElement(pc1);
        network.addPathElement(pc2);
        network.addPathElement(pc3);
        network.addPathElement(pc4);
        network.addPathElement(sw1);
        network.addPathElement(sw2);
        network.addPathElement(hub1);
        network.addPathElement(hub2);
        network.addPathElement(cable1);
        network.addPathElement(cable2);
        network.addPathElement(cable3);
        network.addPathElement(cable4);
        network.addPathElement(cable5);
        network.addPathElement(cable6);
        network.addPathElement(cable7);

        NetworkTest networkTest = new NetworkTest();
        networkTest.networkList.add(network);
        networkTest.providerList.add(RipRouteProvider.class);

        networkTest.start();
    }
}