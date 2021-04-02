package com.averin.networkModel.routeProviders;

import com.averin.networkModel.Request;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.Network;
import com.averin.networkModel.pathElements.active.L3Device;
import com.averin.networkModel.pathElements.active.PC;
import com.averin.networkModel.pathElements.active.Router;
import java.util.Arrays;
import java.util.List;

public class RipRouteProvider implements IRouteProvider {
    @Override
    public List<IPathElement> getRoute(IPv4 senderIP, IPv4 recipientIP, Network net) {
        L3Device sender = net.findElementByIp(senderIP);
        L3Device recipient = net.findElementByIp(recipientIP);

        if (sender == null || recipient == null) {
            System.out.println("One or more network elements with the specified IP do not exist");
            return null;
        }

        //The nodes are in the same subnet.
        if (Arrays.equals(sender.getIp().getNetAddress(), recipient.getIp().getNetAddress())) {
            Request arpRequest = new Request(sender, recipient.getIp());
            MacAddress macAddress = sender.sendArpRequest(arpRequest,null);

            List<IPathElement> route = sender.getRouteByMacAddress(macAddress,sender);
            System.out.println(route);
            return route;
        } else {
            //TODO rewrite getting the path between nodes of different networks
            PC senderPC = (PC) sender;

            if (!senderPC.getArpTable().containsKey(senderPC.getGateway())) {
                //Route from Start Vertex to Gateway
                Request arpRequest = new Request(senderPC, senderPC.getGateway());

                MacAddress recipientMacAddress = senderPC.sendArpRequest(arpRequest, senderPC);
                senderPC.addArpTableRow(senderPC.getGateway(), recipientMacAddress);


                List<IPathElement> route = null;
                route= senderPC.getRouteByMacAddress(recipientMacAddress,senderPC);

                //Route from Gateway to Terminal Vertex
                Request request = new Request(senderPC, recipient.getIp());
                List<IPathElement> routeFromGatewayToTerminalVertex = ((Router)route.get(route.size()-1)).
                        getRouteByRequest(request, null);
                routeFromGatewayToTerminalVertex.remove(0);

                route.addAll(routeFromGatewayToTerminalVertex);
                System.out.println(route);
                return route;
            }

            //System.out.println("Network convergence occurs");
            /*
            1.передаём пакет на маршрутизатор через gateway
            2.производим подготовку маршрутизаторов(динамическое изучение сети)
            3.используя таблицу маршрутизации находим передаём на следующий маршрутизатор
            или сразу в подсеть, если она подключена напрямую
            4.посылаем arp-запрос
            5.узнаём mac-адрес
            */
        }
        return null;
    }
}
