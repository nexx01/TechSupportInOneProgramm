package org.iresto.object.impl.clientIiko;


import org.iresto.object.AbstractClientIIKO;

public class ClientIiko extends AbstractClientIIKO {


    public ClientIiko(int clientId, String brand, String legalEntity, String address) {
        super(clientId, brand, legalEntity, address);
    }

    public ClientIiko(int clientId, String brand, String legalEntity, String address, String kindOfLicense, String statusOfSupport) {
        super(clientId, brand, legalEntity, address, kindOfLicense, statusOfSupport);
    }

    @Override
    public String toString() {
        return "ClientIiko{" +
                       "kindOfLicense='" + kindOfLicense + '\'' +
                       ", statusOfSupport='" + statusOfSupport + '\'' +
                       '}';
    }

}