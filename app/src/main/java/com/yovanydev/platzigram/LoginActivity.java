package com.yovanydev.platzigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yovanydev.platzigram.view.ContainerActivity;
import com.yovanydev.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //Ir a la Ativity CreateAccount en caso de no estar registrado
    public void goCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    //Ir a la Activity Container cuando el usuario se loguea
    public void goContainer(View view) {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

}
