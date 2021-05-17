package com.highload.socialNetwork;

import java.util.stream.Collectors;

public class InfiniteString {
    public static void main(String[] args) {
            repeatedString("a",1000000000000L);
    }

    public static long repeatedString(String s, long n) {
        long count = s.chars().count();
        long l = (n / count)+1;
        StringBuilder result = new StringBuilder(s);
        for (int i = 0; i < l; i++) {
            result.append(s);
        }
        return result.chars().mapToObj(c -> (char) c).collect(Collectors.toList()).stream().filter(el -> el.equals('a')).count();
    }
}
