package com.example.diam.giovannii.presenter;

public class EventoVisualizzato {

    private String nomeEvento;
    private int idEvento;

    public EventoVisualizzato(String nomeEvento, int idEvento){
        this.nomeEvento = nomeEvento;
        this.idEvento = idEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
