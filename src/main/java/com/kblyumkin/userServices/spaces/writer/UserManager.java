package com.kblyumkin.userServices.spaces.writer;

import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.userServices.services.beans.User;
import org.apache.commons.lang3.ObjectUtils;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;

import static org.openspaces.extensions.QueryExtension.maxEntry;

public class UserManager {
    private final static String PARENT_USER_REQUEST   = "firstName = ? and lastName = ? and status = 'NEW'";
    private final static String ALL_CHILDREN_REQUEST  = "parentId = ?";

    @GigaSpaceContext
    private GigaSpace gigaspace;

    public void write(User user) {
        gigaspace.write(user);
    }

    public boolean isExists(User user) {
        return gigaspace.read(new SQLQuery<User>(User.class, PARENT_USER_REQUEST)
                .setParameter(1, user.getFirstName())
                .setParameter(2, user.getLastName())) != null;
    }

    public void writeChild(User user) {
        User parentUser = gigaspace.read(new SQLQuery<>(User.class, PARENT_USER_REQUEST)
                .setParameter(1, user.getFirstName())
                .setParameter(2, user.getLastName()));
        user.setParentId(parentUser.getId());
        write(user);
    }

    public void removeAllChilds(User user) {
        gigaspace.takeMultiple(new SQLQuery<User>(User.class, ALL_CHILDREN_REQUEST)
                .setParameter(1, user.getId()));
    }

    public User getLastEdited(User user) {
        return ObjectUtils.firstNonNull(maxEntry(gigaspace,
                new SQLQuery<User>(User.class, ALL_CHILDREN_REQUEST).setParameter(1, user.getId()),
                "creationTime"), user);
    }
}
