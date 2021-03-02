package com.diego.trabajo_final.adaptador;

import android.app.Presentation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diego.trabajo_final.DialogoRedesSociales;
import com.diego.trabajo_final.R;
import com.diego.trabajo_final.fragments.FragmentEquipos;
import com.diego.trabajo_final.utils.Dialogo;
import com.diego.trabajo_final.utils.Equipo;
import com.diego.trabajo_final.utils.Liga;

import java.util.ArrayList;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.MyHolder>{

    private Context context;
    private ArrayList<Equipo> listaEquipo;
    private pasarDatos listener;

    public AdaptadorEquipos(Context context, ArrayList<Equipo> lista) {
        this.context = context;
        this.listaEquipo = lista;
        this.listener = (pasarDatos) context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adaptador_equipo, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Equipo equipo = listaEquipo.get(position);
        Glide.with(context).load(equipo.getImagen()).into(holder.imagen);
        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.pasarEquipo(equipo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEquipo.size();
    }

    public void agregarEquipo(Equipo equipo) {
        this.listaEquipo.add(equipo);
        this.notifyDataSetChanged();
    }

    public interface pasarDatos {
        void pasarEquipo(Equipo equipo);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        Button boton;
        TextView nombre,descripcion;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_adaptador_equipo);
            boton = itemView.findViewById(R.id.boton_adaptador_equipo);

        }

        public ImageView getImage() {
            return imagen;
        }

        public Button getBoton() {
            return boton;
        }
    }

}

