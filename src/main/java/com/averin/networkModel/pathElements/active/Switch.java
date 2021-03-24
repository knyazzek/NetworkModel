package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.IPV4;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.HashMap;
import java.util.Map;

public class Switch extends ArpDevice {
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
    private Map<PassiveElement, Integer> connectionsTable = new HashMap<>();
    //              Mac     Port
    private Map<MacAddress,Integer> switchingTable = new HashMap<>();

    @Override
    public MacAddress respondArpRequest(ArpDevice sender, IPV4 ip) {
        return respondArpRequest(sender, sender, ip);
    }

    public MacAddress respondArpRequest(ArpDevice sender, ArpDevice intermediateDevice, IPV4 ip) {
        if (!switchingTable.containsKey(sender.getMacAddress())) {
            switchingTable.put(sender.getMacAddress(), getPortByIntermediateDevice(intermediateDevice));
        }

        for (PassiveElement connection : this.getConnections()) {
            ActiveElement adjacentActiveElement = connection.getConnection(this);

            if (adjacentActiveElement == intermediateDevice) continue;

            if (adjacentActiveElement instanceof PC) {
                MacAddress response = ((PC) adjacentActiveElement).
                        respondArpRequest(sender, ip);
                if (response != null) {
                    switchingTable.put(response,
                            getPortByIntermediateDevice(adjacentActiveElement));
                    System.out.println(switchingTable);
                    return response;
                }
            } else if (adjacentActiveElement instanceof Switch) {
                MacAddress response = ((Switch) adjacentActiveElement).
                        respondArpRequest(sender,this, ip);
                if (response != null) {
                    switchingTable.put(response,
                            getPortByIntermediateDevice(adjacentActiveElement));
                    System.out.println(switchingTable);
                    return response;
                }
            }
        }
        return null;
    }

    //TODO: make an adequate getPortByIntermediateDevice() method
    private int getPortByIntermediateDevice(ActiveElement activeElement) {
        for (PassiveElement passiveElement : getConnections()) {
            if (passiveElement.getConnection(this) == activeElement) {
                for (Map.Entry connectionsTableRow : connectionsTable.entrySet()){
                    if (connectionsTableRow.getKey() == passiveElement)
                        return (int) connectionsTableRow.getValue();
                }
            }
        }
        return -1;
    }

    @Override
    public void addConnection(PassiveElement passiveElement) {
        if (getConnections().size() < maxConnectionsCount) {
            connectionsTable.put(passiveElement, getConnections().size() + 1);
            super.addConnection(passiveElement);
        } else {
            System.out.println("The device has no free ports.");
        }
    }

    @Override
    public String getInfo() {
        return null;
    }
}
