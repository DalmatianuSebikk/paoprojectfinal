package com.company.servicii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class AuditService {

    private static AuditService instance;

    private AuditService() {
    }

    public static synchronized AuditService getInstance() {
        if(instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void initializeAudit(String pathCSV) throws IOException {
        FileWriter file = new FileWriter(pathCSV);
        BufferedWriter csvWriter = new BufferedWriter(file);

        // initializez cu ce vreau eu sa scriu in el acum

        csvWriter.append("comandaUtilizata,dataUtilizare,user\n");
        csvWriter.close();
        file.close();
    }

    public void addInAudit(String pathCSV, String command, String user) throws IOException {
        FileWriter file = new FileWriter(pathCSV, true);
        BufferedWriter csvWriter = new BufferedWriter(file);

        csvWriter.append(command);
        csvWriter.append(",");
        csvWriter.append(String.valueOf(LocalTime.now()));
        csvWriter.append(",");
        csvWriter.append(user);
        csvWriter.append("\n");

        csvWriter.close();
        file.close();
    }


}
