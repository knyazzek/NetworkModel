package com.averin.networkModel;

import com.averin.networkModel.routeProviders.IRouteProvider;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ConsoleCommand {

    public static void route(String[] command, Network net, IRouteProvider routeProvider) {
        switch (command.length) {
            case 5 :
                if (IPV4.isIPV4(command[3]) && IPV4.isIPV4(command[4])) {
                    System.out.println("route network, provider, id1, id2");
                } else {
                    System.out.println("Invalid parameters.");
                }
                break;

            case 6 :
                if (command[1].equals("-ip")) {
                    if (IPV4.isIPV4(command[4]) && IPV4.isIPV4(command[5])) {
                        System.out.println("route –ip network, provider, ip1, ip2");

                        IPV4 senderIP = new IPV4(command[4]);
                        IPV4 recipientIP = new IPV4(command[5]);

                        routeProvider.getRoute(senderIP, recipientIP, net);
                    } else {
                        System.out.println("Invalid parameters.");
                    }
                } else {
                    System.out.println("Invalid flag for the command ");
                }
                break;

            default:
                System.out.println("Invalid number of parameters.");
                break;
        }
    }

    public static void help() {

        Method[] methods = ConsoleCommand.class.getMethods();

        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                System.out.println("    -" + method.getName());
            }
        }
    }
}
