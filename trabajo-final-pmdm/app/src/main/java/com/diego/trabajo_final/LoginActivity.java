package com.diego.trabajo_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textoEmail,textoPasswd;
    Button botonRegistrar,botonAcceder;
    //puntero a mi base de datos
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        acciones();

    }

    private void acciones(){
    botonAcceder.setOnClickListener(this);
    botonRegistrar.setOnClickListener(this);
    }

    private void instancias() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser  = firebaseAuth.getCurrentUser();
        textoEmail = findViewById(R.id.username);
        textoPasswd = findViewById(R.id.password);
        botonAcceder = findViewById(R.id.boton_login_acceder);
        botonRegistrar  = findViewById(R.id.boton_login_registrarse);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton_login_registrarse:
                Toast.makeText(getApplicationContext(),"pulsado",Toast.LENGTH_LONG).show();
                firebaseAuth.createUserWithEmailAndPassword(textoEmail.getText().toString(),textoPasswd.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.v("usuarios","el usuario se ha creado correctamente");
                        }else{
                            Log.v("usuarios","el usuario se ha creado erroneamente");
                        }
                    }
                });
                break;
            case R.id.boton_login_acceder:
                firebaseAuth.signInWithEmailAndPassword(textoEmail.getText().toString(),textoPasswd.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Log.v("usuarios","sesion iniciada");
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }else{
                                    Log.v("usuarios","error usuario no registrado");
                                }
                            }
                        });

                break;
        }
    }
}
