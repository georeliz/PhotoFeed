package edu.galileo.android.photofeed.login.ui;

/**
 * Created by Lab1 on 28/06/2016.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

    void setUserEmail(String email);



}
