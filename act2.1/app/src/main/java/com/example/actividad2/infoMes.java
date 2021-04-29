package com.example.actividad2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class infoMes extends AppCompatActivity {


    private TextView textView;
    TextView nombre,hobby,edad,telefono,direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_mes);

        Intent intent = getIntent();


        //textView = findViewById(R.id.textView2);
        nombre = findViewById(R.id.textView);
        hobby = findViewById(R.id.textView2);
        edad = findViewById(R.id.textView3);
        telefono = findViewById(R.id.textView4);
        direccion = findViewById(R.id.textView5);

        //textView.setText("Hi " +intent.getStringExtra("myName"));
        nombre.setText("Nombre: " + intent.getStringExtra("nombre"));
        edad.setText("Edad: " + intent.getStringExtra("edad"));
        hobby.setText("Hobby: " + intent.getStringExtra("hobby"));
        telefono.setText("Telefono: " + intent.getStringExtra("telefono"));
        direccion.setText("Direccion: " + intent.getStringExtra("direccion"));

    }
    public void goBack(View v){

        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);

        finish();
    }

}
