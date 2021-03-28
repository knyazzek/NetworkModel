package com.averin.networkModel.pathElements.active;

public class Router {
        /*
        Имеет белый и серый ip

        В некоторых случаях вместо роутера используется Прокси-сервер (Proxy) - компьютер,
        который так же имеет два сетевых интерфейса для внешней сети и для внутренней.

        Таблица маршрутизации
        destination | netmask | gateway | interface | metric

        Настройка таблица
          1.Статическая
          2.Динамическая
              a)RIP
                    Метрика вычисляется по количеству узлов
              b)OSPF
                    Вызвешенный граф

               ...

        Чаще всего имеет 4 интерфейса GigabitEthernet

        Возможность добавить встроенного firewall
        */
}
