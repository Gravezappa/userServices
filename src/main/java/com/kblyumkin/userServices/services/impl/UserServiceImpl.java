package com.kblyumkin.userServices.services.impl;

import com.kblyumkin.userServices.services.beans.User;
import com.kblyumkin.userServices.services.UserService;
import com.kblyumkin.userServices.services.exceptions.UserFault;
import com.kblyumkin.userServices.spaces.writer.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "com.kblyumkin.userServices.services.UserService",
        targetNamespace = "http://service.userServices.kblyumkin.com/users",
        serviceName = "UserService",
        portName = "UserServicePort",
        wsdlLocation = "UserService.wsdl")
public class UserServiceImpl implements UserService {
    @Autowired
    UserManager userManager;

    @Override
    public User processUser(User user) throws UserFault {
        user.setCreationTime(System.currentTimeMillis());
        if (User.Status.NEW.equals(user.getStatus())) {
            if (userManager.isExists(user)) {
                throw new UserFault(user + " already exists");
            }
            userManager.write(user);
        } else {
            if (!userManager.isExists(user)) {
                throw new UserFault(user + " not exists");
            }
            userManager.writeChild(user);
        }
        return user;
    }
}
