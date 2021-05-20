package com.highload.socialNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class HourGlasses {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<List<Integer>> twoDArray = Arrays.asList(integers, integers, integers, integers, integers, integers);
        hourglassSum(twoDArray);
    }

    public static int hourglassSum(List<List<Integer>> arr) {
        System.out.println(arr);
        arr.forEach(System.out::println);
        convertTOGlasses(arr);
        return 7;
    }

    public static List<List<Integer>> convertTOGlasses(List<List<Integer>> arr) {
        int arrSize = arr.size();
        List<List<Integer>> glasses = new ArrayList<>();
        IntStream.range(0, arrSize).forEach(index -> {
            List<Integer> el = arr.get(index);
            System.out.println("index -> " + index);
            int i1 = index % 2;
            System.out.println("ostatok -> " + i1);
            if (i1 != 0) {
                for (int i = 1; i < el.size() - 1; i++) {
                    glasses.add(Arrays.asList(0, el.get(i), 0));
                }
            } else {
                System.out.println("i1 == 0");
                for (int i = 0; i < el.size() - 2; i++) {
                    List<Integer> integers = el.subList(i, i + 3);
                    glasses.add(integers);
                }
            }
            System.out.println("current glasses -> " + glasses);
        });
        glasses.forEach(System.out::println);
        System.out.println("---------------------");
        return new ArrayList<>();
    }
}
