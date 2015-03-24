package com.kblyumkin.userServices.spaces.writer;

import org.openspaces.core.GigaSpace;
import com.kblyumkin.userServices.services.User;

import javax.annotation.Resource;

public class UserWriter {
    @Resource
    GigaSpace gigaspace;

    public UserWriter() {}

    public void writeUser(User user) {
        user.setCreationTime(System.currentTimeMillis());
        gigaspace.write(user);
    }
}
