package com.example.diam.giovannii.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diam.giovannii.R;

import java.util.ArrayList;

public class CustomAdapterEvento extends ArrayAdapter<EventoVisualizzato> {

    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterEvento(Context c, int resourceId, ArrayList<EventoVisualizzato> events){
        super(c, resourceId, events);
        resource = resourceId;
        inflater = LayoutInflater.from(c);
    }


    @Override
    public View getView(int position,  View v, ViewGroup parent) {

        if (v == null) {
            v = inflater.inflate(R.layout.list_element_evento, null);
        }

        EventoVisualizzato eventoVisualizzato = getItem(position);


        TextView nomeEvento;
        int idEvento;


        nomeEvento =  v.findViewById(R.id.textView_nome_evento_click);

        nomeEvento.setText(eventoVisualizzato.getNomeEvento());
        idEvento = eventoVisualizzato.getIdEvento();


        return v;
    }
}
