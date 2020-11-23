package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Чтобы удовлетворять условию: В обоих случаях все параметры должны быть идентичными, в программе не используется рандом, все время и номер процесса во время выполнения которого произойдет прерывания выставлены заранее" + '\n' + '\n');
        Core core = new Core();
        int quantityProcess = 3;
        int time;
        int time2;
        core.createProcess(quantityProcess);
        time = core.planningWithoutControlledInterrupt();
        time2 = core.planningWithSoftwareInterrupt();

        System.out.println("На процесс с управляемым прерыванием ушло " + time + " мс, а на процесс с программным прерыванием " + time2 + " мс");
    }
}
