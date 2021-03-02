package com.diego.trabajo_final.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.diego.trabajo_final.R;
import com.diego.trabajo_final.adaptador.AdaptadorLiga;
import com.diego.trabajo_final.utils.Liga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentLigas extends Fragment{

    //Elementos para la peticion de datos al Json Volley
    private ArrayList<Liga> listaLigas;
    private RecyclerView recycler;
    private AdaptadorLiga miAdaptador;

    //Elementos para hacer el cambio de fragments

    private static final String ARG_PARAM1 = "detalle";
    private String detalle;

    public FragmentLigas() {
        // Required empty public constructor
    }

    public static FragmentLigas newInstance(String detalle) {
        FragmentLigas fragment = new FragmentLigas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, detalle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newInstance(detalle);
        instancias();
        realizarPeticion();
        acciones();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ligas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler = getView().findViewById(R.id.recycler_ligas);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recycler.setAdapter(miAdaptador);
    }

    private void instancias() {
        listaLigas = new ArrayList();
        miAdaptador = new AdaptadorLiga(getContext(),listaLigas);
        miAdaptador.notifyDataSetChanged();

    }

    private void acciones() {

    }

    //Metodo el cual coge el array de Ligas y guarda liga a liga en un nuevo array
    private void procesarPeticion(JSONObject response) {
        try {
            JSONArray arrayResultados = response.getJSONArray("leagues");
            for (int i = 0; i < arrayResultados.length(); i++) {
                JSONObject liga = arrayResultados.getJSONObject(i);
                String nombre = liga.getString("strLeague");
                String equipo = liga.getString("idLeague");
                Liga ligaAgregada = new Liga(nombre,equipo);
                miAdaptador.agregarLiga(ligaAgregada);
                miAdaptador.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Hace la conexion con la url
    private void realizarPeticion() {
        String url = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php";
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
