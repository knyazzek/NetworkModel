package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.ArpRequest;
import com.averin.networkModel.IPv4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import java.util.HashMap;
import java.util.Map;

public class Switch extends ActiveElement implements IArpDevice{
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

    private final int MAX_CONNECTIONS_COUNT = 4;
    private Map<MacAddress, IPathElement> switchingTable = new HashMap<>();

    public Switch(IPv4 ip, MacAddress macAddress) {
        super(ip, macAddress);
    }

    @Override
    public MacAddress sendArpRequest(ArpRequest arpRequest, IPathElement lastSender) {
        if (!switchingTable.containsKey(arpRequest.getSenderMacAddress())) {
            switchingTable.put(arpRequest.getSenderMacAddress(), lastSender);
        }
        MacAddress macAddress = IArpDevice.super.sendArpRequest(arpRequest, this);
        System.out.println(switchingTable);
        return macAddress;
    }

    public void addSwitching(MacAddress macAddress, IPathElement pathElement) {
        switchingTable.put(macAddress, pathElement);
    }

    @Override
    public String getInfo() {
        return "";
    }

    @Override
    public String toString() {
        return "Switch";
    }
}