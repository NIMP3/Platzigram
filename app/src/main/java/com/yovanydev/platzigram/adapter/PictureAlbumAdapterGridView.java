package com.yovanydev.platzigram.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yovanydev.platzigram.R;
import com.yovanydev.platzigram.model.Picture;
import com.yovanydev.platzigram.view.PictureDetailActivity;

import java.util.ArrayList;

public class PictureAlbumAdapterGridView extends BaseAdapter {

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;

    public PictureAlbumAdapterGridView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Picture picture = pictures.get(i);

        if (view == null) {
            view = LayoutInflater.from(activity).inflate(resource,viewGroup,false);
        }

        ImageView imageViewPictureAlbum = view.findViewById(R.id.imageViewPictureAlbum);
        CardView cardViewPictureAlbum = view.findViewById(R.id.cardviewPictureAlbum);

        Picasso.get().load(picture.getPicture()).into(imageViewPictureAlbum);
        cardViewPictureAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);
                activity.startActivity(intent);
            }
        });

        return view;
    }
}
