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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diam.giovannii.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;

    ListView listaEventi;
    TextView nomeEvento;

    JSONArray eventi;
    int idEvento;
    String nomeEventoS;

    CustomAdapterEvento customAdapterEvento;

    public FragmentHome() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();


        listaEventi = v.findViewById(android.R.id.list);
        nomeEvento = v.findViewById(R.id.textView_nome_evento_click);


        return v;
    }


    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("LISTA_EVENTI")) {
            try {
                eventi = new JSONArray(i.getStringExtra("LISTA_EVENTI"));

                ArrayList<String> nomiEventi = new ArrayList<String>();
                ArrayList<Integer> idEventi = new ArrayList<Integer>();

                customAdapterEvento = new CustomAdapterEvento(getActivity().getApplicationContext(),
                        R.layout.list_element_evento,
                        new ArrayList<EventoVisualizzato>());

                listaEventi.setAdapter(customAdapterEvento);

                for (int j = 0; j < eventi.length(); j++) {

                    nomeEventoS = eventi.getJSONObject(j).getString("nomeEvento");
                    nomiEventi.add(nomeEventoS);

                    idEvento = eventi.getJSONObject(j).getInt("idEvento");
                    idEventi.add(idEvento);

                    EventoVisualizzato miServi = new EventoVisualizzato(nomeEventoS, idEvento);
                    customAdapterEvento.add(miServi);

                }

                getListView().setOnItemClickListener(this);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE EVENTI");
            Toast.makeText(getActivity().getApplicationContext(), "Non è stato pubblicato ancora alcun evento", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        idEvento = customAdapterEvento.getItem(position).getIdEvento();

        i.putExtra("ID_EVENTO", idEvento);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentSingoloEvento fragmentSingoloEvento = new FragmentSingoloEvento();
        ft.replace(R.id.frammento_da_inserire, fragmentSingoloEvento);
        ft.addToBackStack(null);
        ft.commit();


    }


}
