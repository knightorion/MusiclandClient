package com.example.diam.giovannii.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diam.giovannii.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentEventiPromotore extends ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;

    ListView listaEventiPromotore;
    TextView nomeEventoPromotore;

    JSONArray eventiPromotore;
    int idEvento;
    String nomeEvento;

    CustomAdapterEventoPromotore customAdapterEventoPromotore;

    public FragmentEventiPromotore() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_eventi_promotore, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        listaEventiPromotore=v.findViewById(android.R.id.list);
        nomeEventoPromotore=v.findViewById(R.id.nome_evento);

        return v;
    }



    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("EVENTI_PROMOTORE")) {
            try {
                eventiPromotore = new JSONArray(i.getStringExtra("EVENTI_PROMOTORE"));

                ArrayList<String> nomiEventi = new ArrayList<String>();
                ArrayList<Integer> idEventi = new ArrayList<Integer>();

                customAdapterEventoPromotore = new CustomAdapterEventoPromotore(getActivity().getApplicationContext(),
                        R.layout.list_element_evento_promotore,
                        new ArrayList<EventoVisualizzato>());

                listaEventiPromotore.setAdapter(customAdapterEventoPromotore);


                for (int j = 0; j < eventiPromotore.length(); j++) {

                    nomeEvento = eventiPromotore.getJSONObject(j).getString("nomeEvento");
                    nomiEventi.add(nomeEvento);

                    idEvento = eventiPromotore.getJSONObject(j).getInt("idEvento");
                    idEventi.add(idEvento);

                    EventoVisualizzato miServi = new EventoVisualizzato(nomeEvento, idEvento);
                    customAdapterEventoPromotore.add(miServi);

                }

                getListView().setOnItemClickListener(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE EVENTI");
            Toast.makeText(getActivity().getApplicationContext(), "Non hai ancora creato nessun evento", Toast.LENGTH_LONG).show();
        }

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        idEvento = customAdapterEventoPromotore.getItem(position).getIdEvento();
        i.putExtra("ID_EVENTO", idEvento);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentSingoloEventoPromotore fragmentSingoloEventoPromotore = new FragmentSingoloEventoPromotore();
        ft.replace(R.id.frammento_da_inserire, fragmentSingoloEventoPromotore);
        ft.addToBackStack(null);
        ft.commit();
    }
}
