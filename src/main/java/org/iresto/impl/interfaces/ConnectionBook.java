package org.iresto.impl.interfaces;


import org.iresto.object.AbstractClient;

public interface ConnectionBook {
    public void add(AbstractClient client);
    public void update(AbstractClient client);
    public void delete(AbstractClient client);
    /*Интерфейс для добаления удаления редактирования данных о клиенте*/
}
