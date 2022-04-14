package com.company;

import java.util.List;

public class Ecografie extends Investigatie {
    private String descriereEcografie;
    private Boolean limitaDeVarsta;
    private int durataInMinute;

    public Ecografie(String nume, int pret, String locatieCabinet, List<Medic> listaDeMedici, String descriereEcografie, Boolean limitaDeVarsta, int durataInMinute) {

        super(nume, pret, locatieCabinet, listaDeMedici);
        this.descriereEcografie = descriereEcografie;
        this.limitaDeVarsta = limitaDeVarsta;
        this.durataInMinute = durataInMinute;
    }

    public String getDescriereEcografie() {
        return descriereEcografie;
    }

    public void setDescriereEcografie(String descriereEcografie) {
        this.descriereEcografie = descriereEcografie;
    }

    public Boolean getLimitaDeVarsta() {
        return limitaDeVarsta;
    }

    public void setLimitaDeVarsta(Boolean limitaDeVarsta) {
        this.limitaDeVarsta = limitaDeVarsta;
    }

    public int getDurataInMinute() {
        return durataInMinute;
    }

    public void setDurataInMinute(int durataInMinute) {
        this.durataInMinute = durataInMinute;
    }

    @Override
    public String toString() {
        return "Ecografie{" +
                "descriereEcografie='" + descriereEcografie + '\'' +
                ", limitaDeVarsta=" + limitaDeVarsta +
                ", durataInMinute=" + durataInMinute +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                ", locatieCabinet='" + locatieCabinet + '\'' +
                ", listaDeMedici=" + listaDeMedici +
                '}';
    }
}
