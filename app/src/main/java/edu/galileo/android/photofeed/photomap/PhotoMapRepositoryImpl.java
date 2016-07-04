package edu.galileo.android.photofeed.photomap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import edu.galileo.android.photofeed.domain.FirebaseAPI;
import edu.galileo.android.photofeed.domain.FirebaseActionListenerCallback;
import edu.galileo.android.photofeed.domain.FirebaseEventListenerCallback;
import edu.galileo.android.photofeed.entities.Photo;
import edu.galileo.android.photofeed.libs.base.EventBus;
import edu.galileo.android.photofeed.photomap.events.PhotoMapEvent;

/**
 * Created by Gerson on 2/07/2016.
 */
public class PhotoMapRepositoryImpl implements PhotoMapRepository {
    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public PhotoMapRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(PhotoMapEvent.READ_EVENT, error.getMessage());
                } else {
                    post(PhotoMapEvent.READ_EVENT, "");
                }
            }
        });

        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());
                String email = firebaseAPI.getAuthEmail();
                boolean publishedByMy = photo.getEmail().equals(email);
                photo.setPublishedByMe(publishedByMy);
                post(PhotoMapEvent.READ_EVENT, photo);

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());
                post(PhotoMapEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(PhotoMapEvent.READ_EVENT, error.getMessage());
            }
        });

    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }

    private void post(int type, Photo photo) {
        post(type, null, photo);
    }

    private void post(int type, String error) {
        post(type, error, null);
    }

    private void post(int type, String error, Photo photo) {
        PhotoMapEvent event = new PhotoMapEvent();
        event.setType(type);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }
}
