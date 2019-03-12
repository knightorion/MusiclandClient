package com.example.diam.giovannii.presenter;

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

public class FragmentListaArtisti extends ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;


    ListView listViewArtisti;
    TextView nomeArtista;

    JSONArray artisti;

    int idArtistaSelezionato;
    String nomeArtistaStringa;

    CustomAdapterArtista customAdapterArtista;


    public FragmentListaArtisti() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_artisti, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();


        listViewArtisti = v.findViewById(android.R.id.list);
        nomeArtista = v.findViewById(R.id.textView_email_list_element_artista);

        return v;
    }




    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("LISTA_ARTISTI")) {
            try {
                artisti = new JSONArray(i.getStringExtra("LISTA_ARTISTI"));

                ArrayList<String> nicknameArtisti = new ArrayList<String>();
                ArrayList<Integer> idArtisti = new ArrayList<Integer>();

                customAdapterArtista = new CustomAdapterArtista(getActivity().getApplicationContext(),
                        R.layout.list_element_artista,
                        new ArrayList<ArtistaVisualizzato>());

                listViewArtisti.setAdapter(customAdapterArtista);

                for (int j = 0; j < artisti.length(); j++) {

                    nomeArtistaStringa = artisti.getJSONObject(j).getString("nickname");
                    nicknameArtisti.add(nomeArtistaStringa);

                    idArtistaSelezionato = artisti.getJSONObject(j).getInt("idArtista");
                    idArtisti.add(idArtistaSelezionato);

                    ArtistaVisualizzato miServi = new ArtistaVisualizzato(nomeArtistaStringa, idArtistaSelezionato);
                    customAdapterArtista.add(miServi);

                }

                getListView().setOnItemClickListener(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE ARTISTI");
            Toast.makeText(getActivity().getApplicationContext(), "Non sono presenti artisti sulla piattaforma", Toast.LENGTH_LONG).show();
        }


    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        idArtistaSelezionato = customAdapterArtista.getItem(position).getIdArtista();

        i.putExtra("ID_ARTISTA", idArtistaSelezionato);


        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentVisualizzaProfiloArtista fragmentVisualizzaProfiloArtista = new FragmentVisualizzaProfiloArtista();
        ft.replace(R.id.frammento_da_inserire, fragmentVisualizzaProfiloArtista);
        ft.addToBackStack(null);
        ft.commit();
    }
}
