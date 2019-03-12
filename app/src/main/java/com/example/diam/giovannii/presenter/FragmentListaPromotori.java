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

public class FragmentListaPromotori extends ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;

    ListView listViewPromotori;

    TextView nomePromotore;

    JSONArray promotori;
    String emailPromotoreSelezionato;

    String nomePromotoreStringa;

    CustomAdapterPromotore customAdapterPromotore;

    public FragmentListaPromotori() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_promotori, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        listViewPromotori = v.findViewById(android.R.id.list);
        nomePromotore = v.findViewById(R.id.textView_email_promotore_list_element_campo);

        return v;
    }




    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("LISTA_PROMOTORI")) {
            try {
                promotori = new JSONArray(i.getStringExtra("LISTA_PROMOTORI"));

                ArrayList<String> nomiPromotori = new ArrayList<String>();
                ArrayList<String> emailPromotori = new ArrayList<String>();

                customAdapterPromotore = new CustomAdapterPromotore(getActivity().getApplicationContext(),
                        R.layout.list_element_promotore,
                        new ArrayList<PromotoreVisualizzato>());

                listViewPromotori.setAdapter(customAdapterPromotore);

                for (int j = 0; j < promotori.length(); j++) {
                    nomePromotoreStringa = promotori.getJSONObject(j).getString("nome");
                    emailPromotoreSelezionato = promotori.getJSONObject(j).getString("email");
                    nomiPromotori.add(nomePromotoreStringa);
                    emailPromotori.add(emailPromotoreSelezionato);

                    PromotoreVisualizzato miServi = new PromotoreVisualizzato(nomePromotoreStringa, emailPromotoreSelezionato);
                    customAdapterPromotore.add(miServi);
                }


                getListView().setOnItemClickListener(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE PROMOTORI");
            Toast.makeText(getActivity().getApplicationContext(), "Nessun promotore registrato alla piattaforma", Toast.LENGTH_LONG).show();
        }

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        emailPromotoreSelezionato = customAdapterPromotore.getItem(position).getEmailPromotore();

        i.putExtra("EMAIL_PROMOTORE", emailPromotoreSelezionato);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentVisualizzaProfiloPromotore fragmentVisualizzaProfiloPromotore = new FragmentVisualizzaProfiloPromotore();
        ft.replace(R.id.frammento_da_inserire, fragmentVisualizzaProfiloPromotore);
        ft.addToBackStack(null);
        ft.commit();
    }
}
