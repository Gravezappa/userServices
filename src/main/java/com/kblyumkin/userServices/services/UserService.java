package com.kblyumkin.userServices.services;

import com.kblyumkin.userServices.services.beans.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface UserService {
    @WebMethod(action="#processUser")
    String processUser(@WebParam(name="processUser") User user);
}
