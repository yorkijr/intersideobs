/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interside.accessdoor.ws;

import com.interside.accessdoor.business.ProcessFile;
import com.interside.accessdoor.domain.DisplayAccessDoorRS;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

/**
 *
 * @author zenta
 */
@WebService(serviceName = "DisplayAccessDoorWS")
public class DisplayAccessDoorWS {

    /**
     * Web service operation
     * @return DisplayAccessDoorRS
     */
    @WebMethod(operationName = "getAccessDoor")
    @SOAPBinding(parameterStyle=ParameterStyle.BARE)
    public DisplayAccessDoorRS getAccessDoor() {
        return ProcessFile.process();
    }

}
