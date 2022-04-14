package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medic {
    private String nume;
    private int numarDePacienti;
    private String specializare;

    private List<Programare> listaProgramari = new ArrayList<Programare>();

    public Medic(String nume, int numarDePacienti, String specializare, List<Programare> listaProgramari) {
        this.nume = nume;
        this.numarDePacienti = numarDePacienti;
        this.specializare = specializare;
        this.listaProgramari = listaProgramari;
    }

    // Constructor cand vrem sa adaugam separat in listaProgramari.
    public Medic(String nume, int numarDePacienti, String specializare) {
        this.nume = nume;
        this.numarDePacienti = numarDePacienti;
        this.specializare = specializare;
    }

    public Medic() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNumarDePacienti() {
        return numarDePacienti;
    }

    public void setNumarDePacienti(int numarDePacienti) {
        this.numarDePacienti = numarDePacienti;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public List<Programare> getListaProgramari() {
        return listaProgramari;
    }

    public void setListaProgramari(List<Programare> listaProgramari) {
        this.listaProgramari = listaProgramari;
    }

    public void resetareNumarDePacieti() {
        numarDePacienti = 0;
    }

    public void aprobareProgramare(Programare programare) {
        programare.setStare("Aprobat");
        numarDePacienti += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medic)) return false;
        Medic medic = (Medic) o;
        return numarDePacienti == medic.numarDePacienti && nume.equals(medic.nume) && specializare.equals(medic.specializare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, numarDePacienti, specializare, listaProgramari);
    }

    @Override
    public String toString() {
        return "Medic{" +
                "nume='" + nume + '\'' +
                ", numarDePacienti=" + numarDePacienti +
                ", specializare='" + specializare + '\'' +
                ", listaProgramari=" + listaProgramari +
                '}';
    }


}
