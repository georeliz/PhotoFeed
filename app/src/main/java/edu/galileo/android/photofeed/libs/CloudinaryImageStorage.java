package edu.galileo.android.photofeed.libs;

import android.os.AsyncTask;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import edu.galileo.android.photofeed.libs.base.ImageStorage;
import edu.galileo.android.photofeed.libs.base.ImageStorageFinishedListener;

/**
 * Created by Lab1 on 28/06/2016.
 */
public class CloudinaryImageStorage implements ImageStorage {
    private Cloudinary cloudinary;

    public CloudinaryImageStorage(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String getImageUrl(String id) {
        return cloudinary.url().generate(id);
    }

    @Override
    public void upload(final File file, final String id, final ImageStorageFinishedListener listener) {
        new AsyncTask<Void, Void, Void>() {
            boolean success = false;
            @Override
            protected Void doInBackground(Void... params) {
                Map param = ObjectUtils.asMap("public_id", id);
                try {
                    cloudinary.uploader().upload(file, param);
                    success = true;
                } catch (IOException e) {
                    listener.onError(e.getLocalizedMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (success){
                    listener.onSuccess();
                }
            }
        }.execute();
    }
}
