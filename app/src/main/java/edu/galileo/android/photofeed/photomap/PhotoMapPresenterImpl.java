package edu.galileo.android.photofeed.photomap;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.photofeed.entities.Photo;
import edu.galileo.android.photofeed.libs.base.EventBus;
import edu.galileo.android.photofeed.photolist.events.PhotoListEvent;
import edu.galileo.android.photofeed.photolist.ui.PhotoListView;
import edu.galileo.android.photofeed.photomap.events.PhotoMapEvent;
import edu.galileo.android.photofeed.photomap.ui.PhotoMapView;

/**
 * Created by Gerson on 2/07/2016.
 */
public class PhotoMapPresenterImpl implements PhotoMapPresenter {
    private static final String EMPTY_LIST = "Listado vacío";

    private EventBus eventBus;
    private PhotoMapView view;
    private PhotoMapInteractor interactor;

    public PhotoMapPresenterImpl(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    public void subscribe() {
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();
    }



    @Override
    @Subscribe
    public void onEventMainThread(PhotoMapEvent event) {
        if (view != null){
           if (event.getError() != null){
               view.onPhotosError(event.getError());
           }else {
               if (event.getType()== PhotoMapEvent.READ_EVENT){
                   view.addPhoto(event.getPhoto());
               } else if (event.getType() == PhotoMapEvent.DELETE_EVENT){
                   view.removePhoto(event.getPhoto());
               }
           }
        }

    }
}
