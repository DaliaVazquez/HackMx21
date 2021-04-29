package com.example.actividad2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;

public class CrearCuenta extends AppCompatActivity {

    private static final int EXAMPLE_CODE = 0;

    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;
    private EditText nombre, password, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearcuenta);

        nombre = findViewById(R.id.textNombre);
        password = findViewById(R.id.passwordText);
        correo = findViewById(R.id.correo);
        mAuth = FirebaseAuth.getInstance();
    }
    // create a new user with the values given on the GUI
    public void saveUser(View v){

        mAuth.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            // do something on success
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nombre.getText().toString())
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(
                                                        CrearCuenta.this,
                                                        "User crate: " + user.getEmail(),
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

                            mAuth.signOut();

                        } else {
                            // do something on failure
                            Toast.makeText(
                                    CrearCuenta.this,
                                    "FAILURE: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void goBack(View v){

        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);

        finish();
    }
}
