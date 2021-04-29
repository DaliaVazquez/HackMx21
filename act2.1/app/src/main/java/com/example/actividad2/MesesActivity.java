package com.example.actividad2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MesesActivity extends AppCompatActivity  implements Handler.Callback {
    private static final int EXAMPLE_CODE = 0;
    private static final String TAG_FRAGMENTO="fragmento";
    private Handler handler;
    JSONArray datos;
    private static String info;
    private FirebaseAuth mAuth;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meses);
        textView = findViewById(R.id.hola);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            textView.setText("Hello "+user.getDisplayName());
        }
        handler=new Handler(Looper.getMainLooper(),this);
    }

    public void cambiarFragmento(Fragment nuevo){
        FragmentManager manager=getSupportFragmentManager();
        Fragment f=manager.findFragmentByTag(TAG_FRAGMENTO);
        FragmentTransaction transaction=manager.beginTransaction();
        if(nuevo==f){
            return;
        }
        if(f!=null){
            transaction.remove(f);
        }
        transaction.add(R.id.contenedor,nuevo,TAG_FRAGMENTO);
        transaction.commit();

    }

    public void cargar(View v){
        Request request=new Request("https://raw.githubusercontent.com/DaliaVazquez/appsMov/main/info.txt", handler);
        request.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        try{
            datos=(JSONArray)msg.obj;
            for(int i=0;i<datos.length();i++){
                JSONObject temp=datos.getJSONObject(i);
                Log.wtf("JSON","--------------");
                Log.wtf("JSON",temp.getString("nombre"));
                Log.wtf("JSON",temp.getString("hobby"));

                MesesFragment recyclerViewFragment= MesesFragment.newInstance(datos);
                cambiarFragmento(recyclerViewFragment);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void goBack(View v){
        mAuth.signOut();
        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);

        finish();
    }

    public void toInfo(View v, int pos) {
        try{
            JSONObject temp=datos.getJSONObject(pos);
            Intent i = new Intent(this, infoMes.class);
            i.putExtra("nombre", temp.getString("nombre"));
            i.putExtra("hobby", temp.getString("hobby"));
            i.putExtra("edad", temp.getString("edad"));
            i.putExtra("telefono", temp.getString("telefono"));
            i.putExtra("direccion", temp.getString("direccion"));
            startActivityForResult(i, EXAMPLE_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
/*private void setFragment(Fragment newOne){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // within the same transaction whatever amount of actions
        transaction.add(R.id.container, newOne, TAG_FRAGMENT);
        transaction.commit();
    }

    private void removeFragment(){

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(TAG_FRAGMENT);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    public void swapFragments(View v){

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(TAG_FRAGMENT);

        removeFragment();

        if(fragment == info){
            setFragment(amigos);
        } else {
            setFragment(info);
        }
    }*/
}