package com.yovanydev.platzigram.view.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yovanydev.platzigram.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostFragment extends Fragment {

    ImageView imageViewPictureTaken;
    FloatingActionButton floatingActionButtonPictureTake;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;


    public NewPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);

        imageViewPictureTaken = view.findViewById(R.id.imageViewPictureTaken);
        floatingActionButtonPictureTake = view.findViewById(R.id.floatingActionButtonPictureTake);

        floatingActionButtonPictureTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Picasso.get().load(currentPhotoPath).into(imageViewPictureTaken);
            addPictureToGallery();
            //Snackbar.make(getView(),currentPhotoPath,Snackbar.LENGTH_LONG).show();
        }
    }

    private void takePicture() {
        //Intent para hacer uso de la camara
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(photoFile != null) {
                //Crear el archivo dentro de los directorios de nuestro dispositivo
                Uri photoUri = FileProvider.getUriForFile(
                        getActivity(),
                        "com.yovanydev.platzigram",
                        photoFile);

                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intentTakePicture, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp +"_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = "file:"+image.getAbsolutePath();
        return image;
    }

    //Añadir la foto a la galeria
    private void addPictureToGallery () {
        Intent intentMediaScan = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(currentPhotoPath);
        Uri contenUri = Uri.fromFile(file);
        intentMediaScan.setData(contenUri);
        getActivity().sendBroadcast(intentMediaScan);
    }

}
