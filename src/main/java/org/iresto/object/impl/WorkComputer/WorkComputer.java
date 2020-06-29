package org.iresto.object.impl.WorkComputer;


import org.iresto.object.AbstractWorkComputer;

public class WorkComputer extends AbstractWorkComputer {
    private int idInBaseOfData; // номер строки в БД, для редактирования данных

    public WorkComputer(String typePC) {
        super(typePC);
    }

    public WorkComputer(String typePC, String IDAmmyAdmin, String IDAnyDesk, String pswAmmyAdmin, String pswAnyDesk,
                        int idInBaseOfData) {
        super(typePC, IDAmmyAdmin, IDAnyDesk, pswAmmyAdmin, pswAnyDesk);
        this.idInBaseOfData=idInBaseOfData;
    }

    @Override
    public void setHostServer(String hostServer) {
        super.setHostServer(hostServer);
    }

    @Override
    public String getTypePC() {
        return super.getTypePC();
    }

    @Override
    public String getIDAmmyAdmin() {
        return super.getIDAmmyAdmin();
    }

    @Override
    public String getIDAnyDesk() {
        return super.getIDAnyDesk();
    }

    @Override
    public String getPswAmmyAdmin() {
        return super.getPswAmmyAdmin();
    }

    @Override
    public String getPswAnyDesk() {
        return super.getPswAnyDesk();
    }

    @Override
    public String getHostServer() {
        return super.getHostServer();
    }

    @Override
    public void setTypePC(String typePC) {
        super.setTypePC(typePC);
    }

    public int getIdInBaseOfData() {
        return idInBaseOfData;
    }

    @Override
    public void setIDAmmyAdmin(String IDAmmyAdmin) {
        super.setIDAmmyAdmin(IDAmmyAdmin);
    }

    @Override
    public void setIDAnyDesk(String IDAnyDesk) {
        super.setIDAnyDesk(IDAnyDesk);
    }

    @Override
    public void setPswAmmyAdmin(String pswAmmyAdmin) {
        super.setPswAmmyAdmin(pswAmmyAdmin);
    }

    @Override
    public void setPswAnyDesk(String pswAnyDesk) {
        super.setPswAnyDesk(pswAnyDesk);
    }
}
