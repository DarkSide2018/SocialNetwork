package com.highload.socialNetwork;

import java.io.*;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UuidComapring {
    public static void main(String[] args) throws Exception {
        HashSet<String> from1CUuids = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/from1C.csv"))).lines().collect(Collectors.toSet());
        HashSet<String> fromOboz2Uuids = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/fromOboz2.csv"))).lines().collect(Collectors.toSet());
        BufferedWriter bufferedWriter30000 = new BufferedWriter(new FileWriter("src/main/resources/result1.csv"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/result2.csv"));
        HashSet<String> matchingSet = new HashSet<String>();
        from1CUuids.forEach(el -> {
            if (fromOboz2Uuids.contains(el)) {
                matchingSet.add(el);
            }
        });
        System.out.println(matchingSet.size());

        matchingSet.forEach(el->{
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
