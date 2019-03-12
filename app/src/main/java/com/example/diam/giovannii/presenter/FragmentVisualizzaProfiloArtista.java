package com.example.diam.giovannii.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diam.giovannii.R;

import org.json.JSONArray;
import org.json.JSONException;

public class FragmentVisualizzaProfiloArtista extends Fragment{

    Intent i;
    Bundle a;

    TextView emailArtista;
    TextView nomeArtista;
    TextView cognomeArtista;
    TextView etaArtista;
    TextView nicknameArtista;
    TextView sessoArtista;
    TextView localitaArtista;

    TextView biografiaArtista;
    TextView numeroTelefonoArtista;
    TextView linkSocialArtista;

    ListView listViewGeneriArtista;
    ListView listViewStrumentiArtista;

    public FragmentVisualizzaProfiloArtista() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profilo_artista, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        int idArtistaSelezionato = a.getInt("ID_ARTISTA");


        emailArtista = v.findViewById(R.id.textView_email_artista_visualizzabile);
        biografiaArtista = v.findViewById(R.id.textView_biografia_artista_visualizzabile);
        numeroTelefonoArtista = v.findViewById(R.id.textView_numero_telefono_artista_visualizzabile);
        linkSocialArtista = v.findViewById(R.id.textView_link_social_artista_visualizzabile);


        nomeArtista = v.findViewById(R.id.textView_nome_artista_visualizzabile);
        cognomeArtista = v.findViewById(R.id.textView_cognome_artista_visualizzabile);

        etaArtista = v.findViewById(R.id.textView_eta_artista_visualizzabile);
        nicknameArtista = v.findViewById(R.id.textView_nickname_artista_visualizzabile);

        localitaArtista = v.findViewById(R.id.textView_località_artista_visualizzabile);
        sessoArtista = v.findViewById(R.id.textView_sesso_artista_visualizzabile);


        listViewGeneriArtista = v.findViewById(R.id.listView_generi_musicali);
        listViewStrumentiArtista = v.findViewById(R.id.listView_strumenti_musicali_artista_visualizzabili);


        try {
            JSONArray listaArtisti = new JSONArray(i.getStringExtra("LISTA_ARTISTI"));
            for (int j = 0; j < listaArtisti.length(); j++) {
                if (listaArtisti.getJSONObject(j).getInt("idArtista")==idArtistaSelezionato) {

                    String emailArtistaSelezionato = listaArtisti.getJSONObject(j).getString("email");
                    emailArtista.setText(emailArtistaSelezionato);

                    String nomeArtistaS = listaArtisti.getJSONObject(j).getString("nome");
                    nomeArtista.setText(nomeArtistaS);

                    String cognomeArtistaS= listaArtisti.getJSONObject(j).getString("cognome");
                    cognomeArtista.setText(cognomeArtistaS);

                    int etaArtistaI = listaArtisti.getJSONObject(j).getInt("age");
                    etaArtista.setText(String.valueOf(etaArtistaI));

                    String biografiaArtistaS = listaArtisti.getJSONObject(j).getString("biografia");
                    biografiaArtista.setText(biografiaArtistaS);

                    String sessoA = listaArtisti.getJSONObject(j).getString("sesso");
                    if (sessoA.equalsIgnoreCase("m")) {
                        sessoArtista.setText("M");
                    } else {
                        sessoArtista.setText("F");
                    }

                    String luogoS = listaArtisti.getJSONObject(j).getString("luogo");
                    localitaArtista.setText(luogoS);
                    String telefonoS = listaArtisti.getJSONObject(j).getString("numeroTelefono");
                    numeroTelefonoArtista.setText(telefonoS);
                    String linkSocialS = listaArtisti.getJSONObject(j).getString("linkSocial");
                    linkSocialArtista.setText(linkSocialS);

                    String generiJSON = listaArtisti.getJSONObject(j).getString("generiMusicali");
                    String[] arrayGeneri = generiJSON.split(",");
                    ArrayAdapter<String> adapterGeneriMusicali = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                            R.layout.list_element_generi_musicali_artista,
                            R.id.textView_genere_musicali_artista_uno,
                            arrayGeneri);
                    listViewGeneriArtista.setAdapter(adapterGeneriMusicali);


                    String strumentiJSON = listaArtisti.getJSONObject(j).getString("strumentiSuonati");
                    String[] arrayStrumenti = strumentiJSON.split(",");
                    ArrayAdapter<String> adapterStrumentiMusicali = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                            R.layout.list_element_strumenti_musicali_artista,
                            R.id.textView_list_element_strumenti_musicali_artista,
                            arrayStrumenti);
                    listViewStrumentiArtista.setAdapter(adapterStrumentiMusicali);

                }



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return v;
    }




    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);


    }




}
