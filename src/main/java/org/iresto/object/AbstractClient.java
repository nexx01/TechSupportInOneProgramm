package org.iresto.object;

public abstract class AbstractClient {
    private final String Brand;
    private final String legalEntity;
    private final String address;


    public AbstractClient(String brand, String legalEntity, String statusOfSupport) {
        Brand = brand;
        this.legalEntity = legalEntity;
        this.address = statusOfSupport;
    }




}
