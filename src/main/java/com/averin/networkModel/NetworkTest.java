package com.averin.networkModel;

import com.averin.networkModel.routeProviders.IRouteProvider;
import java.util.List;
import java.util.Scanner;

public class NetworkTest {

    List<Network> networkList;
    List<Class<? extends IRouteProvider>> providerList;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        outer:
        while (true) {
            System.out.print("Input:");

            String str = scanner.nextLine();
            String[] command = str.split(" ");

            switch (command[0]) {
                case ("route") :
                    //ConsoleCommand.route(command);
                    break;

                case ("help") :
                    ConsoleCommand.help();
                    break ;

                case ("stop") :
                    scanner.close();
                    System.out.println("Scanner was closed.");
                    break outer;

                default:
                    System.out.println(command[0] +
                            " is not recognized as a command. To view the list of commands, enter \"help\".");
                    break;
            }
        }
    }
}
