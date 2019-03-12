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

public class FragmentListaBand extends ListFragment implements AdapterView.OnItemClickListener {

    Intent i;
    Bundle a;

    ListView listViewBand;

    TextView nomeBand;


    JSONArray band;

    String emailBandSelezionata;
    String nomeBandStringa;

    CustomAdapterBand customAdapterBand;

    public FragmentListaBand() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_band, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        listViewBand = v.findViewById(android.R.id.list);
        nomeBand = v.findViewById(R.id.textView_email_band_list_element);


        return v;
    }




    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        if (i.hasExtra("LISTA_BAND")) {
            try {
                band = new JSONArray(i.getStringExtra("LISTA_BAND"));

                ArrayList<String> nomiBand = new ArrayList<String>();
                ArrayList<String> emailBand = new ArrayList<String>();

                customAdapterBand = new CustomAdapterBand(getActivity().getApplicationContext(),
                        R.layout.list_element_band,
                        new ArrayList<BandVisualizzata>());

                listViewBand.setAdapter(customAdapterBand);

                for (int j = 0; j < band.length(); j++) {

                    nomeBandStringa = band.getJSONObject(j).getString("nomeBand");
                    nomiBand.add(nomeBandStringa);

                    emailBandSelezionata = band.getJSONObject(j).getString("email");
                    emailBand.add(emailBandSelezionata);

                    BandVisualizzata miServi = new BandVisualizzata(nomeBandStringa, emailBandSelezionata);
                    customAdapterBand.add(miServi);

                }


                getListView().setOnItemClickListener(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE BAND");
            Toast.makeText(getActivity().getApplicationContext(), "Non sono presenti band sulla piattaforma", Toast.LENGTH_LONG).show();
        }



    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        emailBandSelezionata = customAdapterBand.getItem(position).getEmailBand();

        i.putExtra("EMAIL_BAND", emailBandSelezionata);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentVisualizzaProfiloBand fragmentVisualizzaProfiloBand = new FragmentVisualizzaProfiloBand();
        ft.replace(R.id.frammento_da_inserire, fragmentVisualizzaProfiloBand);
        ft.addToBackStack(null);
        ft.commit();
    }
}
