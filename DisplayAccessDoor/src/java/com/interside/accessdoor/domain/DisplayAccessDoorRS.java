/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interside.accessdoor.domain;

import java.io.Serializable;

/**
 *
 * @author zenta
 */
public class DisplayAccessDoorRS implements Serializable {

    private static final long serialVersionUID = 1L;
    private Access access;
    private ServiceStatus serviceStatus;

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

}
