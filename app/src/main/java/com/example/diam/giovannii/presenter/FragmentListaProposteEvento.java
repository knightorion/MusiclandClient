package com.example.diam.giovannii.presenter;

import android.content.Intent;
import android.os.Bundle;
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

public class FragmentListaProposteEvento extends ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;

    ListView listViewProposteEvento;

    TextView nomePropostaEvento;

    JSONArray proposteEvento;
    int idPropostaEvento;
    String nomePropostaEventoStringa;

    CustomAdapterProposta customAdapterProposta;

    public FragmentListaProposteEvento() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_proposte_evento, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();


        listViewProposteEvento = v.findViewById(android.R.id.list);
        nomePropostaEvento = v.findViewById(R.id.textView_nome_proposta_evento);

        return v;
    }




    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("PROPOSTE_EVENTO_PROMOTORE")) {
            try {
                proposteEvento = new JSONArray(i.getStringExtra("PROPOSTE_EVENTO_PROMOTORE"));

                ArrayList<String> nomiProposteEvento = new ArrayList<String>();
                ArrayList<Integer> idProposteEvento = new ArrayList<Integer>();

                customAdapterProposta = new CustomAdapterProposta(getActivity().getApplicationContext(),
                        R.layout.list_element_proposta_evento,
                        new ArrayList<PropostaEventoVisualizzata>());
                listViewProposteEvento.setAdapter(customAdapterProposta);

                for (int j = 0; j < proposteEvento.length(); j++) {

                    nomePropostaEventoStringa = proposteEvento.getJSONObject(j).getString("nomePropostaEvento");
                    nomiProposteEvento.add(nomePropostaEventoStringa);

                    idPropostaEvento = proposteEvento.getJSONObject(j).getInt("idPropostaEvento");
                    idProposteEvento.add(idPropostaEvento);

                    PropostaEventoVisualizzata miServi = new PropostaEventoVisualizzata(nomePropostaEventoStringa, idPropostaEvento);
                    customAdapterProposta.add(miServi);

                }


                getListView().setOnItemClickListener(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE PROPOSTE EVENTO");
            Toast.makeText(getActivity().getApplicationContext(), "Non è ancora stata pubblicata alcuna proposta", Toast.LENGTH_LONG).show();
        }


    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idPropostaEvento = customAdapterProposta.getItem(position).getIdProposta();

        i.putExtra("ID_PROPOSTA_EVENTO", idPropostaEvento);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentSingolaProposta fragmentSingolaProposta = new FragmentSingolaProposta();
        ft.replace(R.id.frammento_da_inserire, fragmentSingolaProposta);
        ft.addToBackStack(null);
        ft.commit();
    }
}
