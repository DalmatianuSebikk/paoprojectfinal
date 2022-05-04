package com.company.servicii;

import com.company.entitati.Analiza;
import com.company.entitati.Consultatie;
import com.company.entitati.Ecografie;
import com.company.entitati.Medic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWriteService {

    private static CSVWriteService instance;
    public static synchronized CSVWriteService getInstance() {
        if(instance == null) {
            instance = new CSVWriteService();
        }
        return instance;
    }

    public boolean saveMedici(String pathCSV, ArrayList<Medic> medici) throws IOException {
        try{
            FileWriter file = new FileWriter(pathCSV);
            BufferedWriter csvWriter = new BufferedWriter(file);


            csvWriter.append("nume");
            csvWriter.append(",");
            csvWriter.append("numarDePacienti");
            csvWriter.append(",");
            csvWriter.append("specializare");
            csvWriter.append("\n");

            for(Medic medic: medici) {

                csvWriter.append(medic.getNume());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(medic.getNumarDePacienti()));
                csvWriter.append(",");
                csvWriter.append(medic.getSpecializare());
                csvWriter.append("\n");
            }

            csvWriter.close();
            file.close();

        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public boolean saveAnalize(String pathCSV, ArrayList<Analiza> listaAnalize, ArrayList<Medic> listaMedici) throws IOException {

        try{
            FileWriter file = new FileWriter(pathCSV);
            BufferedWriter csvWriter = new BufferedWriter(file);
            // nume,pret,locatieCabinet,medicID,limitaDeVarsta,descriereAnaliza
            csvWriter.append("nume");
            csvWriter.append(",");
            csvWriter.append("pret");
            csvWriter.append(",");
            csvWriter.append("locatieCabinet");
            csvWriter.append(",");
            csvWriter.append("medicID");
            csvWriter.append(",");
            csvWriter.append("limitaDeVarsta");
            csvWriter.append(",");
            csvWriter.append("descriereAnaliza");
            csvWriter.append("\n");

            for(Analiza analiza: listaAnalize) {

                csvWriter.append(analiza.getNume());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(analiza.getPret()));
                csvWriter.append(",");
                csvWriter.append(analiza.getLocatieCabinet());
                csvWriter.append(",");

                // aici fac inversul la ce am zis ca fac
                for (Medic medic: listaMedici) {
                    if(medic.getThisID() == analiza.getListaDeMedici().get(0).getThisID()) {
                        csvWriter.append(String.valueOf(analiza.getListaDeMedici().get(0).getThisID()));
                        break;
                    }
                }

                csvWriter.append(",");
                csvWriter.append(String.valueOf(analiza.getLimitaDeVarsta()));
                csvWriter.append(",");
                csvWriter.append(analiza.getDescriereAnaliza());
                csvWriter.append("\n");
            }

            csvWriter.close();
            file.close();
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    public boolean saveConsultatii(String pathCSV, ArrayList<Consultatie> listaConsultatii, ArrayList<Medic> listaMedici) throws IOException {
        try {
            FileWriter file = new FileWriter(pathCSV);
            BufferedWriter csvWriter = new BufferedWriter(file);
            // nume,pret,locatieCabinet,medicID,descriereConsultatie,limitaDeVarsta
            csvWriter.append("nume");
            csvWriter.append(",");
            csvWriter.append("pret");
            csvWriter.append(",");
            csvWriter.append("locatieCabinet");
            csvWriter.append(",");
            csvWriter.append("medicID");
            csvWriter.append(",");
            csvWriter.append("descriereConsultatie");
            csvWriter.append(",");
            csvWriter.append("limitaDeVarsta");
            csvWriter.append("\n");

            for(Consultatie consultatie: listaConsultatii) {

                csvWriter.append(consultatie.getNume());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(consultatie.getPret()));
                csvWriter.append(",");
                csvWriter.append(consultatie.getLocatieCabinet());
                csvWriter.append(",");

                // aici fac inversul la ce am zis ca fac
                for (Medic medic: listaMedici) {
                    if(medic.getThisID() == consultatie.getListaDeMedici().get(0).getThisID()) {
                        csvWriter.append(String.valueOf(consultatie.getListaDeMedici().get(0).getThisID()));
                        break;
                    }
                }

                csvWriter.append(consultatie.getDescriereConsultatie());
                csvWriter.append("\n");

                csvWriter.close();
                file.close();
            }
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    public boolean saveEcografii(String pathCSV, ArrayList<Ecografie> listaEcografii, ArrayList<Medic> listaMedici) throws IOException {
        try {
            FileWriter file = new FileWriter(pathCSV);
            BufferedWriter csvWriter = new BufferedWriter(file);
//        nume,pret,locatieCabinet,medicID,descriereEcografie,limitaDeVarsta,durataInMinute

            csvWriter.append("nume");
            csvWriter.append(",");
            csvWriter.append("pret");
            csvWriter.append(",");
            csvWriter.append("locatieCabinet");
            csvWriter.append(",");
            csvWriter.append("medicID");
            csvWriter.append(",");
            csvWriter.append("descriereEcografie");
            csvWriter.append(",");
            csvWriter.append("limitaDeVarsta");
            csvWriter.append(",");
            csvWriter.append("durataInMinute");
            csvWriter.append("\n");

            for(Ecografie ecografie: listaEcografii) {

                csvWriter.append(ecografie.getNume());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(ecografie.getPret()));
                csvWriter.append(",");
                csvWriter.append(ecografie.getLocatieCabinet());
                csvWriter.append(",");

                // aici fac inversul la ce am zis ca fac
                for (Medic medic: listaMedici) {
                    if(medic.getThisID() == ecografie.getListaDeMedici().get(0).getThisID()) {
                        csvWriter.append(String.valueOf(ecografie.getListaDeMedici().get(0).getThisID()));
                        break;
                    }
                }

                csvWriter.append(ecografie.getDescriereEcografie());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(ecografie.getLimitaDeVarsta()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(ecografie.getDurataInMinute()));
                csvWriter.append("\n");

                csvWriter.close();
                file.close();
            }
        } catch(Exception e) {
            return false;
        }

        return true;
    }

}
