package com.yovanydev.platzigram;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.yovanydev.platzigram.view.ContainerActivity;
import com.yovanydev.platzigram.view.CreateAccountActivity;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppCenter.start(getApplication(), "47a0412d-5918-47c4-82f2-f809fc3d9a6f", Analytics.class, Crashes.class);

        firebaseAuth = FirebaseAuth.getInstance();

        final TextInputEditText etEmail = findViewById(R.id.et_username);
        final TextInputEditText etPassword = findViewById(R.id.et_pass);

        Button buttonLogin = findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                if (email.equals("") || password.equals("")){
                    Snackbar.make(view,"Por favor complete todos los campos",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) goContainer();
                                else
                                    Snackbar.make(view,"Autenticación Fallida",Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    //Ir a la Ativity CreateAccount en caso de no estar registrado
    public void goCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    //Ir a la Activity Container cuando el usuario se loguea
    public void goContainer() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    /*----------------------------------------------------------------------------------------------
    * Lanzar el navegador a la ruta platzigram.com con un Intent implicito*/
    public void goPlatzigramWeb(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://nimp3.github.io"));
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

}
