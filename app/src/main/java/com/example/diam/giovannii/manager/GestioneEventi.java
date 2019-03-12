package com.example.diam.giovannii.manager;

import android.view.View;


import com.example.diam.giovannii.model.pojo.Evento;
import com.example.diam.giovannii.model.pojo.Promotore;
import com.example.diam.giovannii.model.pojo.PropostaEvento;

import java.util.ArrayList;

public interface GestioneEventi {
/*
    public void pubblicaPropostaEvento(View v, PropostaEvento propostaEvento);

    public void pubblicaEvento(View v, Evento evento);

    public void pubblicaEvento(View v, PropostaEvento propostaEvento);

    public PropostaEvento modificaPropostaEvento(View v, int idPropostaEvento);

    public Evento modificaEvento(View v, int idEvento);

    public boolean eliminaPropostaEvento(View v, int idPropostaEvento);

    public boolean eliminaEvento(View v, int idEvento);
*/
    public ArrayList<PropostaEvento> cercaProposteEvento(String filtro);

    public ArrayList<Evento> cercaEventi(String filtro);

    public PropostaEvento visualizzaUnaPropostaEvento(View v, int idPropostaEvento, String nomeMetodoOnClick, Promotore promotore);

    public Evento visualizzaSingoloEvento(View v, int idEvento, String nomeMetodoOnClick, Promotore promotore);


}
