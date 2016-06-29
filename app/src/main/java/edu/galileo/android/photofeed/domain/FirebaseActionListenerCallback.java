package edu.galileo.android.photofeed.domain;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Lab1 on 28/06/2016.
 */
public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(FirebaseError error);
}
