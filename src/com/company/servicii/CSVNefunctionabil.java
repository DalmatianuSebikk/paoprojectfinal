package com.company.servicii;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CSVNefunctionabil<T> {
    private String pathCSV;
    private Class<T> clasa;
    private List<T> listOfObjects = new ArrayList<T>();

    // defect pentru unele clase.

//    public T getInstanceOfT()
//            throws InstantiationException,
//            IllegalAccessException,
//            IllegalArgumentException,
//            InvocationTargetException,
//            NoSuchMethodException,
//            SecurityException {
//        return clasa.getDeclaredConstructor().newInstance();
//    }

    public CSVNefunctionabil(Class<T> clasa, String pathCSV) {
        this.clasa = clasa;
        this.pathCSV = pathCSV;
    }

    public T assignAndReturnObject(List<String> data, List<Field> fieldList)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        List<String> memberNames = new ArrayList<>();

        var instanta = clasa.getConstructor().newInstance(); // INSTANTA PE CARE O VOM RETURNA

        for(var field: fieldList) {
            System.out.println(field.getName());
            memberNames.add(field.getName());
        }

        System.out.println("--------------------------------------------");

        List<String> lista;
        if(memberNames.size() == data.size())
            lista = memberNames;
        else
            lista = data;

        for(int i = 0; i < lista.size(); i++) {
            Field field = null;
            try {
                field = clasa.getDeclaredField(memberNames.get(i));
            } catch (Exception e) {};

            if(field == null) {
                field = clasa.getSuperclass().getDeclaredField(memberNames.get(i));
            }
            field.setAccessible(true);

            if(field.getType() == String.class) {
                field.set(instanta, data.get(i));
            }
            else if(field.getType() == int.class) {
                field.set(instanta, Integer.parseInt(data.get(i)));
            }
            else if(field.getType() == boolean.class) {
                field.set(instanta, Boolean.parseBoolean(data.get(i)));
            }
            else if(field.getType() == float.class) {
                field.set(instanta, Float.parseFloat(data.get(i)));
            }
        }

//        for(String name: memberNames) {
//            Field field = clasa.getDeclaredField(name);
//
//           if(field == null) {
//                field = clasa.getSuperclass().getDeclaredField(name);
//           }
//
//           field.setAccessible(true);
//
//
//            if(field.getType() == String.class) {
//                field.set(instanta, data.get(i));
//            }
//            else if(field.getType() == int.class) {
//                field.set(instanta, Integer.parseInt(data.get(i)));
//            }
//            else if(field.getType() == boolean.class) {
//                field.set(instanta, Boolean.parseBoolean(data.get(i)));
//            }
//            else if(field.getType() == float.class) {
//                field.set(instanta, Float.parseFloat(data.get(i)));
//            }
//        }
//
        return instanta;

    }

    private int checkHeader(List<String> headerList, List<Field> fieldList) {
        List<String> memberNames = new ArrayList<>();

        for(var field: fieldList) {
            memberNames.add(field.getName());
        }

        for (String s : headerList) {
            if (!memberNames.contains(s)) {
                return -1;
            }
        }

        return 0;

    }

    public List<T> read() throws IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BufferedReader reader = new BufferedReader(new FileReader(pathCSV));
        String line = null;
        Scanner scanner = null;
        int index = 0;

        // prima data verific headerele

        String header = reader.readLine();
        scanner = new Scanner(header);
        List<String> headerList = Arrays.asList(scanner.nextLine().split(",")); // String[]
        List<Field> fieldList = new ArrayList<>(Arrays.asList(clasa.getDeclaredFields())); // limabjul asta este o gluma.
        List<Field> superFieldList = new ArrayList<>();
        if(clasa.getSuperclass() != null) {
            superFieldList = Arrays.asList(clasa.getSuperclass().getDeclaredFields());

            int indexForAdd = 0;
            for(Field field: superFieldList) {
                fieldList.add(indexForAdd, field);
                indexForAdd++;
            }
        }


//        for(Field field: fieldList) {
//            System.out.println(field);
//        }
        try {
            int isOk = checkHeader(headerList, fieldList);
            if(isOk == -1) {
                throw new Exception("Headerele nu sunt corecte. Verifica din nou");
            }
        } catch(Exception e) {
        }

        // Dupa care citesc fiecare linie si declar obiectele pe care le bag intr-o lista.

        while((line = reader.readLine()) != null) {
            List<String> data = Arrays.asList(line.split(","));
            T instanta = assignAndReturnObject(data, fieldList);
            listOfObjects.add(instanta);

        }
        return listOfObjects;
    }
}
