package edu.galileo.android.photofeed.main;

import android.location.Location;

/**
 * Created by Lab1 on 29/06/2016.
 */
public interface MainRepository {
    void logout();
    void uploadPhoto(Location location, String path);
}
