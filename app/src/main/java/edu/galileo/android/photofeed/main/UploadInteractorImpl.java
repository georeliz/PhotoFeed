package edu.galileo.android.photofeed.main;

import android.location.Location;

/**
 * Created by Gerson on 1/07/2016.
 */
public class UploadInteractorImpl implements UploadInteractor {

    MainRepository  repository;

    public UploadInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Location location, String path) {
        repository.uploadPhoto(location, path);
    }
}
