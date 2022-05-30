package com.company.mySQL;

import com.company.entitati.Analiza;
import com.company.entitati.Consultatie;
import com.company.entitati.Ecografie;
import com.company.entitati.Medic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseService {

    private static DatabaseService instance;
    private JDBCConnection jdbcConnection = JDBCConnection.getInstance();

    private DatabaseService() {
    }

    public static synchronized DatabaseService getInstance() {

        if(instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    //region
    // --------------- OPERATII CRUD PENTRU MEDICI ---------------

    public void selectAllMedici() {

        String sql = "SELECT * FROM medici";
        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Numar de pacienti:" + resultSet.getInt("numarDePacienti"));
                System.out.println("Specializare:" + resultSet.getString("specializare"));
            }

            System.out.println("Select successfully executed.");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public Medic selectMedicWithID(int id) {
        String sql = "SELECT * FROM medici WHERE ID=" + Integer.toString(id);
        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Numar de pacienti:" + resultSet.getInt("numarDePacienti"));
                System.out.println("Specializare:" + resultSet.getString("specializare"));

                Medic medic = new Medic(resultSet.getString("nume"), resultSet.getInt("numarDePacienti"),
                        resultSet.getString("specializare"));

                return medic;
            }

            System.out.println("ID Select successfully executed.");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }

    public void updateMedic(Medic medic, int id) {
        String sql = "UPDATE Medici " +
                "SET" +
                "nume = " + "'" + medic.getNume() + "'" + ", " +
                "numarDePacienti = " + "'" + Integer.toString(medic.getNumarDePacienti()) + "'" + ", " +
                "specializare = " + "'" + medic.getSpecializare() +"'" + " " +
                "WHERE id = " + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Update executed successfully");
            jdbcConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void deleteMedic(int id) {
        String sql = "DELETE FROM medici WHERE id =" + Integer.toString(id);
        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Delete executed sucessfully");
            jdbcConnection.closeConnection();
        }

        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
    //endregion

    //region
    // --------------- OPERATII CRUD PENTRU ANALIZE ---------------

    public void selectAllAnalize() {
        String sql = "SELECT * FROM analize";
        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Pret: " + resultSet.getInt("pret"));
                System.out.println("Locatie Cabinet: " + resultSet.getString("locatieCabinet"));
                System.out.println("Id Medic: " + resultSet.getInt("medicID"));
                System.out.println("Limita de varsta: " + resultSet.getBoolean("limitaDeVarsta"));
                System.out.println("Descriere analiza:" + resultSet.getString("descriereAnaliza"));
            }

            System.out.println("Select successfully executed.");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public Analiza selectAnalizaWithID(int id) {
        String sql = "SELECT * FROM analize WHERE ID=" + Integer.toString(id);
        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
//                System.out.println("ID: " + resultSet.getInt("id"));
//                System.out.println("Nume: " + resultSet.getString("nume"));
//                System.out.println("Pret: " + resultSet.getInt("pret"));
//                System.out.println("Locatie Cabinet: " + resultSet.getString("locatieCabinet"));
//                System.out.println("Id Medic: " + resultSet.getInt("medicID"));
//                System.out.println("Limita de varsta: " + resultSet.getBoolean("limitaDeVarsta"));
//                System.out.println("Descriere analiza:" + resultSet.getString("descriereAnaliza"));

                List<Medic> medic = new ArrayList<>();
                medic.add(this.selectMedicWithID(resultSet.getInt("medicID")));

                Analiza analiza = new Analiza(resultSet.getString("nume"),
                        resultSet.getInt("pret"),
                        resultSet.getString("locatieCabinet"),
                        medic,
                        resultSet.getBoolean("limitaDeVarsta"),
                        resultSet.getString("descriereAnaliza")
                        );

                return analiza;
            }

            System.out.println("ID Select successfully executed.");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }

    public void updateAnaliza(Analiza analiza, int id) {
        String sql = "UPDATE analize " +
                "SET " +
                "nume = " + "'" + analiza.getNume() + "'" + ", " +
                "pret = " + Integer.toString(analiza.getPret()) + ", " +
                "locatieCabinet = " + "'" + analiza.getLocatieCabinet() + "'" + ", " +
                "limitaDeVarsta = " +  Boolean.toString(analiza.getLimitaDeVarsta()) + ", " +
                "descriereAnaliza = " + "'" + analiza.getDescriereAnaliza() + "'" + " " +
                "WHERE id = " + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Update executed successfully");
            jdbcConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void deleteAnaliza(int id) {
        String sql = "DELETE FROM analize WHERE id =" + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Delete executed sucessfully");
            jdbcConnection.closeConnection();
        }

        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    //endregion

    //region
    // --------------- OPERATII CRUD PENTRU ECOGRAFII ---------------

    public void selectAllEcografii() {
        String sql = "SELECT * FROM ecografii";

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Pret: " + resultSet.getInt("pret"));
                System.out.println("Locatie Cabinet: " + resultSet.getString("locatieCabinet"));
                System.out.println("Id Medic: " + resultSet.getInt("medicID"));
                System.out.println("Limita de varsta: " + resultSet.getBoolean("limitaDeVarsta"));
                System.out.println("Descriere ecografie: " + resultSet.getString("descriereEcografie"));
                System.out.println("Durata in minute: " + resultSet.getInt("durataInMinute"));
            }

            System.out.println("Select executed successfully");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }

    public Ecografie selectEcografieWithID(int id) {
        String sql = "SELECT * FROM ecografii WHERE ID=" + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Pret: " + resultSet.getInt("pret"));
                System.out.println("Locatie Cabinet: " + resultSet.getString("locatieCabinet"));
                System.out.println("Id Medic: " + resultSet.getInt("medicID"));
                System.out.println("Limita de varsta: " + resultSet.getBoolean("limitaDeVarsta"));
                System.out.println("Descriere ecografie: " + resultSet.getString("descriereEcografie"));
                System.out.println("Durata in minute: " + resultSet.getInt("durataInMinute"));

//                Medic medic = this.selectMedicWithID(resultSet.getInt("medicID"));
                List<Medic> medic = new ArrayList<>();
                medic.add(this.selectMedicWithID(resultSet.getInt("medicID")));

                Ecografie ecografie = new Ecografie(
                        resultSet.getString("nume"),
                        resultSet.getInt("pret"),
                        resultSet.getString("locatieCabinet"),
                        medic,
                        resultSet.getString("descriereEcografie"),
                        resultSet.getBoolean("limitaDeVarsta"),
                        resultSet.getInt("durataInMinute")
                );

                return ecografie;
            }

            System.out.println("Select executed successfully");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }

    public void updateEcografie(Ecografie ecografie, int id) {
        String sql = "UPDATE ecografii " +
                "SET " +
                "nume = " + "'" + ecografie.getNume() + "'" + ", " +
                "pret = " + Integer.toString(ecografie.getPret()) + ", " +
                "locatieCabinet = " + "'" + ecografie.getLocatieCabinet() + "'" + ", " +
                "limitaDeVarsta = " + Boolean.toString(ecografie.getLimitaDeVarsta()) + ", " +
                "descriereEcografie = " + "'" + ecografie.getDescriereEcografie() + "'" + " " +
                "WHERE id = " + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Update executed successfully");
            jdbcConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


    public void deleteEcografie(int id) {
        String sql = "DELETE FROM ecografii WHERE id =" + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Delete executed sucessfully");
            jdbcConnection.closeConnection();
        }

        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    //endregion

    //region
    //--------------- OPERATII CRUD PENTRU CONSULTATII ---------------

    public void selectAllConsultatii() {
        String sql = "SELECT * FROM consultatii";

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Pret: " + resultSet.getInt("pret"));
                System.out.println("Locatie Cabinet: " + resultSet.getString("locatieCabinet"));
                System.out.println("Id Medic: " + resultSet.getInt("medicID"));
                System.out.println("Descriere consultatie: " + resultSet.getString("descriereConsultatie"));
            }

            System.out.println("Select executed successfully");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public Consultatie selectConsultatieWithID(int id) {
        String sql = "SELECT * FROM consultatii WHERE id=" + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Pret: " + resultSet.getInt("pret"));
                System.out.println("Locatie Cabinet: " + resultSet.getString("locatieCabinet"));
                System.out.println("Id Medic: " + resultSet.getInt("medicID"));
                System.out.println("Descriere consultatie: " + resultSet.getString("descriereConsultatie"));

                List<Medic> medic = new ArrayList<>();

                medic.add(this.selectMedicWithID(resultSet.getInt("medicID")));

                Consultatie consultatie = new Consultatie(
                        resultSet.getString("nume"),
                        resultSet.getInt("pret"),
                        resultSet.getString("locatieCabinet"),
                        medic,
                        resultSet.getString("descriereConsultatie")
                );

                return consultatie;
            }

            System.out.println("Select executed successfully");
            resultSet.close();
            jdbcConnection.closeConnection();
        }
        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }

    public void updateConsultatie(Consultatie consultatie, int id) {
        String sql = "UPDATE consultatii " +
                "SET " +
                "nume = " + "'" + consultatie.getNume() + "'"  + ", " +
                "pret = " + "'" +Integer.toString(consultatie.getPret()) + "'" +  ", " +
                "locatieCabinet = " + "'" + consultatie.getLocatieCabinet() + "'" + ", " +
                "descriereConsultatie = " + "'" + consultatie.getDescriereConsultatie() + "'" + " " +
                "WHERE id = " + Integer.toString(id);

        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Update executed successfully");
            jdbcConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteConsultatie(int id) {
        String sql = "DELETE FROM consultatii WHERE id =" + Integer.toString(id);
        try {
            Connection conn = jdbcConnection.initializeConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate(sql);
            System.out.println("Delete executed sucessfully");
            jdbcConnection.closeConnection();
        }

        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    //endregion


}
