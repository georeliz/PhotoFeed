package edu.galileo.android.photofeed.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import edu.galileo.android.photofeed.libs.base.ImageLoader;

/**
 * Created by Lab1 on 28/06/2016.
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager requestManager;

    public GlideImageLoader(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        requestManager
                .load(URL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(800, 800)
                .centerCrop()
                .into(imageView);

    }
}
