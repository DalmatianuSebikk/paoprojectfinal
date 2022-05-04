package com.company.servicii;

import com.company.entitati.Analiza;
import com.company.entitati.Consultatie;
import com.company.entitati.Ecografie;
import com.company.entitati.Medic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class CSVReadService {
    private static CSVReadService instance;
    public static synchronized CSVReadService getInstance() {
        if(instance == null) {
            instance = new CSVReadService();
        }
        return instance;
    }

    public ArrayList<Medic> readMedici(String pathCSV) throws IOException {
        ArrayList<Medic> listaMedici = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathCSV));
        String line = null;
        Scanner scanner = null;


        String header = reader.readLine();
        scanner = new Scanner(header);
        List<String> headerList = Arrays.asList(scanner.nextLine().split(","));

        while((line = reader.readLine()) != null) {
            List<String> data = Arrays.asList(line.split(","));
            Medic instanta = new Medic();

            // Eu citesc:
            // 0        ->      nume
            // 1        ->      numarDePacienti
            // 2        ->      specializare

            instanta.setNume(data.get(0));
            instanta.setNumarDePacienti(Integer.parseInt(data.get(1)));
            instanta.setSpecializare(data.get(2));

            listaMedici.add(instanta);

        }

        return listaMedici;
    }

    public ArrayList<Analiza> readAnalize(String pathCSV, List<Medic> listaMedici) throws IOException {

        ArrayList<Analiza> listaAnalize = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathCSV));
        String line = null;
        Scanner scanner = null;


        String header = reader.readLine();
        scanner = new Scanner(header);
        List<String> headerList = Arrays.asList(scanner.nextLine().split(","));

        while((line = reader.readLine()) != null) {
            List<String> data = Arrays.asList(line.split(","));
//            System.out.println(data);
            Analiza instanta = new Analiza();

            // Eu citesc:
            // 0        ->      nume
            // 1        ->      pret
            // 2        ->      locatieCabinet
            // 3        ->      medicID
            // 4        ->      limitaDeVarsta

            instanta.setNume(data.get(0));
            instanta.setPret(Integer.parseInt(data.get(1)));
            instanta.setLocatieCabinet(data.get(2));
            instanta.setLimitaDeVarsta(Boolean.parseBoolean(data.get(4)));
            instanta.setDescriereAnaliza(data.get(5));

            // fac o smecherie astfel incat sa imi ia medicul corect deci pt asta am nevoie de lista de medici
            int idMedic = Integer.parseInt(data.get(3));
            for (Medic medic: listaMedici) {
                List<Medic> listaMedic = new ArrayList<>();
                if(medic.getThisID() == idMedic) {
                    listaMedic.add(medic);
                    instanta.setListaDeMedici(listaMedic);
                    break;
                }
            }
            listaAnalize.add(instanta);

        }

        return listaAnalize;
    }

    public ArrayList<Consultatie> readConsultatii(String pathCSV, List<Medic> listaMedici) throws IOException {
        ArrayList<Consultatie> listaConsultatii = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(pathCSV));
        String line = null;
        Scanner scanner = null;


        String header = reader.readLine();
        scanner = new Scanner(header);
        List<String> headerList = Arrays.asList(scanner.nextLine().split(","));

        while((line = reader.readLine()) != null) {
            List<String> data = Arrays.asList(line.split(","));
            Consultatie instanta = new Consultatie();

            // Eu citesc:
            // 0        ->      nume,
            // 1        ->      pret,
            // 2        ->      locatieCabinet,
            // 3        ->      medicID,
            // 4        ->      descriereConsultatie,
            // 5        ->      limitaDeVarsta

            instanta.setNume(data.get(0));
            instanta.setPret(Integer.parseInt(data.get(1)));
            instanta.setLocatieCabinet(data.get(2));
            instanta.setDescriereConsultatie(data.get(4));


            int idMedic = Integer.parseInt(data.get(3));
            for (Medic medic: listaMedici) {
                List<Medic> listaMedic = new ArrayList<>();
                if(medic.getThisID() == idMedic) {
                    listaMedic.add(medic);
                    instanta.setListaDeMedici(listaMedic);
                    break;
                }
            }

            listaConsultatii.add(instanta);

        }

        return listaConsultatii;
    }

    public ArrayList<Ecografie> readEcografii(String pathCSV, List<Medic> listaMedici) throws IOException {
        ArrayList<Ecografie> listaEcografii= new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(pathCSV));
        String line = null;
        Scanner scanner = null;


        String header = reader.readLine();
        scanner = new Scanner(header);
        List<String> headerList = Arrays.asList(scanner.nextLine().split(","));

        while((line = reader.readLine()) != null) {
            List<String> data = Arrays.asList(line.split(","));
            Ecografie instanta = new Ecografie();

            // Eu citesc:
            // 0        ->      nume,
            // 1        ->      pret,
            // 2        ->      locatieCabinet,
            // 3        ->      medicID,
            // 4        ->      descriereEcografie,
            // 5        ->      limitaDeVarsta
            // 6        ->      durataInMinute

            instanta.setNume(data.get(0));
            instanta.setPret(Integer.parseInt(data.get(1)));
            instanta.setLocatieCabinet(data.get(2));
            instanta.setDescriereEcografie(data.get(4));
            instanta.setLimitaDeVarsta(Boolean.parseBoolean(data.get(5)));
            instanta.setDurataInMinute(Integer.parseInt(data.get(6)));


            int idMedic = Integer.parseInt(data.get(3));
            for (Medic medic: listaMedici) {
                List<Medic> listaMedic = new ArrayList<>();
                if(medic.getThisID() == idMedic) {
                    listaMedic.add(medic);
                    instanta.setListaDeMedici(listaMedic);
                    break;
                }
            }

            listaEcografii.add(instanta);

        }

        return listaEcografii;
    }



}
