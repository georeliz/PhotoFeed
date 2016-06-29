package edu.galileo.android.photofeed.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.photofeed.main.ui.MainActivity;
import edu.galileo.android.photofeed.PhotoFeedApp;
import edu.galileo.android.photofeed.R;
import edu.galileo.android.photofeed.login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {



    @Bind(R.id.editTxtEmail)
    EditText editTxtEmail;
    @Bind(R.id.editTxtPassword)
    EditText editTxtPassword;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    private PhotoFeedApp app;
    @Inject
    LoginPresenter loginPresenter;
    @Inject
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        app = (PhotoFeedApp) getApplication();
        setupInjection();
        loginPresenter.onCreate();
        loginPresenter.validateLogin(null,null);

    }

    private void setupInjection() {
        app.getLoginComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();

    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);


    }
    @Override
    @OnClick(R.id.btnSignup)
    public void handleSignUp() {
        //startActivity(new Intent(this, SignUpActivity.class));
        loginPresenter.registerNewUser(editTxtEmail.getText().toString(), editTxtPassword.getText().toString());
    }
    @Override
    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        loginPresenter.validateLogin(editTxtEmail.getText().toString(), editTxtPassword.getText().toString());
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer,R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void setUserEmail(String email) {
        if (email != null){

            sharedPreferences.edit().putString(app.getEmailKey(), email).commit();
        }


    }

    @Override
    public void loginError(String error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        editTxtPassword.setError(msgError);
    }

    @Override
    public void newUserError(String error) {
        editTxtPassword.setText("");;
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        editTxtPassword.setError(msgError);

    }


    private void setInputs(boolean enabled){
        editTxtEmail.setEnabled(enabled);
        editTxtPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
    }
}
