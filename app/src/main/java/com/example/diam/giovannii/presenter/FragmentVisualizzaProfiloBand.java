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
import com.example.diam.giovannii.model.pojo.Band;

import org.json.JSONArray;
import org.json.JSONException;

public class FragmentVisualizzaProfiloBand extends Fragment {

    Intent i;
    Bundle a;

    TextView emailBand;

    TextView nomeBand;
    TextView numeroComponentiBand;
    TextView localitaBand;
    ListView generiMusicaliBand;
    TextView biografiaBand;
    TextView numeroTelefonoBand;
    TextView linkSocialBand;

    public FragmentVisualizzaProfiloBand() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profilo_band, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        String emailBandSelezionata = a.getString("EMAIL_BAND");
        System.out.println("MIAOOOOOOOOO"+emailBandSelezionata);

        emailBand = v.findViewById(R.id.textView_email_band_visualizzabile);
        nomeBand=v.findViewById(R.id.textView_nome_band_visualizzabile);
        numeroComponentiBand=v.findViewById(R.id.textView_numero_singoli_componenti);
        localitaBand=v.findViewById(R.id.textView_località_band_visualizzabile);
        generiMusicaliBand=v.findViewById(R.id.listView_generi_musicali);
        biografiaBand=v.findViewById(R.id.textView_biografia_band_visualizzabile);
        numeroTelefonoBand=v.findViewById(R.id.textView_numero_telefono_band_visualizzabile);
        linkSocialBand=v.findViewById(R.id.textView_link_social_una_band_visualizzabile);

        try {
            JSONArray listaBand = new JSONArray(i.getStringExtra("LISTA_BAND"));
            for (int j = 0; j < listaBand.length(); j++) {
                if (listaBand.getJSONObject(j).getString("email").equals(emailBandSelezionata)) {
                    System.out.println("WEEEEEEEEEEEEEEEEEE" + listaBand.getJSONObject(j));

                    emailBand.setText(emailBandSelezionata);

                    String nomeBandS = listaBand.getJSONObject(j).getString("nomeBand");
                    nomeBand.setText(nomeBandS);

                    int numero = listaBand.getJSONObject(j).getInt("numeroComponenti");
                    numeroComponentiBand.setText(String.valueOf(numero));

                    String luogoS = listaBand.getJSONObject(j).getString("luogo");
                    localitaBand.setText(luogoS);

                    String biografiaS = listaBand.getJSONObject(j).getString("biografia");
                    biografiaBand.setText(biografiaS);

                    String telefonoS= listaBand.getJSONObject(j).getString("numeroTelefono");
                    numeroTelefonoBand.setText(telefonoS);

                    String linkS =listaBand.getJSONObject(j).getString("linkSocial");
                    linkSocialBand.setText(linkS);

                    String generiJSON = listaBand.getJSONObject(j).getString("generiMusicali");
                    String [] arrayGeneri = generiJSON.split(",");
                    ArrayAdapter<String> adapterGeneriMusicali= new ArrayAdapter<String>(getActivity().getApplicationContext(),
                            R.layout.list_element_generi_musicali_artista,R.id.textView_genere_musicali_artista_uno,arrayGeneri);
                    generiMusicaliBand.setAdapter(adapterGeneriMusicali);
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
