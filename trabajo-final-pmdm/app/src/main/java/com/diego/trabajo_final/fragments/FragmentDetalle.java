package com.diego.trabajo_final.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.diego.trabajo_final.DialogoRedesSociales;
import com.diego.trabajo_final.R;
import com.diego.trabajo_final.utils.Equipo;

public class FragmentDetalle extends Fragment{

    private static final String ARG_PARAM1 = "detalle";
    private ImageView imagen;
    private TextView nombre;
    private TextView descripcion;
    private Button botonRedes,botonFavoritos;
    private Equipo equipoRecuperado;
    private String equipo;

    public FragmentDetalle() {
        // Required empty public constructor
    }

    public static FragmentDetalle newInstance(Equipo equipo) {
        FragmentDetalle fragment = new FragmentDetalle();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, equipo);
        fragment.setArguments(args);
        return fragment;
    }

    // sirve para recuperar el dato pasado por la interfaz
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null){
            equipoRecuperado = (Equipo) getArguments().getSerializable("detalle");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();
        acciones();
        Glide.with(getContext()).load(equipoRecuperado.getImagen()).into(imagen);
        nombre.setText(equipoRecuperado.getNombre());
        descripcion.setText(equipoRecuperado.getDescripcion());
    }

    private void acciones() {
        botonRedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DialogoRedesSociales dialogoRedesSociales = new DialogoRedesSociales().newInstance(equipoRecuperado.getNombre());
            dialogoRedesSociales.show(getFragmentManager(),"tag1");
            }
        });
        botonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void instancias() {
        imagen = getView().findViewById(R.id.imagen_equipo);
        nombre = getView().findViewById(R.id.nombre_equipo);
        descripcion = getView().findViewById(R.id.descripcion_equipo);
        botonFavoritos = getView().findViewById(R.id.boton_favoritos_equipo);
        botonRedes = getView().findViewById(R.id.boton_redessociales_equipo);
    }



}
