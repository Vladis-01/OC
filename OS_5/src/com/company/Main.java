package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Чтобы удовлетворять условию: В обоих случаях все параметры должны быть идентичными, в программе не используется рандом, все время и номер процесса во время выполнения которого произойдет прерывания выставлены заранее" + '\n' + '\n');
        Core core = new Core();
        int quantityProcess = 3;

        core.createProcess(quantityProcess);
        core.planningWithoutControlledInterrupt();
        core.planningWithSoftwareInterrupt();


    }
}
