package org.iresto.object;
import javafx.beans.property.SimpleStringProperty;

public class AbstractWebResourse {

    private SimpleStringProperty nameWebResource = new SimpleStringProperty("");
    private SimpleStringProperty webAddress = new SimpleStringProperty("");
    private SimpleStringProperty loginWebResource = new SimpleStringProperty("");
    private SimpleStringProperty webpassword = new SimpleStringProperty("");




    public AbstractWebResourse(String nameWebResource, String webAddress, String loginWebResource) {
        this.nameWebResource = new SimpleStringProperty(nameWebResource);
        this.webAddress =new SimpleStringProperty(webAddress);
        this.loginWebResource = new SimpleStringProperty(loginWebResource);
    }

    public void setNameWebResource(String nameWebResource) {
        this.nameWebResource.set(nameWebResource);
    }

    public void setWebAddress(String webAddress) {
        this.webAddress.set(webAddress);
    }

    public void setLoginWebResource(String loginWebResource) {
        this.loginWebResource.set(loginWebResource);
    }

    public void setWebpassword(String webpassword) {
        this.webpassword.set(webpassword);
    }

    public String getNameWebResource() {
        return nameWebResource.get();
    }

    public SimpleStringProperty nameWebResourceProperty() {
        return nameWebResource;
    }

    public String getWebAddress() {
        return webAddress.get();
    }

    public SimpleStringProperty webAddressProperty() {
        return webAddress;
    }

    public String getLoginWebResource() {
        return loginWebResource.get();
    }

    public SimpleStringProperty loginWebResourceProperty() {
        return loginWebResource;
    }

    public String getWebpassword() {
        return webpassword.get();
    }

    public SimpleStringProperty webpasswordProperty() {
        return webpassword;
    }
}
