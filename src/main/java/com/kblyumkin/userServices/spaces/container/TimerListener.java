package com.kblyumkin.userServices.spaces.container;

import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.userServices.services.beans.User;
import com.kblyumkin.userServices.spaces.writer.UserDAO;
import org.openspaces.events.DynamicEventTemplate;
import org.openspaces.events.EventDriven;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.springframework.beans.factory.annotation.Autowired;

@EventDriven @Polling
public class TimerListener {
    @Autowired
    UserDAO userDao;

    @DynamicEventTemplate
    SQLQuery<User> unprocessedExpiredData() {
        long expired = System.currentTimeMillis() - 10000;
        SQLQuery<User> dynamicTemplate = new SQLQuery<User>(User.class, "parentId is null AND creationTime < ?")
                .setParameter(1, expired);
        return dynamicTemplate;
    }

    @SpaceDataEvent
    public User eventListener(User event) {
        User lastEdited = userDao.getEdited(event);
        System.out.println("Last edited user was: " + lastEdited);
        if (!User.Status.REJECT.equals(lastEdited.getStatus())) {
            System.out.println(lastEdited + "is sending to JMS");
        }
        userDao.remove(event);
        System.out.println("User " + event + " removed");
        return null;
    }
}
