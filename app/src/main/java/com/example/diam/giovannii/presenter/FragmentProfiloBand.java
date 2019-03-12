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

import com.example.diam.giovannii.R;
import com.example.diam.giovannii.model.pojo.Band;

public class FragmentProfiloBand extends Fragment {

    Intent i;
    Bundle a;
    Band band = new Band();


    TextView nomeBand;
    TextView numeroComponentiBand;
    TextView localitaBand;
    ListView generiMusicaliBand;
    TextView biografiaBand;
    TextView numeroTelefonoBand;
    TextView linkSocialBand;

    public FragmentProfiloBand() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.visualizza_profilo_band, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        nomeBand=v.findViewById(R.id.textView_nome_band_visualizzabile);
        numeroComponentiBand=v.findViewById(R.id.textView_numero_singoli_componenti);
        localitaBand=v.findViewById(R.id.textView_località_band_visualizzabile);
        generiMusicaliBand=v.findViewById(R.id.listView_generi_musicali);
        biografiaBand=v.findViewById(R.id.textView_biografia_band_visualizzabile);
        numeroTelefonoBand=v.findViewById(R.id.textView_numero_telefono_band_visualizzabile);
        linkSocialBand=v.findViewById(R.id.textView_link_social_una_band_visualizzabile);


        System.out.println("UNO " + a.getString("NOME_BAND"));

        band.setNomeBand(a.getString("NOME_BAND"));

        System.out.println("DUE " + a.getString("NOME_BAND"));

        band.setNumeroComponenti(a.getInt("NUMERO_COMPONENTI_BAND"));
        int numComponenti = band.getNumeroComponenti();
        String componentiBand = String.valueOf(numComponenti);
        band.setEmail(a.getString("EMAIL_BAND"));
        band.setPassword(a.getString("PASSWORD_BAND"));
        band.setLuogo(a.getString("LUOGO_BAND"));


        nomeBand.setText(band.getNomeBand());
        numeroComponentiBand.setText(componentiBand);
        localitaBand.setText(band.getLuogo());


        if(!a.getString("TELEFONO_BAND").equals("")) {
            band.setNumeroTelefono(a.getString("TELEFONO_BAND"));
            numeroTelefonoBand.setText(a.getString("TELEFONO_BAND"));
        }else{
            band.setNumeroTelefono("");
        }
        if(!a.getString("BIOGRAFIA_BAND").equals("")) {
            band.setBiografia(a.getString("BIOGRAFIA_BAND"));
            biografiaBand.setText(a.getString("BIOGRAFIA_BAND"));
        }
        else {
            band.setBiografia("");
        }

        if(!a.getString("LINK_BAND").equals("")) {
            band.setLinkSocial(a.getString("LINK_BAND"));
            linkSocialBand.setText(a.getString("LINK_BAND"));
        }
        else{
            band.setLinkSocial("");
        }

        String [] arrayGeneri = a.getStringArray("GENERI_BAND");
        ArrayAdapter<String> adapterGeneriMusicali= new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.list_element_generi_musicali_artista,R.id.textView_genere_musicali_artista_uno,arrayGeneri);
        generiMusicaliBand.setAdapter(adapterGeneriMusicali);

        System.out.println();

        return v;
    }

    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
    }


}
