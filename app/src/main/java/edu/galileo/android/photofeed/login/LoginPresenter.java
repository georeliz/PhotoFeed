package edu.galileo.android.photofeed.login;

import edu.galileo.android.photofeed.login.events.LoginEvent;

/**
 * Created by Lab1 on 28/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
