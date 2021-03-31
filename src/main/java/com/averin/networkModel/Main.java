package com.averin.networkModel;

import com.averin.networkModel.pathElements.active.Router;
import com.averin.networkModel.pathElements.passive.Hub;
import com.averin.networkModel.pathElements.active.PC;
import com.averin.networkModel.pathElements.active.Switch;
import com.averin.networkModel.pathElements.passive.Cable;
import com.averin.networkModel.routeProviders.RipRouteProvider;

public class Main {
    public static void main(String[] args) {
        IPv4 ipPC1 = new IPv4("122.122.122.1", "255.255.255.0");
        IPv4 ipPC2 = new IPv4("122.122.122.2", "255.255.255.0");
        IPv4 ipPC3 = new IPv4("122.122.122.3", "255.255.255.0");
        IPv4 ipPC4 = new IPv4("122.122.122.4", "255.255.255.0");
        IPv4 ipPC5 = new IPv4("10.0.0.1", "255.255.255.0");
        IPv4 ipInterface1Router1 = new IPv4("122.122.122.6", "255.255.255.0");
        IPv4 ipInterface2Router1 = new IPv4("2.2.2.1", "255.255.255.0");
        IPv4 ipInterface1Router2 = new IPv4("10.0.0.2", "255.255.255.0");
        IPv4 ipInterface2Router2 = new IPv4("2.2.2.2", "255.255.255.0");
        IPv4 ipRouter1 = new IPv4("1.1.1.1", "255.255.255.0");
        IPv4 ipRouter2 = new IPv4("1.1.1.2", "255.255.255.0");

        MacAddress macPC1 = new MacAddress("1A-1A-1A-1A-1A-1A");
        MacAddress macPC2 = new MacAddress("1A-1A-1A-1A-1A-1B");
        MacAddress macPC3 = new MacAddress("1A-1A-1A-1A-1A-1C");
        MacAddress macPC4 = new MacAddress("1A-1A-1A-1A-1A-1D");
        MacAddress macPC5 = new MacAddress("1A-1A-1A-1A-1B-1A");
        MacAddress macRouter1 = new MacAddress("1A-1A-1A-1A-1A-1F");
        MacAddress macRouter2 = new MacAddress("1A-1A-1A-1A-1B-1B");

        PC pc1 = new PC(ipPC1, macPC1, ipInterface1Router1);
        PC pc2 = new PC(ipPC2, macPC2, ipInterface1Router1);
        PC pc3 = new PC(ipPC3, macPC3, ipInterface1Router1);
        PC pc4 = new PC(ipPC4, macPC4, ipInterface1Router1);
        PC pc5 = new PC(ipPC5, macPC5, ipInterface1Router2);

        Hub hub1 = new Hub();
        Hub hub2 = new Hub();

        Switch sw1 = new Switch();

        Router router1 = new Router(ipRouter1, macRouter1);
        Router router2 = new Router(ipRouter2, macRouter2);

        Cable cable1 = new Cable(pc1, hub1);
        Cable cable2 = new Cable(pc2, hub1);
        Cable cable3 = new Cable(pc3, hub2);
        Cable cable4 = new Cable(pc4, hub2);
        Cable cable5 = new Cable(hub1, sw1);
        Cable cable6 = new Cable(hub2, sw1);
        Cable cable7 = new Cable(sw1, router1);
        Cable cable8 = new Cable(router1, router2);
        Cable cable9 = new Cable(router2, pc5);

        router1.addRoutingTableRow(new int[] {122,122,122,0},
                new int[] {255,255,255,0},
                new int[] {122,122,122,6},
                cable7,
                ipInterface1Router1
        );
        router1.addRoutingTableRow(new int[] {10,0,0,0},
                new int[] {255,255,255,0},
                new int[] {2,2,2,1},
                cable8,
                ipInterface2Router2
        );

        router2.addRoutingTableRow(new int[] {122,122,122,0},
                new int[] {255,255,255,0},
                new int[] {2,2,2,1},
                cable8,
                ipInterface2Router1
        );
        router2.addRoutingTableRow(new int[] {10,0,0,0},
                new int[] {255,255,255,0},
                new int[] {10,0,0,2},
                cable9,
                ipInterface1Router2
        );

        Network network = new Network();
        network.addPathElements(pc1,pc2,pc3,pc4,pc5);
        network.addPathElements(hub1,hub2);
        network.addPathElement(sw1);
        network.addPathElements(router1,router2);
        network.addPathElements(cable1, cable2, cable3, cable4, cable5, cable6, cable7, cable8, cable9);

        NetworkTest networkTest = new NetworkTest();
        networkTest.networkList.add(network);
        networkTest.providerList.add(RipRouteProvider.class);

        networkTest.start();
        System.out.println(network);

/*        Request request = new Request(pc1, pc5.getIp());
        System.out.println(router1.getRouteByRequest(request, null));*/
    }
}