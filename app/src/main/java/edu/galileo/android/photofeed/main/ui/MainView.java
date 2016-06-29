package edu.galileo.android.photofeed.main.ui;

/**
 * Created by Lab1 on 29/06/2016.
 */
public interface MainView {
    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);
}
