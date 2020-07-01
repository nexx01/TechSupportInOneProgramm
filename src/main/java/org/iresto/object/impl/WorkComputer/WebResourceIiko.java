package org.iresto.object.impl.WorkComputer;

import org.iresto.object.AbstractWebResourse;

public class WebResourceIiko extends AbstractWebResourse{
    private int idInDataBase;


    public WebResourceIiko(String nameWebResource, String webAddress,
                           String loginWebResource, int idInDataBase) {
        super(nameWebResource, webAddress, loginWebResource);
        this.idInDataBase=idInDataBase;
    }

    public int getIdInDataBase() {
        return idInDataBase;
    }
}
