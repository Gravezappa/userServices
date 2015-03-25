package com.kblyumkin.userServices.services.impl;

import com.kblyumkin.userServices.services.beans.User;
import com.kblyumkin.userServices.services.UserService;
import com.kblyumkin.userServices.services.exceptions.UserFault;
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
    public User processUser(User user) throws UserFault {
        if (User.Status.NEW.equals(user.getStatus())) {
            if (userDao.isUserExists(user)) {
                throw new UserFault(user + " already exists");
            }
            userDao.writeUser(user);
        } else {
            if (!userDao.isUserExists(user)) {
                throw new UserFault(user + " not exists");
            }
            userDao.update(user);
        }
        return user;
    }
}
