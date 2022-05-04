package com.company.entitati;

import java.util.List;

public class Consultatie extends Investigatie {
    private String tip;
    private String descriereConsultatie;

    // Constructori

    public Consultatie(String nume, int pret, String locatieCabinet, List<Medic> listaDeMedici, String tip, String descriereConsultatie) {
        super(nume, pret, locatieCabinet, listaDeMedici);
        this.tip = tip;
        this.descriereConsultatie = descriereConsultatie;
    }

    public Consultatie() {
        super();
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDescriereConsultatie() {
        return descriereConsultatie;
    }

    public void setDescriereConsultatie(String descriereConsultatie) {
        this.descriereConsultatie = descriereConsultatie;
    }

    @Override
    public String toString() {
        return "Consultatie{" +
                "tip='" + tip + '\'' +
                ", descriereConsultatie='" + descriereConsultatie + '\'' +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                ", locatieCabinet='" + locatieCabinet + '\'' +
                ", listaDeMedici=" + listaDeMedici +
                '}';
    }
}
