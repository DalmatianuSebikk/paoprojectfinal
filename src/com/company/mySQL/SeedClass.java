package com.company.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SeedClass {
    private static SeedClass instance;

    private SeedClass() {

    }

    public static synchronized SeedClass getInstance() {
        if(instance == null) {
            instance = new SeedClass();
        }
        return instance;
    }

    private JDBCConnection jdbcConnection = JDBCConnection.getInstance();

    public void seed() {

        String createTableMedici =
                "CREATE TABLE IF NOT EXISTS Medici (" +
                        "id INT AUTO_INCREMENT NOT NULL," +
                        "nume VARCHAR(45) NOT NULL," +
                        "numarDePacienti INT NOT NULL," +
                        "specializare VARCHAR(45) NOT NULL," +
                        "PRIMARY KEY (id)" +
                        ")";

        String createTableAnalize =
                "CREATE TABLE IF NOT EXISTS Analize(" +
                        "id INT AUTO_INCREMENT NOT NULL," +
                        "nume VARCHAR(45) NOT NULL," +
                        "pret INT NOT NULL," +
                        "locatieCabinet VARCHAR(45) NOT NULL," +
                        "medicID INT," +
                        "limitaDeVarsta BOOLEAN NOT NULL," +
                        "descriereAnaliza VARCHAR(100) NOT NULL," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (medicID) REFERENCES Medici(id)" +
                        ")";

        String createTableConsultatii =
                "CREATE TABLE IF NOT EXISTS Consultatii(" +
                        "id INT AUTO_INCREMENT NOT NULL," +
                        "nume VARCHAR(45) NOT NULL," +
                        "pret INT NOT NULL," +
                        "locatieCabinet VARCHAR(45) NOT NULL," +
                        "medicID INT," +
                        "descriereConsultatie VARCHAR(100) NOT NULL," +
                        "limitaDeVarsta BOOLEAN NOT NULL," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (medicID) REFERENCES Medici(id)" +
                        ")";

        String createTableEcografii =
                "CREATE TABLE IF NOT EXISTS Ecografii(" +
                        "id INT AUTO_INCREMENT NOT NULL," +
                        "nume VARCHAR(45) NOT NULL," +
                        "pret INT NOT NULL," +
                        "locatieCabinet VARCHAR(45) NOT NULL," +
                        "medicID INT," +
                        "descriereEcografie VARCHAR(100) NOT NULL," +
                        "limitaDeVarsta BOOLEAN NOT NULL," +
                        "durataInMinute INT NOT NULL," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (medicID) REFERENCES Medici(id)" +
                        ")";

        String insertIntoMedici =
                "INSERT INTO medici(nume,numarDePacienti,specializare) " +
                        "VALUES" +
                        "(\"Sebi Ionel\", 0, \"Biochimie\")," +
                        "(\"Andrei Maxim\", 5, \"Biochimie\")," +
                        "(\"Gheorghita Zaharia\", 8,\"Imunologie\")," +
                        "(\"Mihaela Scutelnicu\", 4,\"Imunologie\")," +
                        "(\"Petronela Maria\", 6,\"Hematologie\")," +
                        "(\"Ioan Rosca\", 3,\"Ecografie cardiaca\")," +
                        "(\"Alex Lupascu\", 2,\"Consultatii\")," +
                        "(\"Malus Camelusa\", 5,\"Ecografie abdomen\")," +
                        "(\"Slincu Elena\", 2, \"Psihiatrie\")";

        String insertIntoAnalize =
                "INSERT INTO Analize(nume,pret,locatieCabinet,medicID,limitaDeVarsta,descriereAnaliza) " +
                        "VALUES" +
                        "(\"APTT\",18,\"Bucuresti\",5,true,\"Timp de executie: 1 zi\")," +
                        "(\"Acid uric seric\",11,\"Bucuresti\",1,false,\"Timp de executie: 1 zi\")," +
                        "(\"Feritina\",40,\"Bucuresti\",2,false,\"Timp de execitie: 1-3 Zile\")";

        String insertIntoConsultatii =
                "INSERT INTO Consultatii(nume,pret,locatieCabinet,medicID,descriereConsultatie,limitaDeVarsta) " +
                        "VALUES(" +
                        "\"Consultatie de control psihiatrie\",60,\"Bucuresti\",5, \"Evaluarea psihologica si tratarea depresiei\", false" +
                        ")";

        String insertIntoEcografii =
                "INSERT INTO Ecografii(nume,pret,locatieCabinet,medicID,descriereEcografie,limitaDeVarsta,durataInMinute) " +
                        "VALUES(" +
                        "\"Ecografie cardiaca Doppler color IIID\",150,\"Bucuresti\",5, \"Ecografie Doppler este un efect fizic descris inca din 1842.. definind comportamentul unei unde\",false,80" +
                        ")";

        try {
            Connection conn = jdbcConnection.initializeConnection();
            // STATEMENT CA SA CREEZ TABELELE
            Statement statement = conn.createStatement();

            System.out.println("Creating Tables...");

            statement.execute(createTableMedici);
            statement.execute(createTableAnalize);
            statement.execute(createTableConsultatii);
            statement.execute(createTableEcografii);

            System.out.println("Tables created successfully!");

            System.out.println("Seeding Tables..");
            statement.execute(insertIntoMedici);
            statement.execute(insertIntoAnalize);
            statement.execute(insertIntoConsultatii);
            statement.execute(insertIntoEcografii);

            System.out.println("Seeding successfuly!");
            jdbcConnection.closeConnection();
        }
        catch(Exception e) {
            System.out.println("There was an error:");
            System.out.println(e);
        }
    }
}
