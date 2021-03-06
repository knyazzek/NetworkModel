package com.averin.networkModel;

import com.averin.networkModel.routeProviders.IRouteProvider;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ConsoleCommand {

    public static void route(String[] command, Network net, IRouteProvider routeProvider) {
        switch (command.length) {
            case 5 :
                if (IPv4.isNodeAddress(command[3]) && IPv4.isNodeAddress(command[4])) {
                    System.out.println("route network, provider, id1, id2");
                } else {
                    System.out.println("Invalid parameters.");
                }
                break;

            case 6 :
                if (command[1].equals("-ip")) {
                    if (IPv4.isNodeAddress(command[4]) && IPv4.isNodeAddress(command[5])) {
                        IPv4 senderIP = new IPv4(command[4]);
                        IPv4 recipientIP = new IPv4(command[5]);

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

    public static void exit() {
        System.out.println("Program exit");
        System.exit(0);
    }
}
