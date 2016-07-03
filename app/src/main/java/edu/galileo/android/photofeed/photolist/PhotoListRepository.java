package edu.galileo.android.photofeed.photolist;

import edu.galileo.android.photofeed.entities.Photo;

/**
 * Created by Gerson on 2/07/2016.
 */
public interface PhotoListRepository {
    void subscribe();
    void unsubscribe();
    void removePhoto(Photo photo);
}
