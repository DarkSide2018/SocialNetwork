package com.highload.socialNetwork;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//    "ООО \"ОБОЗ ДИДЖИТАЛ\"" to 1,
//            "ООО \"ОБОЗ\"" to 2

public class ContractComparing {
    static String oboz1C = "Общество с ограниченной ответственностью \"\"ОБОЗ\"\"";
    static String oboz = "Общество с ограниченной ответственностью ОБОЗ";
    static String obozDigital = "ООО ОБОЗ Диджитал";
    static  HashMap<String, String> orgMap = new HashMap<>() {{
        put(obozDigital, "1");
        put(oboz, "2");
    }};
    public static void main(String[] args) throws IOException {


        HashSet<String> from1CUuids = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contracts1C.csv", Charset.forName("windows-1251")))).lines().collect(Collectors.toSet());
        HashSet<String> fromApp = (HashSet<String>) new BufferedReader((new FileReader("src/main/resources/contractsApp.csv", Charset.forName("windows-1251")))).lines().collect(Collectors.toSet());
        Map<String, String> fromAppCollect = fromApp.stream().collect(Collectors.toMap(x -> x.split(",")[0].replaceAll("\"", ""), Function.identity()));

        from1CUuids.stream().filter(el -> el.contains("Договор-оферта")).forEach(el -> {
            if (el.contains(oboz1C)) {
                checkMatch(el,fromAppCollect);
            }
            if (el.contains(obozDigital)) {
               checkMatch(el,fromAppCollect);
            }
        });
    }
    public static void checkMatch(String el, Map<String, String> fromAppCollect){
        String fromAppElement = fromAppCollect.get(el.split(",")[1]);
        if (fromAppElement != null) {
            String orgFromApp = fromAppElement.split(",")[8].replaceAll(" ", "").replaceAll("\"", "");

            String organization = el.split(",")[4].replaceAll("\"", "");
            if (!orgFromApp.equals(orgMap.get(organization))) {
                String orgMapValue = orgMap.entrySet().stream().filter(elem -> elem.getValue().equals(orgFromApp)).findFirst().get().getKey();
                System.out.println(String.format("не совпала организация | %s |  в элементе | %s | должна быть | %s | ",organization,fromAppElement,orgMapValue));
            }
        }
    }
}
