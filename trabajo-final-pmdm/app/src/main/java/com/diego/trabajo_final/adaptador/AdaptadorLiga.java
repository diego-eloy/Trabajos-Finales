package com.diego.trabajo_final.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.diego.trabajo_final.R;
import com.diego.trabajo_final.fragments.FragmentEquipos;
import com.diego.trabajo_final.fragments.FragmentLigas;
import com.diego.trabajo_final.utils.Liga;

import java.util.ArrayList;

public class AdaptadorLiga  extends RecyclerView.Adapter<AdaptadorLiga.MyHolder>{

    private Context context;
    private ArrayList<Liga> listaLiga;
    private pasarDatos listener;
    // TODO listener

    public AdaptadorLiga(Context context, ArrayList<Liga> lista){
        this.context = context;
        this.listaLiga = lista;
        this.listener = (pasarDatos) context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adaptador_liga,parent,false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Liga liga = listaLiga.get(position);
        holder.getTexto().setText(liga.getNombre());
        holder.getTexto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.pasarNombreLiga(liga.getNombre(),liga.getEquipo());

            }
        });
    }


    @Override
    public int getItemCount() {
        return listaLiga.size();
    }

    public interface pasarDatos{
        void pasarNombreLiga(String nombre,String equipo);
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView texto;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            texto = itemView.findViewById(R.id.texto_item_recycler);
        }

        public TextView getTexto() {
            return texto;
        }
    }

    public void agregarLiga(Liga liga){
        listaLiga.add(liga);
        this.notifyDataSetChanged();
    }
}
