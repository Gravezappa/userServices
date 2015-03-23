package com.kblyumkin.userServices.services.impl;

import com.kblyumkin.userServices.services.User;
import com.kblyumkin.userServices.services.UserService;
import com.kblyumkin.userServices.spaces.writer.UserWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "com.kblyumkin.userServices.services.UserService",
        targetNamespace = "http://service.userServices.kblyumkin.com/users",
        serviceName = "UserService",
        portName = "UserServicePort",
        wsdlLocation = "UserService.wsdl")
public class UserServiceImpl implements UserService {
    @Autowired
    UserWriter writer;


    @Override
    public String processUser(User user) {
    writer.writeUser(user);

        return "ok";
    }
}
