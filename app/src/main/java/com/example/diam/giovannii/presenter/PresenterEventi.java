package com.example.diam.giovannii.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



import com.example.diam.giovannii.R;
import com.example.diam.giovannii.manager.GestioneEventi;
import com.example.diam.giovannii.model.pojo.Band;
import com.example.diam.giovannii.model.pojo.Evento;
import com.example.diam.giovannii.model.pojo.Promotore;
import com.example.diam.giovannii.model.pojo.PropostaEvento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.diam.giovannii.presenter.PresenterAutenticazione.ip;

public class PresenterEventi {


    JSONArray linea = null;
    JSONObject richiesta;

    myTask nw;
    Intent i;
    Bundle a;
    Socket s = null;
    BufferedReader reader = null;
    OutputStreamWriter writer = null;
    String tipometodo = "";


    public JSONArray risposta = null;


    public JSONArray creazioneE(String tipoMetodo) {


        tipometodo = tipoMetodo;

        if (tipometodo.equals("cercaEventi")) {
            this.cercaEventi();
            System.out.print("CIAOOOOOOOOOO" + risposta + tipometodo);

        } else if (tipometodo.equals("cercaProposteEvento")) {
            this.cercaProposteEvento();
            System.out.print("CIAOOOOOOOOOO" + risposta + tipometodo);
        }


        return risposta;
    }

    private void cercaEventi() {

        richiesta = new JSONObject();
        try {
            richiesta.put("tipometodo", "cercaevento");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Toast.makeText(getApplicationContext(), "CercaBandArtisti ", Toast.LENGTH_LONG).show();


        nw = new myTask();
        try {
            nw.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//       System.out.println("è tornata la risposta nel metodo PresenterAccount " + "\n" + risp.toString());

    }

    private void cercaProposteEvento() {

        richiesta = new JSONObject();
        try {
            richiesta.put("tipometodo", "cercapevento");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Toast.makeText(getApplicationContext(), "CercaPromotore ", Toast.LENGTH_LONG).show();

        nw = new myTask();
        try {
            nw.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //    System.out.println("è tornata la risposta nel metodo PresenterAccount " + "\n" + risp.toString());
    }


    class myTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... voids) {
            try {


                System.out.println("SONO IN mYTASK ");

                s = new Socket(ip, 5000);

                writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");

                reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));


                writer.write(richiesta.toString() + "\n");
                writer.flush();

                risposta = new JSONArray(reader.readLine());


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return risposta;

        }


        @Override
        protected void onPostExecute(JSONArray ripsosta) {
            //super.onPostExecute(risposta);


            System.out.println("SONO IN mYTASK +++  Ho scritto  " + richiesta.toString());

            //   Stream<String> linee = reader.lines();


            System.out.println("SONO IN mytask +++  Ho letto  " + risposta.toString());

            System.out.println(risposta.toString());

            System.out.println("+++ tipo metodo mytask" + tipometodo.toString());


        }

    }
}
