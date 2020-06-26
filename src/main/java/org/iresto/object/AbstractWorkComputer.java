package org.iresto.object;

import javafx.beans.property.SimpleStringProperty;

public abstract class AbstractWorkComputer {

    //private String typePC;
    private SimpleStringProperty typePC =new SimpleStringProperty();

    /* Тип рабочей станции, а именно ChainServer,Front
     * RMSServer, Kitchen, Arrivals и т.д.*/
   // private String IDAmmyAdmin;
    private SimpleStringProperty IDAmmyAdmin =new SimpleStringProperty();
    /* ID подключение по AmmyAdmin */
    //private String IDAnyDesk;
    private SimpleStringProperty IDAnyDesk =new SimpleStringProperty();
    /* ID подключение по AnyDesk */
    //private String PswAmmyAdmin;
    private SimpleStringProperty PswAmmyAdmin =new SimpleStringProperty();
    /* Пароль подключение по AmmyAdmin */
   // private String PswAnyDesk;
    private SimpleStringProperty PswAnyDesk =new SimpleStringProperty();
    /* Пароль подключение по AnyDesk */
    //private String hostServer;
    private SimpleStringProperty hostServer =new SimpleStringProperty();
    /*хост или внешний ip сервера iiko. Вместе с портом
     * например http:\\demo-stend-1.iiko.it:8080
     **/

    public AbstractWorkComputer(String typePC, String IDAmmyAdmin, String IDAnyDesk, String pswAmmyAdmin, String pswAnyDesk) {
        this.typePC = new SimpleStringProperty(typePC);
        this.IDAmmyAdmin = new SimpleStringProperty(IDAmmyAdmin);
        this.IDAnyDesk = new SimpleStringProperty(IDAnyDesk);
        PswAmmyAdmin = new SimpleStringProperty(pswAmmyAdmin);
        PswAnyDesk = new SimpleStringProperty(pswAnyDesk);
    }

    public AbstractWorkComputer(String typePC) {
        this.typePC.set(typePC);
    }

    public void setHostServer(String hostServer) {
        this.hostServer.set(hostServer);
    }

    public String getTypePC() {
        return typePC.get();
    }

    public String getIDAmmyAdmin() {
        return IDAmmyAdmin.get();
    }

    public String getIDAnyDesk() {
        return IDAnyDesk.get();
    }

    public String getPswAmmyAdmin() {
        return PswAmmyAdmin.get();
    }

    public String getPswAnyDesk() {
        return PswAnyDesk.get();
    }

    public String getHostServer() {
        return hostServer.get();
    }

    public void setTypePC(String typePC) {
        this.typePC.set(typePC);
    }

    public void setIDAmmyAdmin(String IDAmmyAdmin) {
        this.IDAmmyAdmin.set(IDAmmyAdmin);
    }

    public void setIDAnyDesk(String IDAnyDesk) {
        this.IDAnyDesk.set(IDAnyDesk);
    }

    public void setPswAmmyAdmin(String pswAmmyAdmin) {
        this.PswAmmyAdmin.set(pswAmmyAdmin);
    }

    public void setPswAnyDesk(String pswAnyDesk) {
        this.PswAnyDesk.set(pswAnyDesk);
    }
}
