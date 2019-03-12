package com.example.diam.giovannii.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.diam.giovannii.R;
import com.example.diam.giovannii.presenter.FragmentEventiPromotore;
import com.example.diam.giovannii.presenter.FragmentHome;
import com.example.diam.giovannii.presenter.FragmentListaArtisti;
import com.example.diam.giovannii.presenter.FragmentListaBand;
import com.example.diam.giovannii.presenter.FragmentProfiloPromotore;
import com.example.diam.giovannii.presenter.FragmentProposteEventoPromotore;
import com.example.diam.giovannii.presenter.PresenterAccount;
import com.example.diam.giovannii.presenter.PresenterAutenticazione;
import com.example.diam.giovannii.presenter.PresenterEventi;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivityPromotore extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    Intent i;
    Bundle a;
    String tipoProfilo;

    PresenterAccount pa = new PresenterAccount();
    PresenterEventi pe = new PresenterEventi();

    FragmentHome home;
    FragmentProfiloPromotore fragmentProfiloPromotore;
    FragmentEventiPromotore fragmentEventiPromotore;
    FragmentProposteEventoPromotore fragmentProposteEventoPromotore;
    FragmentListaBand fragmentListaBand;
    FragmentListaArtisti fragmentListaArtisti;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        i = getIntent();
        a = i.getExtras();

        tipoProfilo = a.getString("TIPOPROFILO");

        setContentView(R.layout.activity_promotore);


        String tipoMetodo = "cercaEventi";
        JSONArray risposta = pe.creazioneE(tipoMetodo);
        JSONArray eventi = null;
        try {
            eventi = risposta.getJSONArray(1);
            i.putExtra("LISTA_EVENTI",eventi.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        home = new FragmentHome();
        ft.replace(R.id.frammento_da_inserire, home);
        ft.commit();


        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mio_profilo_p) {



            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragmentProfiloPromotore = new FragmentProfiloPromotore();
            ft.replace(R.id.frammento_da_inserire, fragmentProfiloPromotore);
            ft.addToBackStack(null);
            ft.commit();




        } else if (id == R.id.miei_eventi) {


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragmentEventiPromotore = new FragmentEventiPromotore();
            ft.replace(R.id.frammento_da_inserire, fragmentEventiPromotore);
            ft.addToBackStack(null);
            ft.commit();


        } else if (id == R.id.mie_proposte_evento) {


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragmentProposteEventoPromotore = new FragmentProposteEventoPromotore();
            ft.replace(R.id.frammento_da_inserire, fragmentProposteEventoPromotore);
            ft.addToBackStack(null);
            ft.commit();




        } else if (id == R.id.cerca_band) {

            String tipoMetodo = "cercaBandArtisti";

            JSONArray risposta = pa.creazione(tipoMetodo);
            JSONArray band = null;
            try {
                band = risposta.getJSONArray(2);
                i.putExtra("LISTA_BAND",band.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            System.out.println(band.toString());
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            fragmentListaBand = new FragmentListaBand();
            ft.replace(R.id.frammento_da_inserire, fragmentListaBand);
            ft.addToBackStack(null);
            ft.commit();



        } else if(id == R.id.cerca_artisti){
            String tipoMetodo = "cercaBandArtisti";

            JSONArray risposta = pa.creazione(tipoMetodo);
            JSONArray artisti = null;
            try {
                artisti = risposta.getJSONArray(1);
                i.putExtra("LISTA_ARTISTI",artisti.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(artisti.toString());
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            fragmentListaArtisti = new FragmentListaArtisti();
            ft.replace(R.id.frammento_da_inserire, fragmentListaArtisti);
            ft.addToBackStack(null);
            ft.commit();

        }  else if (id == R.id.crea_evento) {


            //setContentView(R.layout.form_evento);
        } else if (id == R.id.crea_proposte_evento) {


            //setContentView(R.layout.form_proposta_evento);

        } else if (id == R.id.cerca_evento) {

            String tipoMetodo = "cercaEventi";
            JSONArray risposta = pe.creazioneE(tipoMetodo);
            JSONArray eventi = null;
            try {
                eventi = risposta.getJSONArray(1);
                i.putExtra("LISTA_EVENTI",eventi.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(risposta.toString());

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            home = new FragmentHome();
            ft.replace(R.id.frammento_da_inserire, home);
            ft.addToBackStack(null);
            ft.commit();


        } else if (id == R.id.logout) {

            Toast.makeText(getApplicationContext(), "Logout. ", Toast.LENGTH_LONG).show();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if(home!=null) ft.remove(home);
            if(fragmentEventiPromotore!=null) ft.remove(fragmentEventiPromotore);
            if(fragmentProfiloPromotore!=null) ft.remove(fragmentProfiloPromotore);
            if(fragmentListaArtisti!=null) ft.remove(fragmentListaArtisti);
            if(fragmentListaBand!=null) ft.remove(fragmentListaBand);
            if(fragmentProposteEventoPromotore!=null) ft.remove(fragmentProposteEventoPromotore);
            ft.commit();
            Intent i = new Intent(getApplicationContext(), PresenterAutenticazione.class);
            startActivity(i);


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}





