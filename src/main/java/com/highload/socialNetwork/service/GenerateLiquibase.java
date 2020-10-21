package com.highload.socialNetwork.service;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateLiquibase {
    private static Random random = new Random();

    public static void main(String[] args) {
        Class clazz = GenerateLiquibase.class;
        InputStream testData = clazz.getResourceAsStream("/testData/names.csv");
        InputStream insertTempl = clazz.getResourceAsStream("/testData/insertTemplate.txt");
        InputStream liquiTempl = clazz.getResourceAsStream("/testData/lTemplate.txt");
        List<String> insertList = new ArrayList<>();
        try {
            List<String> data = getLinesFromInputStream(testData);
            String insert = readFromInputStream(insertTempl);
            data.forEach(el -> {
                String[] split = el.split(",");
                int i = random.nextInt(50);
                String gender = "male";
                String surName = split[0];
                if (surName.endsWith("а")) {
                    gender = "female";
                }
                String modInsert = insert.replace("%NAME%", split[1])
                        .replace("%SURNAME%", surName)
                        .replace("%AGE%", String.valueOf(i))
                        .replace("%GENDER%",gender);
                insertList.add(modInsert);
            });

            String liqui = readFromInputStream(liquiTempl);
            System.out.println(data);
            System.out.println(insert);
            System.out.println(liqui);
            String join = String.join("\n", insertList);
            OffsetDateTime offsetDateTime = OffsetDateTime.now();

            String res = liqui.replace("%INSERT%", join).replace("%DATE%", offsetDateTime.toString());
            try {
                File newTextFile = new File("src/main/resources/testData/fillClient.xml");
                File directory = newTextFile.getParentFile();
                FileWriter fw = new FileWriter(directory +"/"+ newTextFile.getName());
                fw.write(res);
                fw.close();
            } catch (IOException iox) {
                iox.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private static List<String> getLinesFromInputStream(InputStream inputStream)
            throws IOException {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringList.add(line);
            }
        }
        return stringList;
    }
}
