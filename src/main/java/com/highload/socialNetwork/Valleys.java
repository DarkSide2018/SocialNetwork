package com.highload.socialNetwork;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Valleys {
    public static void main(String[] args) {
        countingValleys(6, "UDUDUUDDDUUUD");
    }

    public static int countingValleys(int steps, String path) {
        ArrayList<Character> characterList = (ArrayList<Character>) path.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        AtomicInteger currentLevel = new AtomicInteger(0);
        AtomicBoolean valleyNow = new AtomicBoolean(false);
        AtomicInteger valleyCount = new AtomicInteger(0);
        characterList.forEach(el-> {
            if (el.equals('U')) {
               currentLevel.incrementAndGet();
            }else{
                currentLevel.decrementAndGet();
            }
            if(currentLevel.get()<0){
                valleyNow.getAndSet(true);
            }
            if(currentLevel.get()==0 && valleyNow.get()){
                valleyNow.getAndSet(false);
                valleyCount.incrementAndGet();
            }
        });
        return valleyCount.get();
    }

}
