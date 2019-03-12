package com.example.diam.giovannii.presenter;

public class PropostaEventoVisualizzata {
    private String nomeProposta;
    private int idProposta;

    public PropostaEventoVisualizzata(String nomeProposta, int idProposta){
        this.nomeProposta = nomeProposta;
        this.idProposta = idProposta;
    }

    public int getIdProposta() {
        return idProposta;
    }

    public String getNomeProposta() {
        return nomeProposta;
    }

    public void setIdProposta(int idProposta) {
        this.idProposta = idProposta;
    }

    public void setNomeProposta(String nomeProposta) {
        this.nomeProposta = nomeProposta;
    }
}
