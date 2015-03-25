package com.kblyumkin.userServices.services.exceptions;

import javax.xml.ws.WebFault;

@WebFault()
public class UserFault extends Exception {
    public UserFault() {
        super();
    }

    public UserFault(String message) {
        super(message);
    }

    public UserFault(String message, Throwable cause) {
        super(message, cause);
    }

}
