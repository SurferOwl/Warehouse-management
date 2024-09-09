package com.example.as3_tp.Model;

/**
 * used to represent recipes
 */
public class Recipe {
    private int idComanda;
    private int cantita;
    private float pret;
    private String adresa;

    public Recipe(int idComanda, int cantita, float pret, String adresa) {
        this.idComanda = idComanda;
        this.cantita = cantita;
        this.pret = pret;
        this.adresa = adresa;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public int getCantita() {
        return cantita;
    }

    public float getPret() {
        return pret;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public void setCantita(int cantita) {
        this.cantita = cantita;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

}
