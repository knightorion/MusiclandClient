package com.example.diam.giovannii.presenter;

import android.app.Activity;
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

public class FragmentSingoloEventoPromotore extends Fragment {

    Intent i;
    Bundle a;
    TextView nomeEvento;
    TextView nomeLocaleEvento;
    TextView giornoEvento;
    TextView meseEvento;
    TextView annoEvento;

    ListView bandEsibisconoEvento;
    ListView artistiEsibisconoEvento;

    TextView regioneEvento;
    TextView viaEvento;
    TextView cittaEvento;
    TextView provinciaEvento;

    ListView generiSuonatiEvento;

    TextView descrizioneEvento;
    TextView oraEvento;
    TextView minutiEvento;

    public FragmentSingoloEventoPromotore() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pagina_evento_promotore, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        int idEventoSelezionato = a.getInt("ID_EVENTO");


        nomeEvento = v.findViewById(R.id.textView_nome_evento_visualizzabile);
        nomeLocaleEvento = v.findViewById(R.id.textView_nome_locale_evento_visualizzato);
        giornoEvento = v.findViewById(R.id.textView_giorno_evento_visualizzibile);
        meseEvento = v.findViewById(R.id.textView_mese_evento_visualizzabile);
        annoEvento = v.findViewById(R.id.textView_anno_evento_visualizzabile);

        bandEsibisconoEvento = v.findViewById(R.id.listView_band_esibiscono);
        artistiEsibisconoEvento = v.findViewById(R.id.listView_artisti_esibiscono);

        generiSuonatiEvento = v.findViewById(R.id.listView_generi_suonati_evento);

        descrizioneEvento = v.findViewById(R.id.textView_descrizione_evento_visualizzabile);

        //localitaEvento = v.findViewById(R.id.localita_oggi);
        viaEvento=v.findViewById(R.id.textView_via_evento_visualizzato);
        cittaEvento=v.findViewById(R.id.textView_citta_evento_visualizzato);
        provinciaEvento=v.findViewById(R.id.textView_provincia_evento_visualizzato);
        regioneEvento=v.findViewById(R.id.textView_regione_evento_visualizzabile);


        minutiEvento = v.findViewById(R.id.textView_minuti_evento_visualizzato);
        oraEvento = v.findViewById(R.id.textView_ora_evento_visualizzabile);

        if (!i.getStringExtra("EVENTI_PROMOTORE").equals("")) {
            try {
                JSONArray eventiPromotore = new JSONArray(i.getStringExtra("EVENTI_PROMOTORE"));
                for (int j = 0; j < eventiPromotore.length(); j++) {
                    if (eventiPromotore.getJSONObject(j).getInt("idEvento") == idEventoSelezionato) {
                        System.out.println("WEEEEEEEEEEEEEEEEEE" + eventiPromotore.getJSONObject(j));
                        String nomeLocaleP = eventiPromotore.getJSONObject(j).getString("nomeLocale");
                        nomeLocaleEvento.setText(nomeLocaleP);
                        String nomeEventoP = eventiPromotore.getJSONObject(j).getString("nomeEvento");
                        nomeEvento.setText(nomeEventoP);
                        String dataP = eventiPromotore.getJSONObject(j).getString("dataEvento");
                        String giornoP = dataP.substring(0, dataP.indexOf("-"));
                        String meseP = dataP.substring(dataP.indexOf("-") + 1, dataP.lastIndexOf("-"));
                        String annoP = dataP.substring(dataP.lastIndexOf("-") + 1);
                        giornoEvento.setText(giornoP);
                        meseEvento.setText(meseP);
                        annoEvento.setText(annoP);

                        String orarioP = eventiPromotore.getJSONObject(j).getString("orarioEvento");
                        String ora = orarioP.substring(0, orarioP.indexOf("-"));
                        String minuti = orarioP.substring(orarioP.indexOf("-") + 1);
                        oraEvento.setText(ora);
                        minutiEvento.setText(minuti);


                        String indirizzoJSON = eventiPromotore.getJSONObject(j).getString("indirizzo");
                        String[] indirizzo = indirizzoJSON.split(",");

                        String provinciaP = indirizzo[0].substring(13);

                        String regioneP = indirizzo[1].substring(10);

                        String viaP = indirizzo[2].substring(6);


                        String cittaP = indirizzo[3];
                        int indiceFineCitta = cittaP.length()-1;

                        regioneEvento.setText(regioneP);
                        viaEvento.setText(viaP);
                        cittaEvento.setText(cittaP.substring(8,indiceFineCitta));
                        provinciaEvento.setText(provinciaP);

                        String descrizioneP = eventiPromotore.getJSONObject(j).getString("descrizione");
                        descrizioneEvento.setText(descrizioneP);


                        String generiSuonatiEventoJson = eventiPromotore.getJSONObject(j).getString("generiSuonati");
                        String[] generiSuonatiEventoP = generiSuonatiEventoJson.split(",");

                        ArrayAdapter<String> adapterGeneriMusicali = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.list_generi_musicali_evento,
                                R.id.genere_musicale_suonato,
                                generiSuonatiEventoP);

                        generiSuonatiEvento.setAdapter(adapterGeneriMusicali);


                        String artistiPartecipantiJson = eventiPromotore.getJSONObject(j).getString("artistiPartecipanti");
                        System.out.println("ZANZANZANZANZAZNA"+artistiPartecipantiJson);
                        String[] artistiPartecipanti = artistiPartecipantiJson.split(",");
                        ArrayAdapter<String> adapterArtistiPartecipanti = new ArrayAdapter<String>(
                                getActivity().getApplicationContext(),
                                R.layout.list_element_artista,
                                R.id.textView_email_list_element_artista,
                                artistiPartecipanti );
                        artistiEsibisconoEvento.setAdapter(adapterArtistiPartecipanti);


                        String bandPartecipantiJson = eventiPromotore.getJSONObject(j).getString("bandPartecipanti");
                        System.out.println("OOOOOOOOOOOOOOO"+bandPartecipantiJson);
                        String [] bandPartecipanti = bandPartecipantiJson.split(",");
                        ArrayAdapter<String> adapterBandPartecipanti = new ArrayAdapter<String>(
                                getActivity().getApplicationContext(),
                                R.layout.list_element_band,
                                R.id.textView_email_band_list_element,
                                bandPartecipanti );
                        bandEsibisconoEvento.setAdapter(adapterBandPartecipanti);




                    }

                    //JSON artistiPartecipanti, bandPartecipanti

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return v;
    }


    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
    }


}
