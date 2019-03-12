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

public class FragmentEventiArtista extends  ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;

    ListView listaEventiArtista;
    TextView nomeEventoArtista;

    JSONArray eventiArtista;
    int idEvento;
    String nomeEvento;

    CustomAdapterEvento customAdapterEvento;



    public FragmentEventiArtista() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_eventi_artista, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        listaEventiArtista = v.findViewById(android.R.id.list);
        nomeEventoArtista = v.findViewById(R.id.textView_nome_evento_click);

        return v;
    }




    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("EVENTI_ARTISTA")) {
            try {
                eventiArtista = new JSONArray(i.getStringExtra("EVENTI_ARTISTA"));

                ArrayList<String> nomiEventi = new ArrayList<String>();
                ArrayList<Integer> idEventi = new ArrayList<Integer>();

                customAdapterEvento = new CustomAdapterEvento(getActivity().getApplicationContext(),
                        R.layout.list_element_evento,
                        new ArrayList<EventoVisualizzato>());

                listaEventiArtista.setAdapter(customAdapterEvento);

                for (int j = 0; j < eventiArtista.length(); j++) {

                    nomeEvento = eventiArtista.getJSONObject(j).getString("nomeEvento");
                    nomiEventi.add(nomeEvento);

                    idEvento = eventiArtista.getJSONObject(j).getInt("idEvento");
                    idEventi.add(idEvento);

                    EventoVisualizzato miServi = new EventoVisualizzato(nomeEvento, idEvento);
                    customAdapterEvento.add(miServi);
                }



                getListView().setOnItemClickListener(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE EVENTI");
            Toast.makeText(getActivity().getApplicationContext(), "Non hai partecipato ancora a nessun evento", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        idEvento = customAdapterEvento.getItem(position).getIdEvento();

        i.putExtra("ID_EVENTO", idEvento);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentSingoloEventoArtista fragmentSingoloEventoArtista = new FragmentSingoloEventoArtista();
        ft.replace(R.id.frammento_da_inserire, fragmentSingoloEventoArtista);
        ft.addToBackStack(null);
        ft.commit();
    }
}
