package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IChannelLayerDevice;
import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.HashMap;
import java.util.Map;

public class Switch extends ActiveElement implements IChannelLayerDevice {

    /*
     Что нужно учесть?

        1.Только управляемые имеют ip
        2.Управляемые могут создавать vlan
        3.Должен быть быстрее чем маршрутизатор
        4.Таблица коммутации
            destination | next_hop
        5.destination не обязательно должен представлять собой адрес конечного устройства
        это также мб адрес промежуточного устройства, например, межсетевой шлюз.
        6.Количество портов от 4 до 48
        7.Топология:Только в виде дерева или звезды
     */


    //            Port     Mac
    private Map<Integer, MacAddress> switchingTable = new HashMap<>();


    @Override
    public MacAddress respondArpRequest(ActiveElement sender,IPV4 ip) {
        Map<PassiveElement, ActiveElement> connections = getConnections();

        for (ActiveElement connection : connections.values()) {
            if (connection == sender) continue;

            if (connection instanceof IChannelLayerDevice) {
                MacAddress response = ((IChannelLayerDevice) connection).respondArpRequest(this, ip);

                if (!(response == null))
                    return response;
            }
        }

        return null;
    }

    public void addSwitching() {

    }

    @Override
    public String getInfo() {
        return null;
    }

}
