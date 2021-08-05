package com.highload.socialNetwork.comparing.test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactComparing {
    public static void main(String[] args) throws IOException {
        HashSet<String> from1Ccontacts = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contactProblem/test/contactTest1C.csv", Charset.forName("windows-1251")))).lines().collect(Collectors.toSet());
        HashSet<String> fromObozContacts = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contactProblem/test/contactsObozTest.csv", Charset.forName("windows-1251")))).lines().collect(Collectors.toSet());
        HashSet<String> resultFio = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contactProblem/test/resultFio.csv", Charset.forName("windows-1251")))).lines().collect(Collectors.toSet());

//        Set<String> fio = from1Ccontacts.stream().map(elem -> {
//            String[] split = elem.split(";");
//            return split[2];
//        }).collect(Collectors.toSet());
        Set<String> stringSet = fromObozContacts.stream().filter(elem -> {
            String fio = elem.split(",")[2];
            if (resultFio.contains(fio)) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toSet());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/contactProblem/test/resultContactsAfterComparingWithFIO.csv",StandardCharsets.UTF_8));
        stringSet.forEach(el->{
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
