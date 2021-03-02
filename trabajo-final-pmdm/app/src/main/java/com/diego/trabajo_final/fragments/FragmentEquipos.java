package com.diego.trabajo_final.fragments;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.diego.trabajo_final.R;
import com.diego.trabajo_final.adaptador.AdaptadorEquipos;
import com.diego.trabajo_final.adaptador.AdaptadorLiga;
import com.diego.trabajo_final.utils.Equipo;
import com.diego.trabajo_final.utils.Liga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentEquipos extends Fragment {

    private ArrayList<Equipo> listaEquipos;
    private ImageView imagen;
    private RecyclerView recycler;
    private AdaptadorEquipos miAdaptador;

    private static final String ARG_PARAM1 = "detalle";
    private static final String ARG_PARAM2 = "equipo";

    private String nombreLiga;
    private String equipo;

    public FragmentEquipos() {

    }

    public static FragmentEquipos newInstance(String detalle,String equipo) {
        FragmentEquipos fragment = new FragmentEquipos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, detalle);
        args.putString(ARG_PARAM2, equipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            nombreLiga = getArguments().getString("detalle");
            equipo = getArguments().getString("equipo");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instancias();
        realizarPeticion();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler = getView().findViewById(R.id.recycler_equipos);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler.setAdapter(miAdaptador);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_equipos, container, false);
    }

    private void instancias() {
        listaEquipos = new ArrayList();
        miAdaptador = new AdaptadorEquipos(getContext(), listaEquipos);
    }

    //Metodo el cual coge el array de Ligas y guarda liga a liga en un nuevo array
    private void procesarPeticion(JSONObject response) {

        try {
            JSONArray arrayResultados = response.getJSONArray("teams");
            for (int i = 0; i < arrayResultados.length(); i++) {
                JSONObject equipo = arrayResultados.getJSONObject(i);
                String liga = equipo.getString("strLeague");
                String imagen = equipo.getString("strTeamLogo");
                String nombre = equipo.getString("strTeam");
                String descripcion = equipo.getString("strStadiumDescription");
                Equipo equipoAgregado = new Equipo(imagen, nombre, descripcion);
                miAdaptador.agregarEquipo(equipoAgregado);
                miAdaptador.notifyDataSetChanged();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Hace la conexion con la url
    private void realizarPeticion() {

        String url = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id="+equipo;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarPeticion(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("error", "error");
            }
        });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

    }


}
