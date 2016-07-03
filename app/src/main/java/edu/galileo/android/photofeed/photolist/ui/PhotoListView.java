package edu.galileo.android.photofeed.photolist.ui;

import edu.galileo.android.photofeed.entities.Photo;

/**
 * Created by Gerson on 2/07/2016.
 */
public interface PhotoListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotosError(String error);
}
