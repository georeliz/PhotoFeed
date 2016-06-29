package edu.galileo.android.photofeed.libs.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.photofeed.libs.CloudinaryImageStorage;
import edu.galileo.android.photofeed.libs.GlideImageLoader;
import edu.galileo.android.photofeed.libs.GreenRobotEventBus;
import edu.galileo.android.photofeed.libs.base.EventBus;
import edu.galileo.android.photofeed.libs.base.ImageLoader;
import edu.galileo.android.photofeed.libs.base.ImageStorage;

/**
 * Created by Lab1 on 28/06/2016.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public  LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment) {
        return  Glide.with(fragment);
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return  new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(Cloudinary cloudinary) {
       return new CloudinaryImageStorage(cloudinary);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    Cloudinary providesCloudinary(Context context){
        return new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }


}
