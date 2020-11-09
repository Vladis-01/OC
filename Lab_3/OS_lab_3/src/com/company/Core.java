package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Core {
    int quantityProcess = 3;
    Disk disk = new Disk();
    OZU ozu = new OZU();
    Random random = new Random();

    public void createProcess(){ // Создаем процесс и отправляем на диск
        for(int i = 0; i < quantityProcess; i++){
            System.out.println("Создаем процесс " + i);

            Process process = new Process(i);
            createTable(process);
            disk.addProcess(process);
            addPageInOZU(process.getID(), 0);
            addPageInOZU(process.getID(), 1);
        }
    }

    public void createTable(Process process){
        TablePage tablePage = new TablePage(process.getID());
        for(int i = 0; i < process.getQuantityPage(); i++){
            tablePage.addRow(process.getPage(i).getPageID());
        }
        ozu.addListTablePage(tablePage);
    }

    public void addPageInOZU(int processID, int pageID){
        System.out.println("ОС требует страницу " + pageID + " процесса " + processID);
        if(ozu.getFreePlaceInMemory() > 0){
            ozu.addPageINListPhysicalPage(processID, disk.getProcess(processID).getPage(pageID));
        }else{

            ozu.NRU(processID, disk.getProcess(processID).getPage(pageID));
        }

    }

    public void start(){
        while(true){
            for(int i = 0; i < 10; i++){
                addPageInOZU(random.nextInt(3), random.nextInt(5));
            }

            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 5; j++){
                    int tableID = random.nextInt(3);
                    if(ozu.getListTablePage().get(tableID).getRowPhysicalID(j) != -1){
                        ozu.getListTablePage().get(tableID).setModifications(j, random.nextBoolean());
                        ozu.getListTablePage().get(tableID).setRecentAppeals(j, random.nextBoolean());
                    }

                }

            }
            ozu.printListTablePage();
            ozu.printListPhysicalPage();

            System.out.println("Для продолжения введите любую цифру, для завершения цифру 0");
            Scanner scanner = new Scanner(System.in);
            if(scanner.nextInt() == 0){
                return;
            }

        }

    }
}
