package org.iresto.object;

public abstract class AbstractClient {
    private  int clientId;
    private  String Brand;
    private  String legalEntity;
    private  String address;


    public AbstractClient(int clientId, String brand, String legalEntity, String address) {
        this.clientId = clientId;
        Brand = brand;
        this.legalEntity = legalEntity;
        this.address = address;
    }

    public String getBrand() {
        return Brand;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public String getAddress() {
        return address;
    }

    public int getClientId() {
        return clientId;
    }
}
