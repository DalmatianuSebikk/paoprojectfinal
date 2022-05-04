package com.company;
import com.company.Investigatie;

import java.util.List;

public class Analiza extends Investigatie {
    private Boolean limitaDeVarsta;
    private String descriereAnaliza;

    public Analiza(String nume, int pret, String locatieCabinet, List<Medic> listaDeMedici, Boolean limitaDeVarsta, String descriereAnaliza) {
        super(nume, pret, locatieCabinet, listaDeMedici);
        this.limitaDeVarsta = limitaDeVarsta;
        this.descriereAnaliza = descriereAnaliza;
    }

    public Boolean getLimitaDeVarsta() {
        return limitaDeVarsta;
    }

    public void setLimitaDeVarsta(Boolean limitaDeVarsta) {
        this.limitaDeVarsta = limitaDeVarsta;
    }

    public String getDescriereAnaliza() {
        return descriereAnaliza;
    }

    public void setDescriereAnaliza(String descriereAnaliza) {
        this.descriereAnaliza = descriereAnaliza;
    }

    @Override
    public String toString() {
        return "Analiza{" +
                "limitaDeVarsta=" + limitaDeVarsta +
                ", descriereAnaliza='" + descriereAnaliza + '\'' +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                ", locatieCabinet='" + locatieCabinet + '\'' +
                ", listaDeMedici=" + listaDeMedici +
                '}';
    }
}
