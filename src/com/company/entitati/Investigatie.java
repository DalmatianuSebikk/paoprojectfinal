package com.company.entitati;

import java.util.ArrayList;
import java.util.List;

public abstract class Investigatie {
    protected String nume;
    protected int pret;
    protected String locatieCabinet; // adica la ce cabinet se face respectiva investigatie
    protected List<Medic>  listaDeMedici = new ArrayList<Medic>();

    public Investigatie(String nume, int pret, String locatieCabinet, List<Medic> listaDeMedici) {
        this.nume = nume;
        this.pret = pret;
        this.locatieCabinet = locatieCabinet;
        this.listaDeMedici = listaDeMedici;
    }

    public Investigatie() {

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getLocatieCabinet() {
        return locatieCabinet;
    }

    public void setLocatieCabinet(String locatieCabinet) {
        this.locatieCabinet = locatieCabinet;
    }

    public List<Medic> getListaDeMedici() {
        return listaDeMedici;
    }

    public void setListaDeMedici(List<Medic> listaDeMedici) {
        this.listaDeMedici = listaDeMedici;
    }

    @Override
    public String toString() {
        return "Investigatie{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                ", locatieCabinet='" + locatieCabinet + '\'' +
                ", listaDeMedici=" + listaDeMedici +
                '}';
    }
}
