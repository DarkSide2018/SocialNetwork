package com.highload.socialNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HourGlasses {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<List<Integer>> twoDArray = Arrays.asList(integers, integers, integers, integers, integers, integers);
        hourglassSum(twoDArray);
    }

    public static int hourglassSum(List<List<Integer>> arr) {
        System.out.println(arr);
        arr.forEach(System.out::println);

        return 7;
    }
    public static List<List<Integer>> convertTOGlasses(List<List<Integer>> arr){
        arr.forEach(el->
                {
                    List<Integer> integers = el.subList(0, 2);

                }
                );
        return new ArrayList<>();
    }
}
