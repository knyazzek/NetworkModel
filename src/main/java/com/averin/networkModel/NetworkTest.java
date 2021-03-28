package com.averin.networkModel;

import com.averin.networkModel.routeProviders.IRouteProvider;
import com.averin.networkModel.routeProviders.RipRouteProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkTest {
    List<Network> networkList = new ArrayList<>();
    List<Class<? extends IRouteProvider>> providerList = new ArrayList<>();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        outer:
        while (true) {
            System.out.print("Input:");
            String str = scanner.nextLine();
            String[] command = str.split(" ");

            switch (command[0]) {
                case ("route") :
                    //TODO Don't forget to do it normally
                        ConsoleCommand.route(command, networkList.get(0), new RipRouteProvider());
                    break;

                case ("help") :
                    ConsoleCommand.help();
                    break ;

                case ("exit") :
                    ConsoleCommand.exit();
                    break;

                default:
                    System.out.println(command[0] +
                            " is not recognized as a command. " +
                            "To view the list of commands, enter \"help\".");
                    break;
            }
        }
    }
}
