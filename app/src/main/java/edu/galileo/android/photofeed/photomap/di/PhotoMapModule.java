package edu.galileo.android.photofeed.photomap.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.photofeed.domain.FirebaseAPI;
import edu.galileo.android.photofeed.domain.Util;
import edu.galileo.android.photofeed.entities.Photo;
import edu.galileo.android.photofeed.libs.base.EventBus;
import edu.galileo.android.photofeed.libs.base.ImageLoader;
import edu.galileo.android.photofeed.photolist.PhotoListInteractor;
import edu.galileo.android.photofeed.photolist.PhotoListInteractorImpl;
import edu.galileo.android.photofeed.photolist.PhotoListPresenter;
import edu.galileo.android.photofeed.photolist.PhotoListPresenterImpl;
import edu.galileo.android.photofeed.photolist.PhotoListRepository;
import edu.galileo.android.photofeed.photolist.PhotoListRepositoryImpl;
import edu.galileo.android.photofeed.photolist.adapters.OnItemClickListener;
import edu.galileo.android.photofeed.photolist.adapters.PhotoListAdapter;
import edu.galileo.android.photofeed.photolist.ui.PhotoListView;
import edu.galileo.android.photofeed.photomap.PhotoMapInteractor;
import edu.galileo.android.photofeed.photomap.PhotoMapInteractorImpl;
import edu.galileo.android.photofeed.photomap.PhotoMapPresenter;
import edu.galileo.android.photofeed.photomap.PhotoMapPresenterImpl;
import edu.galileo.android.photofeed.photomap.PhotoMapRepository;
import edu.galileo.android.photofeed.photomap.PhotoMapRepositoryImpl;
import edu.galileo.android.photofeed.photomap.ui.PhotoMapView;

/**
 * Created by Gerson on 3/07/2016.
 */
@Module
public class PhotoMapModule {
    private PhotoMapView view;

    public PhotoMapModule(PhotoMapView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    PhotoMapView providesPhotoMapView(){
        return this.view;
    }

    @Provides
    @Singleton
    PhotoMapPresenter providesPhotoMapPresenter(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor){
        return new PhotoMapPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    PhotoMapInteractor providesPhotoMapInteractor(PhotoMapRepository repository){
        return new PhotoMapInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PhotoMapRepository providesPhotoMapRepository(EventBus eventBus, FirebaseAPI firebaseAPI ){
        return new PhotoMapRepositoryImpl(eventBus, firebaseAPI);
    }


}
