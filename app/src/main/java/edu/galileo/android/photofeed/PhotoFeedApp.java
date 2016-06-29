package edu.galileo.android.photofeed;

import android.app.Application;

import com.firebase.client.Firebase;

import edu.galileo.android.photofeed.domain.di.DomainModule;
import edu.galileo.android.photofeed.libs.di.LibsModule;
import edu.galileo.android.photofeed.login.di.DaggerLoginComponent;
import edu.galileo.android.photofeed.login.di.LoginComponent;
import edu.galileo.android.photofeed.login.di.LoginModule;
import edu.galileo.android.photofeed.login.ui.LoginView;

/**
 * Created by Lab1 on 28/06/2016.
 */
public class PhotoFeedApp extends Application {
    private  final static  String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";
    private final static String FIREBASE_URL = "https://edx-photo-fedd.firebaseIO.com/";


    private DomainModule domainModule;
    private  PhotoFeedAppModule photoFeedAppModule;
    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initModules() {
        domainModule = new DomainModule(FIREBASE_URL);
        photoFeedAppModule = new PhotoFeedAppModule(this);

    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    public  String getEmailKey() {
        return EMAIL_KEY;
    }

    public  String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

 public LoginComponent getLoginComponent(LoginView view){
       return DaggerLoginComponent
               .builder()
               .photoFeedAppModule(photoFeedAppModule)
               .domainModule(domainModule)
               .libsModule(new LibsModule(null))
               .loginModule(new LoginModule(view))
               .build();
   }
}
