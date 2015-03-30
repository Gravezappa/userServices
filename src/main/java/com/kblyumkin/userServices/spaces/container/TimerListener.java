package com.kblyumkin.userServices.spaces.container;

import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.userServices.services.beans.User;
import com.kblyumkin.userServices.spaces.writer.UserManager;
import org.openspaces.events.DynamicEventTemplate;
import org.openspaces.events.EventDriven;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.springframework.beans.factory.annotation.Autowired;

@EventDriven @Polling
public class TimerListener {
    private final static String TEMPLATE_CONDITION = "parentId is null AND creationTime < ?";
    private final static long TIME_TO_STORE        = 20_000l;

    @Autowired
    UserManager userManager;

    @DynamicEventTemplate
    SQLQuery<User> unprocessedExpiredData() {
        long expired = System.currentTimeMillis() - TIME_TO_STORE;
        SQLQuery<User> dynamicTemplate = new SQLQuery<>(User.class, TEMPLATE_CONDITION)
                .setParameter(1, expired);
        return dynamicTemplate;
    }

    @SpaceDataEvent
    public User eventListener(User event) {
        User lastEdited = userManager.getLastEdited(event);
        System.out.println("Last edited user was: " + lastEdited);
        if (!User.Status.REJECT.equals(lastEdited.getStatus())) {
            System.out.println(lastEdited + " is sending to JMS");
        }
        userManager.removeAllChilds(event);
        System.out.println(event + " removed");
        return null;
    }
}
