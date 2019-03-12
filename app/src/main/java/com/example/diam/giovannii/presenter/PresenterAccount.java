package com.example.diam.giovannii.presenter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import static com.example.diam.giovannii.presenter.PresenterAutenticazione.ip;

//import com.example.tramonto.musicland.R;

public  class PresenterAccount {

    JSONArray linea = null;
    JSONObject richiesta;

    //  private static Socket s;
    myTask nw;
    Intent i;
    Bundle a;
    Socket s = null;
    BufferedReader reader = null;
    OutputStreamWriter writer = null;
    String tipometodo="";


    public JSONArray risposta = null;


    public JSONArray creazione(String tipoMetodo) {


        tipometodo=tipoMetodo;

        if (tipometodo.equals("cercaPromotori")) {
            this.cercaPromotore();
            System.out.print("CIAOOOOOOOOOO"+risposta);

        } else if (tipometodo.equals("cercaBandArtisti")) {
            this.cercaBandArtisti();
            System.out.print("CIAOOOOOOOOOO"+risposta);
        }


        return risposta;
    }

    private void cercaBandArtisti() {

        richiesta = new JSONObject();
        try {
            richiesta.put("tipometodo","cercaba");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Toast.makeText(getApplicationContext(), "CercaBandArtisti ", Toast.LENGTH_LONG).show();


        nw= new myTask();
        try {
            nw.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//       System.out.println("è tornata la risposta nel metodo PresenterAccount " + "\n" + risp.toString());

    }

    private void cercaPromotore() {

        richiesta = new JSONObject();
        try {
            richiesta.put("tipometodo","cercap");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Toast.makeText(getApplicationContext(), "CercaPromotore ", Toast.LENGTH_LONG).show();

        myTask nw = new myTask();
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


                System.out.println( "SONO IN mYTASK " );

                s = new Socket(ip, 5000);

                writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");

                reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));


                writer.write(richiesta.toString() + "\n");
                writer.flush();

                risposta  = new JSONArray(reader.readLine());



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

    /*
    public interface MyCallBack{
        void onCallBack(JSONArray risposta);
    }


    public void getRisposta(MyCallBack myCallBack){

        myTask nw = new myTask();
        nw.execute();



            System.out.println("SONO IN mYTASK2 ");

            //  s = new Socket(ip, 5000);

            //writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");

            //  reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));

//            System.out.println(risposta.toString());





        myCallBack.onCallBack(risposta);
    }

*/

}
