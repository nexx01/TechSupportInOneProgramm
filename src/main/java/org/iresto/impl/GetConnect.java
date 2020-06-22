package org.iresto.impl;


import org.iresto.impl.interfaces.Connect;

import java.io.IOException;

public class GetConnect implements Connect {

    @Override
    public void connectAnydesk(String loginAnydesk,String passwordAnyDesk) {
        try {
            /*ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd", "/C", "start C:\\\"Program Files (x86)\"\\AnyDesk\\anydesk.exe echo password / anydesk "
                                         + loginAnydesk + " --with-" + passwordAnyDesk);*/
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd", "/C", "echo "+passwordAnyDesk+"|C:\\\"Program Files (x86)\"\\AnyDesk\\anydesk.exe "+loginAnydesk+" --with-password");
            //echo Tm7SN?dm|"C:\AnyDesk\AnyDesk.exe" 425035387 --with-password
            Process process = processBuilder.start();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void connectAmmyAdmin(String loginAmmyAdmin, String passwordAmmyAdmin) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd", "/C", "C:\\Iresto\\AA_v3.exe -connect " + loginAmmyAdmin + " -password " + passwordAmmyAdmin);
            Process process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
