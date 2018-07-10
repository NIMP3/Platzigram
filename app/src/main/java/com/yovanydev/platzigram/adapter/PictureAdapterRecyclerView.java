package com.yovanydev.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yovanydev.platzigram.R;
import com.yovanydev.platzigram.model.Picture;
import com.yovanydev.platzigram.view.PictureDetailActivity;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>{

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    //Inflamos nuestro cardview en la vista
    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new PictureViewHolder(view);
    }

    //Aqui se trabaja con la lista de Elementos
    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder pictureViewHolder, int i) {
        Picture picture = pictures.get(i);

        Picasso.get().load(picture.getPicture()).into(pictureViewHolder.pictureCard);
        pictureViewHolder.usernameCard.setText(picture.getUsername());
        pictureViewHolder.timeCard.setText(picture.getTime());
        pictureViewHolder.likeNumberCard.setText(picture.getLikeNumber());

        //Evento Onclick sobre la imágen del cardview
        pictureViewHolder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);

                //Transición de Salida
                //Validación para versiones superiores o iguales a SDK 5
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(
                            intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    activity,
                                    view,
                                    activity.getString(R.string.transition_name_picture)
                            ).toBundle()
                    );
                }
                else activity.startActivity(intent);
            }
        });
    }

    //Tamaño de la lista de Elementos
    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder {

        //Views del Cardview
        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard = itemView.findViewById(R.id.ivPictureCard);
            usernameCard = itemView.findViewById(R.id.tvUsernameCard);
            timeCard = itemView.findViewById(R.id.tvTimeCard);
            likeNumberCard = itemView.findViewById(R.id.tvLikeNumberCard);
        }
    }
}
