package com.kblyumkin.userServices.spaces.container;

import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.userServices.spaces.beans.User;
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
        long expired = System.currentTimeMillis() - 60 * 60000;
        SQLQuery<User> dynamicTemplate = new SQLQuery<>(User.class, "timestamp < " + expired);
        return dynamicTemplate;
    }

    @SpaceDataEvent
    public User eventListener(User event) {
        return event;
    }
}
