package org.iresto.impl;


import org.iresto.impl.interfaces.Connect;

import java.io.IOException;

public class GetConnect implements Connect {
    private String passwordAnyDesk;
    private String passwordAmmyAdmin;
    private String loginAnydesk;
    private String loginAmmyAdmin;


    @Override
    public void connectAnydesk() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd", "/C", "start C:\\\"Program Files (x86)\"\\AnyDesk\\anydesk.exe echo password / anydesk "
                                         + loginAnydesk + " --with-" + passwordAnyDesk);
            Process process = processBuilder.start();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void connectAmmyAdmin() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd", "/C", "C:\\Iresto\\AA_v3.exe -connect " + loginAmmyAdmin + " -password " + passwordAmmyAdmin);
            Process process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
