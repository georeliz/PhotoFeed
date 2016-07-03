package edu.galileo.android.photofeed.photolist.di;

import android.widget.ListAdapter;

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

/**
 * Created by Gerson on 3/07/2016.
 */
@Module
public class PhotoListModule {
    private PhotoListView view;
    private OnItemClickListener onItemClickListener;

    public PhotoListModule(PhotoListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    PhotoListView providesPhotoListView(){
        return this.view;
    }

    @Provides
    @Singleton
    PhotoListPresenter providesPhotoListPresenter(EventBus eventBus, PhotoListView view, PhotoListInteractor interactor){
        return new PhotoListPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    PhotoListInteractor providesPhotoListInteractor(PhotoListRepository repository){
        return new PhotoListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PhotoListRepository providesPhotoListRepository(EventBus eventBus, FirebaseAPI firebaseAPI ){
        return new PhotoListRepositoryImpl(eventBus, firebaseAPI);
    }

    @Provides
    @Singleton
    PhotoListAdapter providesPhotoListAdapter(Util utils, List<Photo> photoList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new PhotoListAdapter(utils, photoList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return  this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Photo> providesPhotosList(){
        return new ArrayList<Photo>();
    }
}
