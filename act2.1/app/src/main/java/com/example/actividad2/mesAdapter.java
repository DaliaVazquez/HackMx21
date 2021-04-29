package com.example.actividad2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;

public class mesAdapter extends RecyclerView.Adapter<mesAdapter.AmigosViewHolder>{
    private static final int EXAMPLE_CODE = 0;
    private ArrayList<String> data;
    private View.OnClickListener listener;
    private static final String ARG_PARAM1 = "message";
    private String message;

    public mesAdapter(ArrayList<String> data, View.OnClickListener listener){
        this.listener = listener;
        this.data = data;
    }

    @NonNull
    @Override
    public AmigosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        // add behaviour to the button
        // v.findViewById()

        v.setOnClickListener(listener);
        AmigosViewHolder dvh = new AmigosViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AmigosViewHolder holder, int position) {
        try{
            JSONObject temp=new JSONObject((String.valueOf(data.get(position))));
            /*holder.rowButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Log.wtf("BUTTON!", "data on position: " + position + ", " +data.get(position));
                    //Intent i = new Intent(amigosAdapter.class, infoAmigo.class);
                    //startActivityForResult(i, EXAMPLE_CODE);
                }
            });*/

            holder.rowText1.setText(temp.getString("nombre"));
            holder.rowText2.setText(temp.getString("hobby"));
            //holder.rowButton1.setText("Stalkear");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // first - define a view holder
    // view holder is a class in charge of dealing with an specific type of view
    public class AmigosViewHolder extends RecyclerView.ViewHolder{

        public TextView rowText1, rowText2;
        //public Button rowButton1;

        public AmigosViewHolder(@NonNull View itemView) {
            super(itemView);

            rowText1 = itemView.findViewById(R.id.rowText1);
            rowText2 = itemView.findViewById(R.id.rowText2);
            //rowButton1 = itemView.findViewById(R.id.rowButton1);

            /*rowButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.wtf("BUTTON!", "data on position: " + rowText1);
                    //Intent i = new Intent(this, infoAmi.class);
                    //startActivityForResult(i, EXAMPLE_CODE);
                }
            });*/
        }
    }

}
