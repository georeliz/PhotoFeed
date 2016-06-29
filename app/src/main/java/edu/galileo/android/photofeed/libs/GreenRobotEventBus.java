package edu.galileo.android.photofeed.libs;

import edu.galileo.android.photofeed.libs.base.EventBus;

/**
 * Created by Lab1 on 28/06/2016.
 */
public class GreenRobotEventBus implements EventBus {

    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);

    }

    @Override
    public void post(Object event) {
        eventBus.post(event);

    }
}
