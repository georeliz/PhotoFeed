package edu.galileo.android.photofeed.photomap;

import edu.galileo.android.photofeed.entities.Photo;

/**
 * Created by Gerson on 2/07/2016.
 */
public class PhotoMapInteractorImpl implements PhotoMapInteractor {

    private PhotoMapRepository repository;

    public PhotoMapInteractorImpl(PhotoMapRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

}
