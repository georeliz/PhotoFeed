package edu.galileo.android.photofeed.libs.base;

import android.widget.ImageView;

/**
 * Created by Lab1 on 28/06/2016.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object object);
}
