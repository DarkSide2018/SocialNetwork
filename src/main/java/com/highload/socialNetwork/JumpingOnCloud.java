package com.highload.socialNetwork;

import java.util.Arrays;
import java.util.List;

public class JumpingOnCloud {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(0, 0, 0, 1, 0, 0);
        System.out.println(jumpingOnClouds(integers));
    }

    public static int jumpingOnClouds(List<Integer> c) {
        Jump jump = new Jump(c);
        Jump calculatedJump = calculateJump(jump);
        return calculatedJump.getCount();
    }
    public static Jump calculateJump(Jump jump){
        int cursor = jump.getCursor();
        cursor = cursor + 2;
        if(cursor == jump.getCloudsSize()){
            jump.incrementCount();
            return jump;
        }
        int currentCloud = jump.getCloud(cursor);
        if(currentCloud == 1) cursor--;
        jump.setCursor(cursor);
        jump.incrementCount();
        if(jump.getCursor() < jump.getCloudsSize()-1){
            calculateJump(jump);
        }
        return jump;
    }
}

class Jump{
    private int cursor = 0;
    private int count = 0;
    private final List<Integer> clouds;

    Jump(List<Integer> clouds) {
        this.clouds = clouds;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public int getCount() {
        return count;
    }
    public void incrementCount(){
        count++;
    }
    public int getCloud(int cursor){
        return clouds.get(cursor);
    }
    public int getCloudsSize(){
        return clouds.size();
    }
}
