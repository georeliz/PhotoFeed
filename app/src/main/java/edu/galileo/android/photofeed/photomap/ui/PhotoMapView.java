package edu.galileo.android.photofeed.photomap.ui;

import edu.galileo.android.photofeed.entities.Photo;

/**
 * Created by Gerson on 2/07/2016.
 */
public interface PhotoMapView {

    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotosError(String error);
}
