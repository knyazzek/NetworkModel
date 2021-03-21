/*package com.averin.networkModel.routeProviders;

import com.averin.networkModel.IChannelLayerDevice;
import com.averin.networkModel.IPathElement;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.Network;
import com.averin.networkModel.pathElements.active.ActiveElement;
import com.averin.networkModel.pathElements.active.PC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RipRouteProvider implements IRouteProvider {

    Network network;

    @Override
    public List<IPathElement> getRoute(int firstID, int secondID, Network net) {

        List<IPathElement> route = new ArrayList<>();

        ActiveElement sender = ...;
        ActiveElement recipient = ...;

        if (Arrays.equals(sender.getIP().getNetAddress(), recipient.getIP().getNetMask())) {
            MacAddress macAddress =
                    ((IChannelLayerDevice)sender).sendArpRequest(sender, recipient.getIP());

            System.out.println(macAddress.toString());

        } else {
            *//*
            1.передаём пакет на маршрутизатор через gateway
            2.производим подготовку маршрутизаторов(динамическое изучение сети)
            3.используя таблицу маршрутизации находим передаём на следующий маршрутизатор
            или сразу в подсеть, если она подключена напрямую
            4.посылаем arp-запрос
            5.узнаём mac-адрес
            *//*
        }
    }
}*/
