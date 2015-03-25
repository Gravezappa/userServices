package com.kblyumkin.userServices.services;

import com.kblyumkin.userServices.services.beans.User;
import com.kblyumkin.userServices.services.exceptions.UserFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface UserService {
    @WebMethod(action="#processUser")
    @WebResult(name = "response")
    User processUser(@WebParam(name="processUser") User user) throws UserFault;
}
