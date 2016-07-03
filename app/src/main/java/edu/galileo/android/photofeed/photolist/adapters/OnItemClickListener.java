package edu.galileo.android.photofeed.photolist.adapters;

import android.widget.ImageView;

import edu.galileo.android.photofeed.entities.Photo;

/**
 * Created by Gerson on 2/07/2016.
 */
public interface OnItemClickListener {
    void onPlaceClick(Photo photo);
    void onShareClick(Photo photo, ImageView img);
    void onDeleteClick(Photo photo);
}
