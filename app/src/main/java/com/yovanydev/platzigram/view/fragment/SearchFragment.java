package com.yovanydev.platzigram.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.yovanydev.platzigram.R;
import com.yovanydev.platzigram.adapter.PictureAlbumAdapterGridView;
import com.yovanydev.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        GridView gridView = view.findViewById(R.id.gridViewPictures);
        PictureAlbumAdapterGridView pictureAlbumAdapterGridView = new PictureAlbumAdapterGridView(
                buildPictures(),
                R.layout.cardview_picture_album,
                getActivity()
        );

        gridView.setAdapter(pictureAlbumAdapterGridView);

        return view;
    }

    /*----------------------------------------------------------------------------------------------
    * Construye una lista de Pictures prueba*/
    public ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();

        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg","Edwin Yovany","4 días", "4 Me Gusta"));
        pictures.add(new Picture("https://miviaje.com/wp-content/uploads/2016/05/shutterstock_250683580.jpg","Carolina Fernandes","1 día", "1 Me Gusta"));
        pictures.add(new Picture("https://i.imgur.com/PQZ7otL.jpg","Jesús Insuasty","2 días", "10 Me Gusta"));
        pictures.add(new Picture("https://i.imgur.com/5d2HHEE.jpg","Estefania Burbano","14 días", "50 Me Gusta"));
        pictures.add(new Picture("https://i.imgur.com/cmEqJOv.jpg","Felipe Muñoz","50 días", "100 Me Gusta"));

        return pictures;
    }

}
