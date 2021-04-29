package com.example.actividad2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AmigosFragment extends Fragment implements View.OnClickListener {

    //private ArrayList<String> data;
    public RecyclerView recyclerView;
    public JSONArray JSONarray;
    private static String info;
    private static ArrayList<String> ArrayListdatos;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            JSONarray=new JSONArray(ArrayListdatos);
        }
        /*data = new ArrayList<>();
        data.add("Kaiser");
        data.add("Fifi");
        data.add("Fido");
        data.add("Firulais");
        data.add("Killer");
        data.add("Kaiser");
        data.add("Fifi");
        data.add("Fido");
        data.add("Firulais");
        data.add("Killer");
        data.add("Kaiser");
        data.add("Fifi");
        data.add("Fido");
        data.add("Firulais");
        data.add("Killer");
        data.add("Kaiser");
        data.add("Fifi");
        data.add("Fido");
        data.add("Firulais");
        data.add("Killer");*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_amigos, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);

        amigosAdapter adapter = new amigosAdapter(ArrayListdatos, this);
        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        return v;
    }

    public static AmigosFragment newInstance(JSONArray json) throws JSONException{
        AmigosFragment fragment=new AmigosFragment();
        ArrayListdatos=new ArrayList<String>();
        Bundle args=new Bundle();
        if(json!=null){
            for(int i=0;i<json.length();i++){
                JSONObject temp=json.getJSONObject(i);
                ArrayListdatos.add(temp.toString());
            }
        }
        args.putStringArrayList("JSON",ArrayListdatos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {

        int pos = recyclerView.getChildLayoutPosition(v);
        ((MesesActivity) getActivity()).toInfo(v, pos);

        //Intent i = new Intent(amigosAdapter.class, infoAmigo.class);
        //startActivityForResult(i, EXAMPLE_CODE);
    }

   /* @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        try {
            String parametro = JSONarray.getString(pos);
            InfoAmigoFragment amigosInfo = InfoAmigoFragment.newInstance(parametro);
            ((MainActivity) getActivity()).cambiarFragmento(amigosInfo);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/
}