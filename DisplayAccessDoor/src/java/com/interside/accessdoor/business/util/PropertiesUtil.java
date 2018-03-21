/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interside.accessdoor.business.util;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
/**
 *
 * @author zenta
 */
public class PropertiesUtil {
    private static final Logger logger = Logger.getLogger(PropertiesUtil.class);
 
   private PropertiesUtil()
   {}

    public static String getProperties(String value) {
        try {
            Properties prop= new Properties(); 
            FileReader rd= new FileReader("\\DisplayAccessDoor\\config\\DisplayAccessDoor.properties");
            prop.load(rd);
            return prop.getProperty(value);
            
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
       

    }
    


}
