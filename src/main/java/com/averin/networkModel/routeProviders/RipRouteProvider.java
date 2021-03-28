package com.averin.networkModel.routeProviders;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.Network;
import com.averin.networkModel.pathElements.active.ActiveElement;
import com.averin.networkModel.pathElements.active.IArpDevice;
import com.averin.networkModel.pathElements.active.PC;

import java.util.Arrays;
import java.util.List;

public class RipRouteProvider implements IRouteProvider {

    @Override
    public List<IPathElement> getRoute(IPv4 senderIP, IPv4 recipientIP, Network net) {
        ActiveElement sender = net.findByIp(senderIP);
        ActiveElement recipient = net.findByIp(recipientIP);

        if (sender == null || recipient == null) {
            System.out.println("One or more network elements with the specified IP do not exist");
            return null;
        }

        if (Arrays.equals(sender.getIp().getNetMask(), recipient.getIp().getNetMask())) {
            ArpRequest arpRequest = new ArpRequest((PC)sender, recipientIP);
            System.out.println(((PC)sender).sendArpRequest(arpRequest));
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


/*ArpDevice sender = (ArpDevice)net.findByIP(senderIP);
        ArpDevice recipient = (ArpDevice) net.findByIP(recipientIP);

        if (sender == null || recipient == null) {
            System.out.println("One or more network elements with the specified IP do not exist");
            return null;
        }

        if (Arrays.equals(sender.getIP().getNetAddress(), recipient.getIP().getNetAddress())) {
            MacAddress recipientMacAddress =
                    sender.sendArpRequest(recipient.getIP());
            System.out.println(recipientMacAddress);
            System.out.println(sender.getRouteByMacAddress(recipientMacAddress, sender));*/
