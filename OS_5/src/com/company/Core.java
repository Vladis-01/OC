package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Core {
    private Deque<Process> processesQ = new LinkedList<>();
    private Deque<Process> processesQ2 = new LinkedList<>();
    private Queue<Process> interruptQ = new LinkedList<>();
    private Process processes;
    private Process processesCash;
    private int allTime = 0;
    private int allTime2 = 0;
    private int interruptTime = 2000;
    private boolean interruptCheck = true;
    Timer timer;

    public void planningWithoutControlledInterrupt() {
        System.out.println("Управляемое прерывание: ");
        processes = processesQ.pollFirst();
        while(processes != null){
            int check = processes.start();
            if(check == 1){
                processesQ.addLast(processes);
            }
            if(check == 2){
                allTime += processes.getTime();
                processes = processesQ.pollFirst();
            }
            if(check == 3){
                if(interruptCheck){
                    interruptCheck = false;
                    interrupt();
                }else{
                    interruptQ.add(processes);
                    processes = processesQ.pollFirst();
                }
            }
            while (!interruptCheck && processes == null){}

            if(processes == null && interruptQ.peek() != null){
                processes = interruptQ.poll();
                interruptCheck = false;
                interrupt();
            }
            if(processes == null && interruptQ.peek() == null){
                processes = processesQ.poll();
            }
        }
        System.out.println("Время с использованием управляемого прерывания: " + allTime + " мс" + '\n');
    }

    public void interrupt(){
        processesCash = processes;
        System.out.println("Произошло прерывание для взаимодействия с устройством ввода/вывода, планировщик не остановлен");
        processes = processesQ.pollFirst();

        timer = new Timer(interruptTime, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                processesQ.addFirst(processesCash);

                System.out.println("Взаимодействие с устройством ввода/вывода окончено, процесс " + processesCash.getNumber() +
                        " разблокирован и продолжит выполнение");
                timer.stop();
                interruptCheck = true;
            }});
        timer.start();
    }

    public void planningWithSoftwareInterrupt() {
        System.out.println("Программное прерывание: ");
        processes = processesQ2.pollFirst();
        while(processes != null){
            int check = processes.start();
            if(check == 1){
                processesQ.addLast(processes);
            }
            if(check == 2){
                allTime2 += processes.getTime();
                processes = processesQ2.pollFirst();
            }
            if(check == 3){
                System.out.println("Произошло прерывание для взаимодействия с устройством ввода/вывода , планировщик остановлен");
                Process processCash = processes;

                try {
                    Thread.sleep(interruptTime);
                    processesQ.addFirst(processCash);
                    System.out.println("Взаимодействие с устройством ввода/вывода окончено, процесс " + processCash.getNumber() +
                            " разблокирован и продолжит выполнение");
                    allTime2 += interruptTime;
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("Время с использованием программного прерывания: " + allTime2 + " мс" + '\n');
    }

    public void createProcess(int quantityProcess) {
        for(int i = 0; i < quantityProcess; i++){
            int maxTime = 2000;
            processes = new Process(i, maxTime, false);
            if(i == 0){
                processes.setInterrupt(true); // помечаем, что во время этого процесса произойдет блокировка
            }
            processesQ.add(processes);
        }

        for(int i = 0; i < quantityProcess; i++){
            int maxTime = 2000;
            processes = new Process(i, maxTime, false);
            if(i == 0){
                processes.setInterrupt(true); // помечаем, что во время этого процесса произойдет блокировка
            }
            processesQ2.add(processes);
        }

    }
}