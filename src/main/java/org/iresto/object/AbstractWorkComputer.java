package org.iresto.object;

public abstract class AbstractWorkComputer {

    private String typePC;
    /* Тип рабочей станции, а именно ChainServer,Front
     * RMSServer, Kitchen, Arrivals и т.д.*/
    private String IDAmmyAdmin;
    /* ID подключение по AmmyAdmin */
    private String IDAnyDesk;
    /* ID подключение по AnyDesk */
    private String PswAmmyAdmin;
    /* Пароль подключение по AmmyAdmin */
    private String PswAnyDesk;
    /* Пароль подключение по AnyDesk */
    private String hostServer;
    /*хост или внешний ip сервера iiko. Вместе с портом
     * например http:\\demo-stend-1.iiko.it:8080
     **/

    public AbstractWorkComputer(String typePC, String IDAmmyAdmin, String IDAnyDesk, String pswAmmyAdmin, String pswAnyDesk) {
        this.typePC = typePC;
        this.IDAmmyAdmin = IDAmmyAdmin;
        this.IDAnyDesk = IDAnyDesk;
        PswAmmyAdmin = pswAmmyAdmin;
        PswAnyDesk = pswAnyDesk;
    }

    public AbstractWorkComputer(String typePC) {
        this.typePC = typePC;
    }

    public void setHostServer(String hostServer) {
        this.hostServer = hostServer;
    }

    public String getTypePC() {
        return typePC;
    }

    public String getIDAmmyAdmin() {
        return IDAmmyAdmin;
    }

    public String getIDAnyDesk() {
        return IDAnyDesk;
    }

    public String getPswAmmyAdmin() {
        return PswAmmyAdmin;
    }

    public String getPswAnyDesk() {
        return PswAnyDesk;
    }

    public String getHostServer() {
        return hostServer;
    }

    public void setTypePC(String typePC) {
        this.typePC = typePC;
    }

    public void setIDAmmyAdmin(String IDAmmyAdmin) {
        this.IDAmmyAdmin = IDAmmyAdmin;
    }

    public void setIDAnyDesk(String IDAnyDesk) {
        this.IDAnyDesk = IDAnyDesk;
    }

    public void setPswAmmyAdmin(String pswAmmyAdmin) {
        PswAmmyAdmin = pswAmmyAdmin;
    }

    public void setPswAnyDesk(String pswAnyDesk) {
        PswAnyDesk = pswAnyDesk;
    }
}
