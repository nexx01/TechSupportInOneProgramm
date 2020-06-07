package org.iresto.object.impl.clientIiko;


import org.iresto.object.AbstractClientIIKO;

public class ClientIiko extends AbstractClientIIKO {

    public ClientIiko(String brand, String legalEntity, String statusOfSupport) {
        super(brand, legalEntity, statusOfSupport);
    }

    public ClientIiko(String brand, String legalEntity, String address, String kindOfLicense, String statusOfSupport) {
        super(brand, legalEntity, address, kindOfLicense, statusOfSupport);
    }

    @Override
    public String toString() {
        return "ClientIiko{" +
                       "kindOfLicense='" + kindOfLicense + '\'' +
                       ", statusOfSupport='" + statusOfSupport + '\'' +
                       '}';
    }

}