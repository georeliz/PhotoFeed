package edu.galileo.android.photofeed.photolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.galileo.android.photofeed.R;
import edu.galileo.android.photofeed.domain.Util;
import edu.galileo.android.photofeed.entities.Photo;
import edu.galileo.android.photofeed.libs.base.ImageLoader;

/**
 * Created by Gerson on 2/07/2016.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {


    private Util utils;
    private List<Photo> photoList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public PhotoListAdapter(Util utils, List<Photo> photoList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.utils = utils;
        this.photoList = photoList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo currentPhoto = photoList.get(position);
        holder.setOnClickListener(currentPhoto,onItemClickListener);

        imageLoader.load(holder.imgMain, currentPhoto.getUrl());
        imageLoader.load(holder.imgAvatar, utils.getAvatarUrl(currentPhoto.getEmail()));

        holder.txtUser.setText(currentPhoto.getEmail());

        double lat = currentPhoto.getLatitude();
        double lng = currentPhoto.getLongitude();

        if (lat != 0.0 && lng != 0.0){
            holder.txtPlace.setText(utils.getFromLocation(lat, lng));
            holder.txtPlace.setVisibility(View.VISIBLE);
        }else {
            holder.txtPlace.setVisibility(View.GONE);
        }

        if (currentPhoto.isPublishedByMe()){
            holder.imgDelete.setVisibility(View.VISIBLE);
        }else {
            holder.imgDelete.setVisibility(View.GONE);
        }
    }

    public void addPhoto(Photo photo){
        photoList.add(0,photo);
        notifyDataSetChanged();
    }

    public void removePhoto(Photo photo){
        photoList.remove(photo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txtUser)
        TextView txtUser;
        @Bind(R.id.layoutHeader)
        LinearLayout layoutHeader;
        @Bind(R.id.imgMain)
        ImageView imgMain;
        @Bind(R.id.txtPlace)
        TextView txtPlace;
        @Bind(R.id.imgShare)
        ImageButton imgShare;
        @Bind(R.id.imgDelete)
        ImageButton imgDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setOnClickListener(final Photo photo, final OnItemClickListener listener){
            txtPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlaceClick(photo);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onShareClick(photo, imgMain);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(photo);
                }
            });
        }
    }
}
