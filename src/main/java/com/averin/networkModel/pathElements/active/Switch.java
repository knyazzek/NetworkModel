package com.averin.networkModel.pathElements.active;

import com.averin.networkModel.Request;
import com.averin.networkModel.MacAddress;
import com.averin.networkModel.pathElements.IPathElement;
import com.averin.networkModel.pathElements.passive.PassiveElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Switch extends ActiveElement implements IPathElement {
    private final int MAX_CONNECTIONS_COUNT = 4;
    private Map<MacAddress, IPathElement> switchingTable = new HashMap<>();

    @Override
    public MacAddress sendArpRequest(Request arpRequest, IPathElement lastSender) {
        if (!switchingTable.containsKey(arpRequest.getSenderMacAddress())) {
            switchingTable.put(arpRequest.getSenderMacAddress(), lastSender);
        }

        MacAddress macAddress = super.sendArpRequest(arpRequest, lastSender);
        return macAddress;
    }

    @Override
    public List<IPathElement> getRouteByMacAddress(MacAddress recipientMacAddress, IPathElement sender) {
        if (switchingTable.containsKey(recipientMacAddress)) {
            PassiveElement gateway = (PassiveElement)switchingTable.get(recipientMacAddress);
            List<IPathElement> route = gateway.sendAll(recipientMacAddress, this);
            route.add(0, this);
            return route;
        }

        return super.getRouteByMacAddress(recipientMacAddress, sender);
    }

    public void addSwitching(MacAddress macAddress, IPathElement pathElement) {
        switchingTable.put(macAddress, pathElement);
    }

    public Map<MacAddress, IPathElement> getSwitchingTable() {
        return switchingTable;
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