package com.kblyumkin.userServices.spaces.writer;

import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import com.kblyumkin.userServices.services.beans.User;

import javax.annotation.Resource;

public class UserDAO {
    private final static String UPDATE_REJECT_REQUEST = "firstName = ? and lastName = ? and status = 'NEW'";
    private final static String REMOVE_REQUEST        = "parentId = ?";
    private final static String LAST_EDITED_REQUEST   = "parentId = ? order by creationTime desc";

    @Resource
    GigaSpace gigaspace;

    public UserDAO() {}

    public void writeUser(User user) {
        user.setCreationTime(System.currentTimeMillis());
        gigaspace.write(user);
    }

    public void update(User user) {
        User parentUser = gigaspace.read(new SQLQuery<>(User.class, UPDATE_REJECT_REQUEST)
                .setParameter(1, user.getFirstName())
                .setParameter(2, user.getLastName()));
        user.setParentId(parentUser.getId());
        System.out.println("Parent user found: " + parentUser);
        writeUser(user);
    }

    public void remove(User user) {
        gigaspace.takeMultiple(new SQLQuery<User>(User.class, REMOVE_REQUEST)
            .setParameter(1, user.getId()));
    }

    public User getEdited(User user) {
        User[] result = gigaspace.readMultiple(new SQLQuery<>(User.class, LAST_EDITED_REQUEST)
                .setParameter(1, user.getId()));
        if (result.length > 0) {
            return result[0];
        } else {
            return user;
        }

    }
}
