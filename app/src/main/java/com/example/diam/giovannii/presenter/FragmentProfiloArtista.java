package com.example.diam.giovannii.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diam.giovannii.R;
import com.example.diam.giovannii.model.pojo.Artista;

public class FragmentProfiloArtista extends Fragment {

    Intent i;
    Bundle a;

    //  TextView emailArtista;
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

    Artista artista = new Artista();

    public FragmentProfiloArtista() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.visualizza_profilo_artista, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();


        artista.setNome(a.getString("NOME_ARTISTA"));
        artista.setCognome(a.getString("COGNOME_ARTISTA"));
        artista.setPassword(a.getString("PASSWORD_ARTISTA"));
        int etaA = a.getInt("ETA_ARTISTA");
        artista.setAge(a.getInt("ETA_ARTISTA"));
        artista.setLuogo(a.getString("LUOGO_ARTISTA"));
        artista.setNickname(a.getString("NICKNAME_ARTISTA"));
        artista.setSesso(a.getString("SESSO_ARTISTA"));


        biografiaArtista = v.findViewById(R.id.textView_biografia_artista_visualizzabile);
        numeroTelefonoArtista = v.findViewById(R.id.textView_numero_telefono_artista_visualizzabile);
        linkSocialArtista = v.findViewById(R.id.textView_link_social_artista_visualizzabile);


        if (!a.getString("TELEFONO_ARTISTA").equals("")) {
            artista.setNumeroTelefono(a.getString("TELEFONO_ARTISTA"));
            numeroTelefonoArtista.setText(a.getString("TELEFONO_ARTISTA"));
        } else {
            artista.setNumeroTelefono("");
        }
        if (!a.getString("BIOGRAFIA_ARTISTA").equals("")) {
            artista.setBiografia(a.getString("BIOGRAFIA_ARTISTA"));
            biografiaArtista.setText(a.getString("BIOGRAFIA_ARTISTA"));
        } else {
            artista.setBiografia("");
        }

        if (!a.getString("LINK_ARTISTA").equals("")) {
            artista.setLinkSocial(a.getString("LINK_ARTISTA"));
            linkSocialArtista.setText(a.getString("LINK_ARTISTA"));
        } else {
            artista.setLinkSocial("");
        }


        String nomeA = artista.getNome();
        nomeArtista = v.findViewById(R.id.textView_nome_artista_visualizzabile);
        nomeArtista.setText(nomeA);

        String cognomeA = artista.getCognome();
        cognomeArtista = v.findViewById(R.id.textView_cognome_artista_visualizzabile);
        cognomeArtista.setText(cognomeA);


        etaArtista = v.findViewById(R.id.textView_eta_artista_visualizzabile);
        etaArtista.setText(String.valueOf(etaA));

        String nicknameA = artista.getNickname();
        nicknameArtista = v.findViewById(R.id.textView_nickname_artista_visualizzabile);
        nicknameArtista.setText(nicknameA);

        localitaArtista = v.findViewById(R.id.textView_località_artista_visualizzabile);
        String localitaA = artista.getLuogo();
        localitaArtista.setText(localitaA);


        String sessoA = artista.getSesso();
        sessoArtista = v.findViewById(R.id.textView_sesso_artista_visualizzabile);
        if (sessoA.equalsIgnoreCase("M")) {
            sessoArtista.setText("M");
        } else {
            sessoArtista.setText("F");
        }


        String[] arrayGeneri = a.getStringArray("GENERI_ARTISTA");
        listViewGeneriArtista = v.findViewById(R.id.listView_generi_musicali);
        ArrayAdapter<String> adapterGeneriMusicali = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.list_element_generi_musicali_artista,
                R.id.textView_genere_musicali_artista_uno,
                arrayGeneri);
        listViewGeneriArtista.setAdapter(adapterGeneriMusicali);


        String[] arrayStrumenti = a.getStringArray("STRUMENTI_ARTISTA");
        listViewStrumentiArtista = v.findViewById(R.id.listView_strumenti_musicali_artista_visualizzabili);
        ArrayAdapter<String> adapterStrumentiMusicali = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.list_element_strumenti_musicali_artista,
                R.id.textView_list_element_strumenti_musicali_artista,
                arrayStrumenti);
        listViewStrumentiArtista.setAdapter(adapterStrumentiMusicali);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
    }


}
