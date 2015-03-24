package com.kblyumkin.userServices.services.impl;

import com.kblyumkin.userServices.services.beans.User;
import com.kblyumkin.userServices.services.UserService;
import com.kblyumkin.userServices.spaces.writer.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "com.kblyumkin.userServices.services.UserService",
        targetNamespace = "http://service.userServices.kblyumkin.com/users",
        serviceName = "UserService",
        portName = "UserServicePort",
        wsdlLocation = "UserService.wsdl")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDao;


    @Override
    public String processUser(User user) {
        if (User.Status.NEW.equals(user.getStatus())) {
            userDao.writeUser(user);
        } else {
            userDao.update(user);
        }
        return "ok";
    }
}
