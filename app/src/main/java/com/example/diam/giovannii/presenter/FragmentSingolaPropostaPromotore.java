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

public class FragmentSingolaPropostaPromotore extends Fragment {


    Intent i;
    Bundle a;

    TextView nomePropostaEvento;
    TextView nomeLocalePropostaEvento;

    TextView viaPropostaEvento;
    TextView cittaPropostaEvento;
    TextView provinciaPropostaEvento;
    TextView regionePropostaEvento;

    TextView giornoPropostaEvento;
    TextView mesePropostaEvento;
    TextView annoPropostaEvento;

    ListView genereMusicaleRichiesto;

    TextView infoPropostaEvento;


    ListView artistiInteressati;
    ListView bandInteressate;

    TextView oraPropostaEvento;
    TextView minutiPropostaEvento;

    public FragmentSingolaPropostaPromotore() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pagina_proposta_evento_promotore, container, false);


        i = getActivity().getIntent();
        a = i.getExtras();

        int idPropostaSelezionata = a.getInt("ID_PROPOSTA_EVENTO");


        nomePropostaEvento = v.findViewById(R.id.textView_nome_proposta_evento);
        nomeLocalePropostaEvento = v.findViewById(R.id.textView_nome_locale_proposta_evento);

        viaPropostaEvento = v.findViewById(R.id.textView_via_proposta_evento);
        cittaPropostaEvento = v.findViewById(R.id.texView_citta_proposta_evento);
        provinciaPropostaEvento = v.findViewById(R.id.textView_provincia_proposta_evento);
        regionePropostaEvento = v.findViewById(R.id.textView_regione_proposta_evento);

        giornoPropostaEvento = v.findViewById(R.id.textView_giorno_proposta_evento);
        mesePropostaEvento = v.findViewById(R.id.textView_mese_proposta_evento);
        annoPropostaEvento = v.findViewById(R.id.textView_anno_proposta_evento);

        genereMusicaleRichiesto = v.findViewById(R.id.listView_generi_suonati);

        infoPropostaEvento = v.findViewById(R.id.textView_descrizione_serata_proposta_evento);

        artistiInteressati = v.findViewById(R.id.listView_nome_artisti_interessati_proposta_evento);
        bandInteressate = v.findViewById(R.id.listView_band_interessate_proposta_evento);

        oraPropostaEvento = v.findViewById(R.id.textView_ora_proposta_evento);
        minutiPropostaEvento = v.findViewById(R.id.textView_minuti_proposta_evento_promo);

        System.out.println("IDDDDDDDDDDDDDDD" + idPropostaSelezionata);

        if (!i.getStringExtra("PROPOSTE_EVENTO_PROMOTORE").equals("")) {
            try {
                JSONArray eventiPromotore = new JSONArray(i.getStringExtra("PROPOSTE_EVENTO_PROMOTORE"));
                for (int j = 0; j < eventiPromotore.length(); j++) {
                    if (eventiPromotore.getJSONObject(j).getInt("idPropostaEvento") == idPropostaSelezionata) {

                        System.out.println("WEEEEEEEEEEEEEEEEEE" + eventiPromotore.getJSONObject(j));

                        String nomeLocaleP = eventiPromotore.getJSONObject(j).getString("nomeLocale");
                        nomeLocalePropostaEvento.setText(nomeLocaleP);

                        String nomeEventoP = eventiPromotore.getJSONObject(j).getString("nomePropostaEvento");
                        nomePropostaEvento.setText(nomeEventoP);

                        String dataP = eventiPromotore.getJSONObject(j).getString("dataPropostaEvento");
                        String giornoP = dataP.substring(0, dataP.indexOf("-"));
                        String meseP = dataP.substring(dataP.indexOf("-") + 1, dataP.lastIndexOf("-"));
                        String annoP = dataP.substring(dataP.lastIndexOf("-") + 1);
                        giornoPropostaEvento.setText(giornoP);
                        mesePropostaEvento.setText(meseP);
                        annoPropostaEvento.setText(annoP);

                        String orarioP = eventiPromotore.getJSONObject(j).getString("orarioPropostaEvento");
                        String ora = orarioP.substring(0, orarioP.indexOf("-"));
                        String minuti = orarioP.substring(orarioP.indexOf("-") + 1);
                        oraPropostaEvento.setText(ora);
                        minutiPropostaEvento.setText(minuti);


                        String indirizzoJSON = eventiPromotore.getJSONObject(j).getString("indirizzo");
                        String[] indirizzo = indirizzoJSON.split(",");

                        String provinciaP = indirizzo[0].substring(13);

                        String regioneP = indirizzo[1].substring(10);

                        String viaP = indirizzo[2].substring(6);


                        String cittaP = indirizzo[3];
                        int indiceFineCitta = cittaP.length()-1;

                        regionePropostaEvento.setText(regioneP);
                        viaPropostaEvento.setText(viaP);
                        cittaPropostaEvento.setText(cittaP.substring(8,indiceFineCitta));
                        provinciaPropostaEvento.setText(provinciaP);


                        String generiSuonatiEventoJson = eventiPromotore.getJSONObject(j).getString("generiRichiesti");
                        String[] generiSuonatiEventoP = generiSuonatiEventoJson.split(",");

                        ArrayAdapter<String> adapterGeneriMusicali = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.list_generi_musicali_evento,
                                R.id.genere_musicale_suonato,
                                generiSuonatiEventoP);

                        genereMusicaleRichiesto.setAdapter(adapterGeneriMusicali);


                        String descrizioneP = eventiPromotore.getJSONObject(j).getString("descrizione");
                        infoPropostaEvento.setText(descrizioneP);



                        String artistiInteressatiJson = eventiPromotore.getJSONObject(j).getString("listaArtistiInteressati");
                        System.out.println("ZANZANZANZANZAZNA"+artistiInteressatiJson);
                        String[] artistiPartecipanti = artistiInteressatiJson.split(",");
                        ArrayAdapter<String> adapterArtistiInteressati = new ArrayAdapter<String>(
                                getActivity().getApplicationContext(),
                                R.layout.list_element_artista,
                                R.id.textView_email_list_element_artista,
                                artistiPartecipanti );
                        artistiInteressati.setAdapter(adapterArtistiInteressati);


                        String bandInteressateJson = eventiPromotore.getJSONObject(j).getString("listaBandInteressate");
                        System.out.println("OOOOOOOOOOOOOOO"+bandInteressateJson);
                        String [] bandPartecipanti = bandInteressateJson.split(",");
                        ArrayAdapter<String> adapterBandInteressate = new ArrayAdapter<String>(
                                getActivity().getApplicationContext(),
                                R.layout.list_element_band,
                                R.id.textView_email_band_list_element,
                                bandPartecipanti );
                        bandInteressate.setAdapter(adapterBandInteressate);

                    }
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
