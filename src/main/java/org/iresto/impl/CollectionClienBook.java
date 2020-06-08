package org.iresto.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iresto.impl.interfaces.ClientBook;
import org.iresto.object.AbstractClient;
import org.iresto.object.AbstractClientIIKO;
import org.iresto.object.impl.clientIiko.ClientIiko;

public class CollectionClienBook implements ClientBook {
    ObservableList<ClientIiko> clientIikoObservableList= FXCollections.observableArrayList();

    @Override
    public void add(AbstractClient client) {

    }

    @Override
    public void update(AbstractClient client) {

    }

    @Override
    public void delete(AbstractClient client) {

    }

    public ObservableList<ClientIiko> getClientIikoObservableList() {
        return clientIikoObservableList;
    }

    /*public  void fillTestDataClientIiko(){
        clientIikoObservableList.add(new ClientIiko(
                "Мир Пиццы", "ИП Чеснокова", "Павлово,Гвардейцев 18"));
        clientIikoObservableList.add(new ClientIiko(
                "Мир Пиццы", "ИП Захарова", "Дзержинск,Циолковского 19"));
        clientIikoObservableList.add(new ClientIiko(
                "Самурай", "ООО Венусто", "Нижний Новгород, Белинского 61"));

        clientIikoObservableList.get(0).setKindOfLicense("Lifetime");
        clientIikoObservableList.get(1).setKindOfLicense("Cloud Pro+");
        clientIikoObservableList.get(2).setKindOfLicense("Enterpise");
        clientIikoObservableList.get(0).setStatusOfSupport("Не на Тп");
        clientIikoObservableList.get(1).setStatusOfSupport("Только блокеры");
        clientIikoObservableList.get(2).setStatusOfSupport("На полной Тп");

    }*/
}
