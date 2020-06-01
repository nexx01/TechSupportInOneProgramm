package org.iresto.object;

public abstract class AbstractClient {
    private String Brand;
    private String legalEntity;
    private String statusOfSupport;

    public AbstractClient(String brand, String legalEntity, String statusOfSupport) {
        Brand = brand;
        this.legalEntity = legalEntity;
        this.statusOfSupport = statusOfSupport;
    }




}
