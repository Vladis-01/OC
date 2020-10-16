package com.company;

import java.util.Random;

public class MyThread {
    Random random = new Random();
    private int number;
    private int time;
    private int sleepTime = random.nextInt(4) + 1;
    private int allTime = 0;

    public MyThread(int number, int time){
        this.number = number;
        this.time = time;
    }

    public boolean start(){
        System.out.print("       Поток " + number + " начинает выполнение" + '\n');
        try {
            Thread.sleep(sleepTime);


        } catch (InterruptedException ex) {}
        allTime += sleepTime;
        if(sleepTime > time){
            System.out.print("       Поток " + number + " приостановлен" + " Времени затрачено: " + sleepTime + '\n');
            sleepTime -= time;
            return true;
        }
        System.out.print("       Поток " + number + " выполнен"  + " Времени затрачено: " + sleepTime + '\n');
        return false;
    }

    public int getSleepTime(){
        return sleepTime;
    }
}
