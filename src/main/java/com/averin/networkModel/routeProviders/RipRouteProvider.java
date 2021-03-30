package com.averin.networkModel.routeProviders;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.Network;
import com.averin.networkModel.pathElements.active.L3Device;
import com.averin.networkModel.pathElements.active.PC;
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

        if (Arrays.equals(sender.getIp().getNetMask(), recipient.getIp().getNetMask())) {
            ArpRequest arpRequest = new ArpRequest((PC)sender, recipientIP);
            MacAddress macAddress = ((PC)sender).sendArpRequest(arpRequest);

            System.out.println(macAddress);
            List<IPathElement> route = ((PC)sender).getRouteByMacAddress(macAddress,sender);
            System.out.println(route);
        } else {
            System.out.println("Network convergence occurs");

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
