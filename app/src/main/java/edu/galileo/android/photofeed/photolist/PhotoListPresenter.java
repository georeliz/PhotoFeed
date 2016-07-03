package edu.galileo.android.photofeed.photolist;

import edu.galileo.android.photofeed.entities.Photo;
import edu.galileo.android.photofeed.photolist.events.PhotoListEvent;

/**
 * Created by Gerson on 2/07/2016.
 */
public interface PhotoListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removePhoto(Photo photo);
    void onEventMainThread(PhotoListEvent event);
}
