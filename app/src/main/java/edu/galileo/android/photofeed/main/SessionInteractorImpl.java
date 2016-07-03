package edu.galileo.android.photofeed.main;

/**
 * Created by Gerson on 1/07/2016.
 */
public class SessionInteractorImpl implements SessionInteractor{
    MainRepository repository;

    public SessionInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
