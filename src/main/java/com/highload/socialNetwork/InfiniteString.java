package com.highload.socialNetwork;

import java.util.List;
import java.util.stream.Collectors;

public class InfiniteString {
    public static void main(String[] args) {

        System.out.println(repeatedString("ab", 7L));
    }

    public static long repeatedString(String s, long n) {
        long count = s.chars().count();
        long sTimes = (n / count);
        List<Character> characterList = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        long countAChar = characterList.stream().filter(el -> el.equals('a')).count();
        long countInFullStrings = countAChar * sTimes;
        long lengthOfFullStrings = count * sTimes;
        long ending = n-lengthOfFullStrings;
        for (int i = 0; i < ending; i++) {
            if(characterList.get(i).equals('a')){
                countInFullStrings++;
            }
        }
        return countInFullStrings;
    }
}
