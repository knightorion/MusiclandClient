package com.example.diam.giovannii.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diam.giovannii.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentSingoloEventoBand extends Fragment {


    Intent i;
    Bundle a;


    TextView nomeEvento;
    TextView nomeLocaleEvento;

    //ListView bandEvento;

    TextView viaEvento;
    TextView cittaEvento;
    TextView provinciaEvento;
    TextView regioneEvento;

    TextView oraEvento;
    TextView minutiEvento;

    TextView giornoEvento;
    TextView meseEvento;
    TextView annoEvento;

    ListView generiSuonatiEvento;

    TextView descrizioneEvento;


    public FragmentSingoloEventoBand() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pagina_evento_band_e_artisti, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        int idEventoSelezionato = a.getInt("ID_EVENTO");

        nomeEvento=v.findViewById(R.id.textView_nome_evento);
        nomeLocaleEvento=v.findViewById(R.id.textView_nome_locale_evento);
        viaEvento=v.findViewById(R.id.textView_via_evento);
        cittaEvento=v.findViewById(R.id.textView_citta_evento);
        provinciaEvento=v.findViewById(R.id.textView_provincia_evento);
        regioneEvento=v.findViewById(R.id.textView_regione_evento);
        oraEvento=v.findViewById(R.id.textView_ora_evento_visualizzato);
        minutiEvento=v.findViewById(R.id.textView_minuti_evento_bea);
        giornoEvento=v.findViewById(R.id.textView_giorno_evento_visualizzato);
        meseEvento=v.findViewById(R.id.textView_mese_evento_visualizzato);
        annoEvento=v.findViewById(R.id.textView_anno_evento_visualizzato);
        generiSuonatiEvento=v.findViewById(R.id.listView_generi_suonati_evento);
        descrizioneEvento=v.findViewById(R.id.textView_descrizione_evento_visualizzato);



        if (!i.getStringExtra("EVENTI_BAND").equals("")) {
            try {
                JSONArray eventiBand = new JSONArray(i.getStringExtra("EVENTI_BAND"));
                //se non va fai a.getString("EVENTI_ARTISTA")
                for(int j = 0; j<eventiBand.length(); j++) {
                    if (eventiBand.getJSONObject(j).getInt("idEvento") == idEventoSelezionato) {
                        String nomeLocaleP = eventiBand.getJSONObject(j).getString("nomeLocale");
                        nomeLocaleEvento.setText(nomeLocaleP);
                        String nomeEventoP = eventiBand.getJSONObject(j).getString("nomeEvento");
                        nomeEvento.setText(nomeEventoP);

                        String dataP = eventiBand.getJSONObject(j).getString("dataEvento");
                        String giornoP = dataP.substring(0, dataP.indexOf("-"));
                        String meseP = dataP.substring(dataP.indexOf("-") + 1, dataP.lastIndexOf("-"));
                        String annoP = dataP.substring(dataP.lastIndexOf("-") + 1);
                        giornoEvento.setText(giornoP);
                        meseEvento.setText(meseP);
                        annoEvento.setText(annoP);

                        String orarioP = eventiBand.getJSONObject(j).getString("orarioEvento");
                        String ora = orarioP.substring(0, orarioP.indexOf("-"));
                        String minuti = orarioP.substring(orarioP.indexOf("-") + 1);
                        oraEvento.setText(ora);
                        minutiEvento.setText(minuti);


                        String indirizzoJSON = eventiBand.getJSONObject(j).getString("indirizzo");
                        String[] indirizzo = indirizzoJSON.split(",");

                        String provinciaP = indirizzo[0].substring(13);

                        String regioneP = indirizzo[1].substring(9);

                        String viaP = indirizzo[2].substring(6);


                        String cittaP = indirizzo[3];
                        int indiceFineCitta = cittaP.length()-1;

                        regioneEvento.setText(regioneP);
                        viaEvento.setText(viaP);
                        cittaEvento.setText(cittaP.substring(8,indiceFineCitta));
                        provinciaEvento.setText(provinciaP);


                        String generiSuonatiEventoJson = eventiBand.getJSONObject(j).getString("generiSuonati");
                        String[] generiSuonatiEventoP = generiSuonatiEventoJson.split(",");

                        ArrayAdapter<String> adapterGeneriMusicali = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.list_generi_musicali_evento,
                                R.id.genere_musicale_suonato,
                                generiSuonatiEventoP);

                        generiSuonatiEvento.setAdapter(adapterGeneriMusicali);


                        String descrizioneP = eventiBand.getJSONObject(j).getString("descrizione");
                        descrizioneEvento.setText(descrizioneP);
                    }
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("NIENTE EVENTI");
            Toast.makeText(getActivity().getApplicationContext(), "Non dovresti essere qui!", Toast.LENGTH_LONG).show();
        }


        return v;
    }



    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
