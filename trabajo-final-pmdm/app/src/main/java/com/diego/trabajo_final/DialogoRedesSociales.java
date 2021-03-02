package com.diego.trabajo_final;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.diego.trabajo_final.adaptador.AdaptadorDialogo;
import com.diego.trabajo_final.utils.Dialogo;

import java.util.ArrayList;

public class DialogoRedesSociales extends DialogFragment {

    String nombreEquipo;
    AdaptadorDialogo adaptadorDialogo;

    public static DialogoRedesSociales newInstance(String nombre) {

        Bundle bundle = new Bundle();
        DialogoRedesSociales dialogoRedesSociales = new DialogoRedesSociales();
        bundle.putString("nombre", nombre);
        dialogoRedesSociales.setArguments(bundle);
        return dialogoRedesSociales;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (this.getArguments() != null){
            this.nombreEquipo = this.getArguments().getString("nombre");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ArrayList<Dialogo> listaDialogos = new ArrayList<>();
        listaDialogos.add(new Dialogo(R.drawable.twit,"twitter/",nombreEquipo));
        listaDialogos.add(new Dialogo(R.drawable.face,"facebook/",nombreEquipo));
        listaDialogos.add(new Dialogo(R.drawable.insta,"insta/",nombreEquipo));
        listaDialogos.add(new Dialogo(R.drawable.web,"www.",nombreEquipo));

        builder.setTitle("Redes Sociales del "+nombreEquipo);



        return builder.create();
    }




}
