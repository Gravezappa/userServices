package com.kblyumkin.userServices.spaces.container;

import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.userServices.services.User;
import org.openspaces.core.GigaSpace;
import org.openspaces.events.DynamicEventTemplate;
import org.openspaces.events.EventDriven;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.springframework.beans.factory.annotation.Autowired;

@EventDriven @Polling
public class TimerListener {
    @Autowired
    GigaSpace space;

    @DynamicEventTemplate
    SQLQuery<User> unprocessedExpiredData() {
        long expired = System.currentTimeMillis() - 10000;
        SQLQuery<User> dynamicTemplate = new SQLQuery<User>(User.class, "creationTime < ?")
                .setParameter(1, expired);
        return dynamicTemplate;
    }

    @SpaceDataEvent
    public User eventListener(User event) {
        System.out.println("User " + event + " removed");
        return null;
    }
}
