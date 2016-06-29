package edu.galileo.android.photofeed.login;

/**
 * Created by Lab1 on 28/06/2016.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
}
