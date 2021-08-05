package com.highload.socialNetwork.comparing.prod;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactComparing {
    public static void main(String[] args) throws IOException {
        HashSet<String> from1CUuids = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contactProblem/contact2.csv", Charset.forName("windows-1251")))).lines().collect(Collectors.toSet());
        HashSet<String> fromApp = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contactProblem/contacts_oboz.csv", StandardCharsets.UTF_8))).lines().collect(Collectors.toSet());
        HashSet<String> resultFio = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contactProblem/resultFio.csv", StandardCharsets.UTF_8))).lines().collect(Collectors.toSet());
        HashSet<String> comparingUuidContractor = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contactProblem/resultApp.csv", StandardCharsets.UTF_8))).lines().collect(Collectors.toSet());

        Set<String> contragents = from1CUuids.stream().map(elem -> {
            String[] split = elem.split(";");
            return split[5];
        }).collect(Collectors.toSet());

        List<String> stringListApp = comparingUuidContractor.stream().filter(elem->{
           if(contragents.contains(elem.split(",")[1])){
               return true;
           }else{
               return false;
           }

        }).collect(Collectors.toList());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/contactProblem/resultAppCOntragents.csv"));
        stringListApp.forEach(el->{
            try {
                bufferedWriter.write(el);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.close();
    }
}
