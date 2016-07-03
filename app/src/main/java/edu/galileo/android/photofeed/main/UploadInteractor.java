package edu.galileo.android.photofeed.main;

import android.location.Location;

/**
 * Created by Gerson on 1/07/2016.
 */
public interface UploadInteractor {
    void execute(Location location, String path);
}
