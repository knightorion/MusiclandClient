package com.example.diam.giovannii.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diam.giovannii.R;

import java.util.ArrayList;

public class CustomAdapterArtista extends ArrayAdapter<ArtistaVisualizzato> {

    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterArtista(Context c, int resourceId, ArrayList<ArtistaVisualizzato> artists){
        super(c, resourceId, artists);
        resource = resourceId;
        inflater = LayoutInflater.from(c);
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null) {
            v = inflater.inflate(R.layout.list_element_artista, null);
        }

        ArtistaVisualizzato artistaVisualizzato = getItem(position);


        TextView nomeArtista;
        int idArtista;


        nomeArtista =  v.findViewById(R.id.textView_email_list_element_artista);

        nomeArtista.setText(artistaVisualizzato.getNicknameArtista());
        idArtista = artistaVisualizzato.getIdArtista();


        return v;
    }
}