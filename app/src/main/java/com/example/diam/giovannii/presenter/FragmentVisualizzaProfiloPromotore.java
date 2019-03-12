package com.example.diam.giovannii.presenter;

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

public class FragmentVisualizzaProfiloPromotore extends Fragment {

    Intent i;
    Bundle a;


    TextView nomePromotore;
    TextView cognomePromotore;
    TextView etaPromotore;
    TextView emailPromotore;
    TextView biografiaPromotore;
    TextView sessoPromotore;
    TextView luogoPromotore;
    TextView telefonoPromotore;
    TextView linkSocialPromotore;



    public FragmentVisualizzaProfiloPromotore() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profilo_promotore, container, false);

        i = getActivity().getIntent();
        a = i.getExtras();

        String emailPromotoreSelezionato = a.getString("EMAIL_PROMOTORE");
        System.out.println("EEEEEEEEEEEEMMMMMAIL: "+emailPromotoreSelezionato);

        emailPromotore = v.findViewById(R.id.textView_email_promotore_visualizzabile);
        nomePromotore=v.findViewById(R.id.textView_nome_promotore_visualizzabile);
        cognomePromotore=v.findViewById(R.id.textView_cognome_promotore_visualizzabile);
        etaPromotore=v.findViewById(R.id.textView_eta_promotore_visualizzabile);
        biografiaPromotore=v.findViewById(R.id.textView_biografia_promotore_visualizzabile);
        sessoPromotore=v.findViewById(R.id.textView_sesso_promotore_visualizzabile);
        luogoPromotore=v.findViewById(R.id.textView_localita_promtoore_visualizzabile);
        telefonoPromotore=v.findViewById(R.id.textView_numero_telefono_promotore);
        linkSocialPromotore=v.findViewById(R.id.textView_link_social_promotore_visualizzabile);


            try {
                JSONArray listaPromotori = new JSONArray(i.getStringExtra("LISTA_PROMOTORI"));
                for (int j = 0; j < listaPromotori.length(); j++) {
                    if (listaPromotori.getJSONObject(j).getString("email").equals(emailPromotoreSelezionato)) {
                        System.out.println("WEEEEEEEEEEEEEEEEEE" + listaPromotori.getJSONObject(j));

                        emailPromotore.setText(emailPromotoreSelezionato);
                        String nomePromotoreS = listaPromotori.getJSONObject(j).getString("nome");
                        nomePromotore.setText(nomePromotoreS);

                        String cognomePromotoreS = listaPromotori.getJSONObject(j).getString("cognome");
                        cognomePromotore.setText(cognomePromotoreS);

                        int etaPromotoreI = listaPromotori.getJSONObject(j).getInt("age");
                        etaPromotore.setText(String.valueOf(etaPromotoreI));

                        String biografiaPromotoreS = listaPromotori.getJSONObject(j).getString("biografia");
                        biografiaPromotore.setText(biografiaPromotoreS);

                        String sessoP = listaPromotori.getJSONObject(j).getString("sesso");
                        if (sessoP.equalsIgnoreCase("m")) {
                            sessoPromotore.setText("M");
                        } else {
                            sessoPromotore.setText("F");
                        }

                        String luogoS = listaPromotori.getJSONObject(j).getString("luogo");
                        luogoPromotore.setText(luogoS);
                        String telefonoS = listaPromotori.getJSONObject(j).getString("numeroTelefono");
                        telefonoPromotore.setText(telefonoS);
                        String linkSocialS = listaPromotori.getJSONObject(j).getString("linkSocial");
                        linkSocialPromotore.setText(linkSocialS);

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
