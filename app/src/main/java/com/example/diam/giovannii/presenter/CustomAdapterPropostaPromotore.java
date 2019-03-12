package com.example.diam.giovannii.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diam.giovannii.R;

import java.util.ArrayList;

public class CustomAdapterPropostaPromotore extends ArrayAdapter<PropostaEventoVisualizzata> {

    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterPropostaPromotore(Context c, int resourceId, ArrayList<PropostaEventoVisualizzata> props) {
        super(c, resourceId, props);
        resource = resourceId;
        inflater = LayoutInflater.from(c);
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null) {
            v = inflater.inflate(R.layout.list_element_proposta_evento_promotore, null);
        }

        PropostaEventoVisualizzata propostaEventoVisualizzata = getItem(position);


        TextView nomeProposta;
        int idEvento;


        nomeProposta = v.findViewById(R.id.textView_nome_proposta_evento_promotore);

        nomeProposta.setText(propostaEventoVisualizzata.getNomeProposta());
        idEvento = propostaEventoVisualizzata.getIdProposta();


        return v;
    }
}
