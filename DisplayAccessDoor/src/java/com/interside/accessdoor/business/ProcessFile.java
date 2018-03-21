/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interside.accessdoor.business;
import com.interside.accessdoor.business.util.PropertiesUtil;
import com.interside.accessdoor.domain.Access;
import com.interside.accessdoor.domain.DisplayAccessDoorRS;
import com.interside.accessdoor.domain.ServiceStatus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Carelys Scarpati
 */
public class ProcessFile {
    private static final Logger logger = Logger.getLogger(ProcessFile.class);
    public static DisplayAccessDoorRS process() {
        DisplayAccessDoorRS displayAccessDoorRS = new DisplayAccessDoorRS();
        StringBuilder file = new StringBuilder();
        String filename =PropertiesUtil.getProperties("file.path");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))))) {
            int valor = br.read();
            while (valor != -1) {
                file.append((char) valor);
                valor = br.read();
            }
           return readFile(file);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setCode(-1);
            serviceStatus.setMessage(e.getMessage());
            displayAccessDoorRS.setServiceStatus(serviceStatus);
        }
        return displayAccessDoorRS;

    }

    private static DisplayAccessDoorRS readFile(StringBuilder file) {
        DisplayAccessDoorRS displayAccessDoorRS = new DisplayAccessDoorRS();
        String door = "Puerta";
        
        if (file.toString().trim().isEmpty()) {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setCode(-2);
            serviceStatus.setMessage("El archivo se encuentra vacio");
            displayAccessDoorRS.setServiceStatus(serviceStatus);
            return displayAccessDoorRS;
        }
        
        String[] fileProcess = file.toString().trim().split("\n");
        String lastLine = fileProcess[fileProcess.length - 1];
        if (lastLine.contains(door)) {
            Access access = new Access();
            access.setDate(new Date(lastLine.substring(lastLine.length() - 20, lastLine.length())));
            access.setDoor(lastLine.substring(lastLine.indexOf(door), lastLine.length() - 20));
            access.setState(lastLine.substring(lastLine.indexOf("Acces"), lastLine.indexOf(door)).trim());
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setCode(0);
            serviceStatus.setMessage("successfull");
            displayAccessDoorRS.setAccess(access);
            displayAccessDoorRS.setServiceStatus(serviceStatus);

            return displayAccessDoorRS;
        } else {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setCode(-2);
            serviceStatus.setMessage("la ultima linea del archivo no contiene la informacion requerida");
            displayAccessDoorRS.setServiceStatus(serviceStatus);
            return displayAccessDoorRS;
        }
    }

}
