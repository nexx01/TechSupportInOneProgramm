package org.iresto.object;

public class AbstractClientIIKO extends AbstractClient {
    public String kindOfLicense;
    public String statusOfSupport;

    public AbstractClientIIKO(int clientId, String brand, String legalEntity, String address) {
        super(clientId, brand, legalEntity, address);
    }

    public AbstractClientIIKO(int clientId, String brand, String legalEntity, String address, String kindOfLicense, String statusOfSupport) {
        super(clientId, brand, legalEntity, address);
        this.kindOfLicense = kindOfLicense;
        this.statusOfSupport = statusOfSupport;
    }

    @Override
    public String getBrand() {
        return super.getBrand();
    }

    @Override
    public String getLegalEntity() {
        return super.getLegalEntity();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    public String getKindOfLicense() {
        return kindOfLicense;
    }

    public void setKindOfLicense(String kindOfLicense) {
        this.kindOfLicense = kindOfLicense;
    }

    public String getStatusOfSupport() {
        return statusOfSupport;
    }

    public void setStatusOfSupport(String statusOfSupport) {
        this.statusOfSupport = statusOfSupport;
    }

    @Override
    public String toString() {
        return "AbstractClientIIKO{" +
                       "kindOfLicense='" + kindOfLicense + '\'' +
                       ", statusOfSupport='" + statusOfSupport + '\'' +
                       '}';
    }
}
