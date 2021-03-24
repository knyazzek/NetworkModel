package com.averin.networkModel.routeProviders;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.Network;
import com.averin.networkModel.pathElements.active.ActiveElement;
import com.averin.networkModel.pathElements.active.ArpDevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RipRouteProvider implements IRouteProvider {

    @Override
    public List<IPathElement> getRoute(IPV4 senderIP, IPV4 recipientIP, Network net) {

        List<IPathElement> route = new ArrayList<>();

        ActiveElement sender = net.findByIP(senderIP);
        ActiveElement recipient = net.findByIP(recipientIP);

        if (sender == null || recipient == null) {
            System.out.println("One or more network elements with the specified IP do not exist");
            return null;
        }

        if (Arrays.equals(sender.getIP().getNetAddress(), recipient.getIP().getNetAddress())) {
            MacAddress recipientMacAddress =
                    ((ArpDevice)sender).sendArpRequest(recipient.getIP());
            System.out.println(recipientMacAddress.toString());


        } else {
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
