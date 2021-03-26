package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Switch extends Hub {
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

    private int maxConnectionsCount = 8;
    //              Mac     Port
    private Map<MacAddress,PassiveElement> switchingTable = new HashMap<>();

    @Override
    public MacAddress respondArpRequest(ArpDevice sender, IPV4 ip) {
        return respondArpRequest(sender, sender, ip);
    }

    //TODO make normal inheritance for respondArpRequest() in Switch
    public MacAddress respondArpRequest(ArpDevice sender, ArpDevice intermediateDevice, IPV4 ip) {
        if (!switchingTable.containsKey(sender.getMacAddress())) {
            switchingTable.put(sender.getMacAddress(),
                    getPassiveElementByIntermediateDevice(intermediateDevice));
        }

        for (PassiveElement connection : this.getConnections()) {
            ActiveElement adjacentActiveElement = connection.getConnection(this);

            if (adjacentActiveElement == intermediateDevice) continue;

            if (adjacentActiveElement instanceof PC) {
                MacAddress response = ((PC) adjacentActiveElement).
                        respondArpRequest(sender, ip);
                if (response != null) {
                    switchingTable.put(response, connection);
                    return response;
                }
            } else if (adjacentActiveElement instanceof Hub) {
                MacAddress response = ((Hub) adjacentActiveElement).
                        respondArpRequest(sender,this, ip);
                if (response != null) {
                    switchingTable.put(response, connection);
                    return response;
                }
            }
        }
        return null;
    }

    private PassiveElement getPassiveElementByIntermediateDevice(ActiveElement activeElement) {
        for (PassiveElement passiveElement : getConnections()) {
            if (passiveElement.getConnection(this) == activeElement)
                return passiveElement;
        }
        return null;
    }

    @Override
    public List<IPathElement> getRouteByMacAddress(MacAddress macAddress, ArpDevice sender) {
        if (switchingTable.containsKey(macAddress)) {
            PassiveElement passiveElement = switchingTable.get(macAddress);
            List<IPathElement> route = ((ArpDevice)passiveElement.getConnection(this)).
                    getRouteByMacAddress(macAddress, this);

            route.add(0,this);
            route.add(1,passiveElement);
            return route;
        }
        return super.getRouteByMacAddress(macAddress, sender);
    }

    @Override
    public void addConnection(PassiveElement passiveElement) {
        if (getConnections().size() < maxConnectionsCount) {
            super.addConnection(passiveElement);
        } else {
            System.out.println("The device has no free ports.");
        }
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public String toString() {
        return "Switch ";
    }
}
