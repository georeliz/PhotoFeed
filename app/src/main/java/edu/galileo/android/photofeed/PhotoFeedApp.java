package edu.galileo.android.photofeed;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.firebase.client.Firebase;

import edu.galileo.android.photofeed.domain.di.DomainModule;
import edu.galileo.android.photofeed.libs.di.LibsModule;
import edu.galileo.android.photofeed.login.di.DaggerLoginComponent;
import edu.galileo.android.photofeed.login.di.LoginComponent;
import edu.galileo.android.photofeed.login.di.LoginModule;
import edu.galileo.android.photofeed.login.ui.LoginView;
import edu.galileo.android.photofeed.main.di.DaggerMainComponent;
import edu.galileo.android.photofeed.main.di.MainComponent;
import edu.galileo.android.photofeed.main.di.MainModule;
import edu.galileo.android.photofeed.main.ui.MainView;
import edu.galileo.android.photofeed.photolist.adapters.OnItemClickListener;
import edu.galileo.android.photofeed.photolist.di.DaggerPhotoListComponent;
import edu.galileo.android.photofeed.photolist.di.PhotoListComponent;
import edu.galileo.android.photofeed.photolist.di.PhotoListModule;
import edu.galileo.android.photofeed.photolist.ui.PhotoListFragment;
import edu.galileo.android.photofeed.photolist.ui.PhotoListView;

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

    public MainComponent getMainComponent(MainView view, FragmentManager fragmentManager, Fragment[]fragments, String[] titles){
        return DaggerMainComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .mainModule(new MainModule(view,titles, fragments, fragmentManager))
                .build();
    }

    public PhotoListComponent getPhotoListComponent(PhotoListFragment fragment,PhotoListView view, OnItemClickListener onItemClickListener){
        return DaggerPhotoListComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .photoListModule(new PhotoListModule(view, onItemClickListener))
                .build();
    }
}
