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

    void newUserSuccess();
    void navigateToMainScreen();
    void setUserEmail(String email);

    void loginError(String error);
    void newUserError(String error);
}
