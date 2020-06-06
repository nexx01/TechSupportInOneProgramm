package org.iresto.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iresto.impl.interfaces.ClientBook;
import org.iresto.object.AbstractClient;
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

    public void fillTestDataClientIiko(){
        clientIikoObservableList.add(new ClientIiko(
                "Мир Пиццы", "ИП Чеснокова", "Павлово,Гвардейцев 18"));
        clientIikoObservableList.add(new ClientIiko(
                "Мир Пиццы", "ИП Захарова", "Дзержинск,Циолковского 19"));
        clientIikoObservableList.add(new ClientIiko(
                "Самурай", "ООО Венусто", "Нижний Новгород, Белинского 61"));
    }
}
