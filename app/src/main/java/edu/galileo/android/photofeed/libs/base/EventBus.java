package edu.galileo.android.photofeed.libs.base;

/**
 * Created by Lab1 on 28/06/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
