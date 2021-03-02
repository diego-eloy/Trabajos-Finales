package com.diego.trabajo_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.diego.trabajo_final.adaptador.AdaptadorDialogo;
import com.diego.trabajo_final.adaptador.AdaptadorEquipos;
import com.diego.trabajo_final.adaptador.AdaptadorLiga;
import com.diego.trabajo_final.fragments.FragmentDetalle;
import com.diego.trabajo_final.fragments.FragmentEquipos;
import com.diego.trabajo_final.fragments.FragmentLigas;
import com.diego.trabajo_final.utils.Equipo;
import com.diego.trabajo_final.utils.Liga;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorLiga.pasarDatos,AdaptadorEquipos.pasarDatos {

    //Elementos para la configuracion de la barra lateral y del toolbar
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //Elemento de la barra TextView
    private TextView textoBarra;

    //Elementos para hacer el cambio de fragments
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        configurarBarra();
        acciones();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sitio_fragment, new FragmentLigas(), "ligas");
        fragmentTransaction.commit();

    }

    //Configuracion del menu lateral y y la Barra
    //PRIMER METODO
    private void configurarBarra() {
        this.setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar
                , R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    //SEGUNDO METODO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void instancias() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menu_navigation);

        textoBarra = findViewById(R.id.texto_barra_nav_main);
    }

    private void acciones() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.item_home:
                        textoBarra.setText("HOME");
                        fragmentTransaction.replace(R.id.sitio_fragment, new FragmentLigas(), "Liga");
                        fragmentTransaction.addToBackStack("liga");
                        break;
                    case R.id.item_favoritos:
                        textoBarra.setText("FAVORITOS");
                        fragmentTransaction.replace(R.id.sitio_fragment, new FragmentEquipos(), "favoritos");
                        fragmentTransaction.addToBackStack("equipos");
                        break;
                    case R.id.item_salir:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                fragmentTransaction.commit();
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void pasarNombreLiga(String nombre,String equipo) {
        textoBarra.setText(nombre);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sitio_fragment, FragmentEquipos.newInstance(nombre,equipo), "equipos");
        fragmentTransaction.addToBackStack("equipos");
        fragmentTransaction.commit();
    }


    @Override
    public void pasarEquipo(Equipo equipo) {
        textoBarra.setText(equipo.getNombre());
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sitio_fragment, FragmentDetalle.newInstance(equipo), "detalle");
        fragmentTransaction.addToBackStack("detalle");
        fragmentTransaction.commit();
    }


}