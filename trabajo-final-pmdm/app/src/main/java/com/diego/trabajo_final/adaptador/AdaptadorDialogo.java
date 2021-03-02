package com.diego.trabajo_final.adaptador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diego.trabajo_final.R;
import com.diego.trabajo_final.utils.Dialogo;

import java.util.ArrayList;

public class AdaptadorDialogo extends BaseAdapter {

    Context context;
    ArrayList<Dialogo> listaDialogo;

    public AdaptadorDialogo(Context context, ArrayList<Dialogo> listaDialogo) {
        this.context = context;
        this.listaDialogo = listaDialogo;
    }

    @Override
    public int getCount() {
        return listaDialogo.size();
    }

    @Override
    public Object getItem(int i) {
        return listaDialogo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_adaptador_redes_sociales, viewGroup, false);
        Dialogo dialogo = listaDialogo.get(i);
        ImageView imageView = viewGroup.findViewById(R.id.imagen_item_dialogo);
        TextView textView = viewGroup.findViewById(R.id.texto_item_dialogo);
        TextView textView1 = viewGroup.findViewById(R.id.texto_item2_dialogo);

        imageView.setImageResource(dialogo.getImagen());
        textView.setText(dialogo.getNombre());
        textView1.setText(dialogo.getEquipo());

        return view;
    }

}
