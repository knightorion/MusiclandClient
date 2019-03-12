package com.example.diam.giovannii.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.diam.giovannii.R;
import com.example.diam.giovannii.connessione.Connessione;
import com.example.diam.giovannii.manager.GestioneAutenticazione;
import com.example.diam.giovannii.menu.MainActivityBandArtisti;
import com.example.diam.giovannii.menu.MainActivityPromotore;
import com.example.diam.giovannii.model.pojo.Artista;
import com.example.diam.giovannii.model.pojo.Band;
import com.example.diam.giovannii.model.pojo.Evento;
import com.example.diam.giovannii.model.pojo.Promotore;
import com.example.diam.giovannii.model.pojo.Utente;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PresenterAutenticazione extends Activity implements GestioneAutenticazione {


    public static final String E = "EMAIL_ARTISTA";
    public static String TIPOPROFILO="artista";
    EditText emailUtente;
    EditText passwordUtente;

    JSONArray linea;
    String tipoProfilo;

     static Socket s;
     static OutputStreamWriter writer = null;
     static BufferedReader reader = null;

    String email = null;
    String password = null;

    public static String ip = "172.19.13.98";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login3);
        emailUtente = findViewById(R.id.email);
        passwordUtente = findViewById(R.id.editTextPassword);

    }


    @Override
    public void login(View v) {

        email = String.valueOf(emailUtente.getText());
        password = String.valueOf(passwordUtente.getText());

        myTask nt = new myTask();
        nt.execute();

        Toast.makeText(getApplicationContext(), "Data send ", Toast.LENGTH_LONG).show();
        System.out.print(linea);
    }

    class myTask extends AsyncTask<Void, Void, JSONArray> {

            @Override
            protected JSONArray doInBackground(Void... voids) {
                try {

                    s = new Socket(ip, 5000);

                    writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");

                    reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }

                try {

                    //writer.write(richiesta.toString() + "\n");

                    JSONObject jobj = new JSONObject();

                    jobj.put("tipometodo", "login");
                    jobj.put("email", email);
                    jobj.put("password", password);

                    writer.write(jobj.toString() + "\n");
                    writer.flush();

                    linea = new JSONArray(reader.readLine());
                    //     linea =  //legge la linea risposta del server??


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return linea;

            }


            @Override
            protected void onPostExecute(JSONArray linea) {
                //super.onPostExecute(linea);

                String separatore = ",";

                //JSONArray jarray = null;

                JSONObject artista;
                JSONObject band;
                JSONObject promotore = null;

                JSONArray eventiArtista = null;
                JSONArray eventiBand = null;
                JSONArray eventiPromotore = null;
                JSONArray proposteEventoPromotore = null;

                int idArtista = 0;
                String cognomeArtista = "";
                String nomeArtista = "";
                String luogoArtista = "";
                String nicknameArtista = "";
                String biografiaArtista = "";
                String[] generiSuonatiArtista = null;
                String[] strumentiSuonatiArtista = null;
                String passwordArtista = "";
                String sessoArtista = "";
                boolean isBandArtista = false;
                int etaArtista = 0;
                String emailArtista = "";
                String numeroTelefonoArtista = "";
                String contattoSocialArtista = "";


                String nomeBand;
                String emailBand;
                String passwordBand;
                int numeroComponentiBand;
                String luogoBand;
                String numeroTelefonoBand;
                String contattoSocialBand;
                String[] generiSuonatiBand;
                String biografiaBand;


                String emailPromotore;
                String passwordPromotore;
                String nomePromotore;
                String cognomePromotore;
                int etaPromotore;
                String sessoPromotore;
                String telefonoPromotore;
                String contattoSocialPromotore;
                String biografiaPromotore;
                String luogoPromotore;

/*
            try {

               // jarray = (JSONArray) new JSONTokener(linea).nextValue();

               // jarray= new JSONArray(linea.ge);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

                //System.out.println(jarray.toString());

                try {
                    System.out.println(linea.toString());

                    JSONObject verificaUtente = null;
                    JSONArray eventiUtente = null;



                    JSONObject info = linea.getJSONObject(0);
                    tipoProfilo = info.getString("tipoprofilo");
                    System.out.println("TIPOOOOOOOOOOOO"+tipoProfilo);


                    if (tipoProfilo.equals("passwordErrata")) {
                        Toast.makeText(getApplicationContext(), "PasswordErrata. ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), PresenterAutenticazione.class);
                        startActivity(i);
                        //setContentView(R.layout.f_a);
                    }

                    if (tipoProfilo.equals("datiNonTrovati")) {
                        Toast.makeText(getApplicationContext(), "Email/Password Errata ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), PresenterAutenticazione.class);
                        startActivity(i);
                        //setContentView(R.layout.f_a);
                    }


                    if (!((tipoProfilo.equals("passwordErrata")) | (tipoProfilo.equals("datiNonTrovati")))) {


                        verificaUtente = linea.getJSONObject(1);
                        System.out.println(verificaUtente);

                        if (tipoProfilo.equals("artista")) {
                            artista = verificaUtente;


                            idArtista = artista.getInt("idArtista");
                            cognomeArtista = artista.getString("cognome");
                            nomeArtista = artista.getString("nome");
                            luogoArtista = artista.getString("luogo");
                            nicknameArtista = artista.getString("nickname");
                            String generiSuonatiJSON = artista.getString("generiMusicali");
                            generiSuonatiArtista = generiSuonatiJSON.split(separatore);
                            String strumentiSuonatiJSON = artista.getString("strumentiSuonati");
                            strumentiSuonatiArtista = strumentiSuonatiJSON.split(separatore);
                            passwordArtista = artista.getString("password");
                            sessoArtista = artista.getString("sesso");
                            isBandArtista = artista.getBoolean("band");
                            etaArtista = artista.getInt("age");

                            Intent i = new Intent(getApplicationContext(), MainActivityBandArtisti.class);

                            i.putExtra("TIPOPROFILO", tipoProfilo);

                            i.putExtra("ID_ARTISTA", idArtista);
                            i.putExtra("NOME_ARTISTA", nomeArtista);
                            i.putExtra("COGNOME_ARTISTA", cognomeArtista);
                            i.putExtra("NICKNAME_ARTISTA", nicknameArtista);
                            i.putExtra("ETA_ARTISTA", etaArtista);
                            i.putExtra("SESSO_ARTISTA", sessoArtista);
                            i.putExtra("GENERI_ARTISTA", generiSuonatiArtista);
                            i.putExtra("STRUMENTI_ARTISTA", strumentiSuonatiArtista);
                            i.putExtra("IS_BAND", isBandArtista);
                            i.putExtra("LUOGO_ARTISTA", luogoArtista);
                            i.putExtra("PASSWORD_ARTISTA", passwordArtista);
                            i.putExtra(E, emailArtista);


                            if (artista.has("linkSocial")) {
                                contattoSocialArtista = artista.getString("linkSocial");
                                System.out.println("Link si");
                                i.putExtra("LINK_ARTISTA", contattoSocialArtista);
                            } else {
                                System.out.println("Link no");
                                i.putExtra("LINK_ARTISTA", "");
                            }
                            if (artista.has("biografia")) {
                                biografiaArtista = artista.getString("biografia");
                                System.out.println("Biografia si");
                                i.putExtra("BIOGRAFIA_ARTISTA", biografiaArtista);
                            } else {

                                System.out.println("Biografia no");
                                i.putExtra("BIOGRAFIA_ARTISTA", "");
                            }

                            if (artista.has("numeroTelefono")) {
                                numeroTelefonoArtista = artista.getString("numeroTelefono");
                                System.out.println("Telefono si");
                                i.putExtra("TELEFONO_ARTISTA", numeroTelefonoArtista);
                            } else {

                                System.out.println("Telefono no");
                                i.putExtra("TELEFONO_ARTISTA", "");
                            }


                            if (linea.getJSONArray(2) != null) {
                                eventiUtente = linea.getJSONArray(2);
                                eventiArtista = eventiUtente;
                                System.out.println(eventiArtista);
                                i.putExtra("EVENTI_ARTISTA", eventiArtista.toString());


                            } else {
                                i.putExtra("EVENTI_ARTISTA", "");
                            }


                            startActivity(i);

                        } else if (tipoProfilo.equals("band")) {
                            band = verificaUtente;
                            System.out.println(band);

                            emailBand = band.getString("email");
                            nomeBand = band.getString("nomeBand");
                            numeroComponentiBand = band.getInt("numeroComponenti");
                            luogoBand = band.getString("luogo");
                            passwordBand = band.getString("password");
                            String generiSuonatiBandJSON = band.getString("generiMusicali");
                            generiSuonatiBand = generiSuonatiBandJSON.split(separatore);

                            Intent i = new Intent(getApplicationContext(), MainActivityBandArtisti.class);


                            i.putExtra("TIPOPROFILO", tipoProfilo);

                            i.putExtra("EMAIL_BAND", emailBand);
                            i.putExtra("PASSWORD_BAND", passwordBand);
                            i.putExtra("NOME_BAND", nomeBand);
                            i.putExtra("NUMERO_COMPONENTI_BAND", numeroComponentiBand);
                            i.putExtra("GENERI_BAND", generiSuonatiBand);
                            i.putExtra("LUOGO_BAND", luogoBand);


                            if (band.has("linkSocial")) {
                                contattoSocialBand = band.getString("linkSocial");
                                System.out.println("Link si");
                                i.putExtra("LINK_BAND", contattoSocialBand);
                            } else {
                                System.out.println("Link no");
                                i.putExtra("LINK_BAND", "");
                            }
                            if (band.has("numero_telefono")) {
                                numeroTelefonoBand = band.getString("numero_telefono");
                                System.out.println("Telefono si");
                                i.putExtra("TELEFONO_BAND", numeroTelefonoBand);
                            } else {
                                System.out.println("Telefono no");
                                i.putExtra("TELEFONO_BAND", "");
                            }
                            if (band.has("biografia")) {

                                biografiaBand = band.getString("biografia");
                                System.out.println("Biografia si");
                                i.putExtra("BIOGRAFIA_BAND", biografiaBand);
                            } else {
                                System.out.println("Biografia no");
                                i.putExtra("BIOGRAFIA_BAND", "");
                            }


                            if (linea.getJSONArray(2) != null) {
                                eventiUtente = linea.getJSONArray(2);
                                eventiBand = eventiUtente;

                                i.putExtra("EVENTI_BAND", eventiBand.toString());

                            } else {
                                i.putExtra("EVENTI_BAND", "");
                            }


                            startActivity(i);

                        } else if (tipoProfilo.equals("promotore")) {
                            promotore = verificaUtente;
                            System.out.println(promotore);

                            emailPromotore = promotore.getString("email");
                            passwordPromotore = promotore.getString("password");
                            etaPromotore = promotore.getInt("age");
                            sessoPromotore = promotore.getString("sesso");
                            luogoPromotore = promotore.getString("luogo");
                            nomePromotore = promotore.getString("nome");
                            cognomePromotore = promotore.getString("cognome");

                            Intent i = new Intent(getApplicationContext(), MainActivityPromotore.class);

                            i.putExtra("TIPOPROFILO", tipoProfilo);

                            i.putExtra("NOME_PROMOTORE", nomePromotore);
                            i.putExtra("COGNOME_PROMOTORE", cognomePromotore);
                            i.putExtra("EMAIL_PROMOTORE", emailPromotore);
                            i.putExtra("PASSWORD_PROMOTORE", passwordPromotore);
                            i.putExtra("ETA_PROMOTORE", etaPromotore);
                            i.putExtra("SESSO_PROMOTORE", sessoPromotore);
                            i.putExtra("LUOGO_PROMOTORE", luogoPromotore);

                            if (promotore.has("biografia")) {
                                biografiaPromotore = promotore.getString("biografia");
                                i.putExtra("BIOGRAFIA_PROMOTORE", biografiaPromotore);
                                System.out.println("La biografia è presente");
                            } else {
                                i.putExtra("BIOGRAFIA_PROMOTORE", "");
                                System.out.println("No biografia");
                            }
                            if (promotore.has("numeroTelefono")) {
                                telefonoPromotore = promotore.getString("numeroTelefono");
                                i.putExtra("TELEFONO_PROMOTORE", telefonoPromotore);
                                System.out.println("Telefono si");
                            } else {
                                i.putExtra("TELEFONO_PROMOTORE", "");
                                System.out.println("Telefono no");
                            }

                            if (promotore.has("linkSocial")) {
                                contattoSocialPromotore = promotore.getString("linkSocial");
                                System.out.println("Link si");
                                i.putExtra("LINK_PROMOTORE", contattoSocialPromotore);
                            } else {
                                System.out.println("Link no");
                                i.putExtra("LINK_PROMOTORE", "");
                            }

                            if (linea.getJSONArray(2) != null) {
                                eventiUtente = linea.getJSONArray(2);
                                eventiPromotore = eventiUtente;
                                System.out.println("EEEEEEEEEEEEEEEEE"+eventiPromotore);

                                i.putExtra("EVENTI_PROMOTORE", eventiPromotore.toString());

                            } else {
                                i.putExtra("EVENTI_PROMOTORE", "");
                            }

                            if (linea.getJSONArray(3) != null) {
                                proposteEventoPromotore = linea.getJSONArray(3);
                                System.out.println("PROPOSTEEEEEEEEE"+proposteEventoPromotore);

                                i.putExtra("PROPOSTE_EVENTO_PROMOTORE", proposteEventoPromotore.toString());

                            } else {
                                i.putExtra("PROPOSTE_EVENTO_PROMOTORE", "");
                            }


                            startActivity(i);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

    }

}
