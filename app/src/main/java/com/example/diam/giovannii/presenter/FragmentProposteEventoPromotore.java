package com.example.diam.giovannii.presenter;

import android.app.Activity;
import android.content.Context;
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
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentProposteEventoPromotore extends ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;

    ListView listViewProposteEventoPromotore;
    TextView nomePropostaEventoPromotore;
    JSONArray proposteEventoPromotore;
    int idPropostaEvento;
    String nomeProposta;

    CustomAdapterPropostaPromotore customAdapterPropostaPromotore;


    public FragmentProposteEventoPromotore() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_proposte_evento_promotore, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        listViewProposteEventoPromotore=v.findViewById(android.R.id.list);
        nomePropostaEventoPromotore=v.findViewById(R.id.textView_nome_proposta_evento_promotore);

        return v;
    }



    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("PROPOSTE_EVENTO_PROMOTORE")) {
            try {
                proposteEventoPromotore = new JSONArray(i.getStringExtra("PROPOSTE_EVENTO_PROMOTORE"));

                ArrayList<String> nomiProposte = new ArrayList<String>();
                ArrayList<Integer> idProposteEvento = new ArrayList<Integer>();

                customAdapterPropostaPromotore = new CustomAdapterPropostaPromotore(getActivity().getApplicationContext(),
                        R.layout.list_element_proposta_evento_promotore,
                        new ArrayList<PropostaEventoVisualizzata>());
                listViewProposteEventoPromotore.setAdapter(customAdapterPropostaPromotore);

                for (int j = 0; j < proposteEventoPromotore.length(); j++) {

                    nomeProposta = proposteEventoPromotore.getJSONObject(j).getString("nomePropostaEvento");
                    nomiProposte.add(nomeProposta);

                    idPropostaEvento = proposteEventoPromotore.getJSONObject(j).getInt("idPropostaEvento");
                    idProposteEvento.add(idPropostaEvento);

                    PropostaEventoVisualizzata miServi = new PropostaEventoVisualizzata(nomeProposta, idPropostaEvento);
                    customAdapterPropostaPromotore.add(miServi);

                }

                getListView().setOnItemClickListener(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE PROPOSTE EVENTO");
            Toast.makeText(getActivity().getApplicationContext(), "Non hai ancora creato nessuna proposta evento", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        idPropostaEvento = customAdapterPropostaPromotore.getItem(position).getIdProposta();
        i.putExtra("ID_PROPOSTA_EVENTO", idPropostaEvento);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentSingolaPropostaPromotore fragmentSingolaPropostaPromotore = new FragmentSingolaPropostaPromotore();
        ft.replace(R.id.frammento_da_inserire, fragmentSingolaPropostaPromotore);
        ft.addToBackStack(null);
        ft.commit();
    }
}
