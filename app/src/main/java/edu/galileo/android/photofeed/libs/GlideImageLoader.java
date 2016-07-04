package edu.galileo.android.photofeed.libs;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

import edu.galileo.android.photofeed.libs.base.ImageLoader;

/**
 * Created by Lab1 on 28/06/2016.
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager requestManager;
    private RequestListener onFinishedLoadingListener;

    public GlideImageLoader(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if (onFinishedLoadingListener != null){
            requestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinishedLoadingListener)
                    .into(imageView);
        }else{
            requestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(700, 700)
                    .centerCrop()
                    .into(imageView);

        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        try {
            this.onFinishedLoadingListener = (RequestListener) listener;
        } catch (ClassCastException e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }
    }
}
