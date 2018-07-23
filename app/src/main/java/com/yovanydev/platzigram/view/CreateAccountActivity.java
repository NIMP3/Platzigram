package com.yovanydev.platzigram.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.yovanydev.platzigram.LoginActivity;
import com.yovanydev.platzigram.R;

public class CreateAccountActivity extends AppCompatActivity {

    TextInputEditText etEmail, etName, etUser, etPassword, etConfirmPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_create_account), true);

        //Crea una nueva instancia de firebase
        firebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.et_email);
        etName = findViewById(R.id.et_name);
        etUser = findViewById(R.id.et_user);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirmPassword);

        Button btnJoinUs = findViewById(R.id.btn_joinUs);
        btnJoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(validateForm(view)){
                    String password = etPassword.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) goToLogin();
                                    else
                                        Snackbar.make(view, "Registro Fallido", Snackbar.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    /*----------------------------------------------------------------------------------------------
    * Validar el formulario de registro*/
    private Boolean validateForm(View view) {
        //Obtenemos los valores del formulario
        String email = etEmail.getText().toString().trim();
        String name = etEmail.getText().toString().trim();
        String user = etUser.getText().toString().trim();
        String password =  etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();


        if(email.equals("") || name.equals("") || user.equals("") || password.equals("") || confirmPassword.equals("")) {
            Snackbar.make(view, "Por favor complete todos los campos del formulario", Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if(!password.equals(confirmPassword)){
            Snackbar.make(view, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /*----------------------------------------------------------------------------------------------
    * Monstrar un titulo o un botton de navegación en el Toolbar*/
    public void showToolbar(String title, Boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
