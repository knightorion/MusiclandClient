package com.example.diam.giovannii.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diam.giovannii.R;
import com.example.diam.giovannii.model.pojo.Promotore;

import org.json.JSONArray;
import org.json.JSONException;

public class FragmentProfiloPromotore extends Fragment {

    Intent i;
    Bundle a;


    TextView nomePromotore;
    TextView cognomePromotore;
    TextView etaPromotore;
    //TextView emailPromotore;
    TextView biografiaPromotore;
    TextView sessoPromotore;
    TextView luogoPromotore;
    TextView telefonoPromotore;
    TextView linkSocialPromotore;

    Promotore promotore = new Promotore();


    public FragmentProfiloPromotore() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.visualizza_profilo_promotore, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        nomePromotore=v.findViewById(R.id.textView_nome_promotore_visualizzabile);
        cognomePromotore=v.findViewById(R.id.textView_cognome_promotore_visualizzabile);
        etaPromotore=v.findViewById(R.id.textView_eta_promotore_visualizzabile);
        biografiaPromotore=v.findViewById(R.id.textView_biografia_promotore_visualizzabile);
        sessoPromotore=v.findViewById(R.id.textView_sesso_promotore_visualizzabile);
        luogoPromotore=v.findViewById(R.id.textView_localita_promtoore_visualizzabile);
        telefonoPromotore=v.findViewById(R.id.textView_numero_telefono_promotore);
        linkSocialPromotore=v.findViewById(R.id.textView_link_social_promotore_visualizzabile);

        promotore.setEmail(a.getString("EMAIL_PROMOTORE"));
        promotore.setPassword(a.getString("PASSWORD_PROMOTORE"));
        promotore.setAge(a.getInt("ETA_PROMOTORE"));



        if(!a.getString("BIOGRAFIA_PROMOTORE").equals("")) {
            promotore.setBiografia(a.getString("BIOGRAFIA_PROMOTORE"));
            biografiaPromotore.setText(a.getString("BIOGRAFIA_PROMOTORE"));
        }
        else {
            promotore.setBiografia("");
        }


        if(!a.getString("LINK_PROMOTORE").equals("")) {
            promotore.setLinkSocial(a.getString("LINK_PROMOTORE"));
            linkSocialPromotore.setText(a.getString("LINK_PROMOTORE"));
        }
        else{
            promotore.setLinkSocial("");
        }
        promotore.setLuogo(a.getString("LUOGO_PROMOTORE"));

        if(!a.getString("TELEFONO_PROMOTORE").equals("")) {
            promotore.setNumeroTelefono(a.getString("TELEFONO_PROMOTORE"));
            telefonoPromotore.setText(a.getString("TELEFONO_PROMOTORE"));
        }
        else{
            promotore.setNumeroTelefono("");
        }
        promotore.setNome(a.getString("NOME_PROMOTORE"));
        promotore.setCognome(a.getString("COGNOME_PROMOTORE"));
        promotore.setSesso(a.getString("SESSO_PROMOTORE"));


        nomePromotore.setText(a.getString("NOME_PROMOTORE"));
        cognomePromotore.setText(a.getString("COGNOME_PROMOTORE"));
        int etaP = a.getInt("ETA_PROMOTORE");
        etaPromotore.setText(String.valueOf(etaP));
        luogoPromotore.setText(a.getString("LUOGO_PROMOTORE"));

        String sessoP = promotore.getSesso();
        if (sessoP.equalsIgnoreCase("m")) {
            sessoPromotore.setText("M");
        } else {
            sessoPromotore.setText("F");
        }


        return v;
    }



    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
    }



}


