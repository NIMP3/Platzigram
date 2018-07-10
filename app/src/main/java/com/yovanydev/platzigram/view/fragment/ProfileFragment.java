package com.yovanydev.platzigram.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yovanydev.platzigram.R;
import com.yovanydev.platzigram.adapter.PictureAdapterRecyclerView;
import com.yovanydev.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        showToolbar("",false, view);

        RecyclerView picturesRecycler = view.findViewById(R.id.recyclerviewPictureProfile);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(),R.layout.cardview_picture, getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();

        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg","Edwin Yovany","4 días", "4 Me Gusta"));
        pictures.add(new Picture("https://miviaje.com/wp-content/uploads/2016/05/shutterstock_250683580.jpg","Carolina Fernandes","1 día", "1 Me Gusta"));
        pictures.add(new Picture("https://i.imgur.com/PQZ7otL.jpg","Jesús Insuasty","2 días", "10 Me Gusta"));
        pictures.add(new Picture("https://i.imgur.com/5d2HHEE.jpg","Estefania Burbano","14 días", "50 Me Gusta"));
        pictures.add(new Picture("https://i.imgur.com/cmEqJOv.jpg","Felipe Muñoz","50 días", "100 Me Gusta"));

        return pictures;
    }

    /*----------------------------------------------------------------------------------------------
    * Mostrar el Toolbar en la vista*/
    public void showToolbar(String title, Boolean upButton, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
