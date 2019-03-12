package com.example.diam.giovannii.connessione;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connessione extends AppCompatActivity {

    private static String ip = "172.19.9.223";
    private  JSONObject richiesta= null;
    private JSONArray risposta= null;
    private static OutputStreamWriter writer;
    private static Socket s;
    private static BufferedReader reader;


    public Connessione(){

    }



    public JSONArray send_text(JSONObject j) {

        richiesta = j;
        System.out.println( "SONO IN send_text " + richiesta.toString());
        myTask nt = new myTask();
        nt.execute();
        System.out.println( "SONO IN send_text dopo il task ++++ " + richiesta.toString());

        return risposta;

    }

    class myTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... voids) {
            try {


                System.out.println( "SONO IN mYTASK " );

                s = new Socket(ip, 5000);

                writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");

                reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            try {

                writer.write(richiesta.toString() + "\n");
                writer.flush();

                System.out.println( "SONO IN mYTASK +++  Ho scritto  " +richiesta.toString() );

                //   Stream<String> linee = reader.lines();
                risposta  = new JSONArray(reader.readLine());



                System.out.println( "SONO IN mytask +++  Ho letto  " + risposta.toString() );


                return risposta;





            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return risposta;
        }


        @Override
        protected void onPostExecute(JSONArray risposta) {
            super.onPostExecute(risposta);

            System.out.println("Sono in onPost"+ risposta.toString());




        }

    }


}
