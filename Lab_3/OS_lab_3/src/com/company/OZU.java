package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OZU {
    final private int physicalMemorySize = 40; // 40 кб
    public int getPhysicalMemorySize(){
        return physicalMemorySize;
    }
    private List<Page> listPhysicalPage = new ArrayList<>();

    public void addPageINListPhysicalPage(int tablePageID, Page page) {
        if(listTablePage.get(tablePageID).getRowPhysicalID(page.getPageID()) != -1){
            System.out.println("Страница " + page.getPageID() + " находится в ОЗУ" + '\n');
            return;
        }
        System.out.println("В ОЗУ подгружается с диска страница " + page.getPageID() + " процесса " + tablePageID + '\n');

        listPhysicalPage.add(page);

        for(int i = 0; i < listPhysicalPage.size(); i++){
            if(listPhysicalPage.get(i) == page){
                listTablePage.get(tablePageID).setRowPhysicalID(page.getPageID(), i);
            }
        }
        physicalMemorySize -= 4;
    }

    public void NRU(int tablePageID, Page page) {
        for (int j = 0; j < 5; j++) { // кол-во рядов
            if (listTablePage.get(tablePageID).getRow(j).pageID == page.getPageID()) {
                if (listTablePage.get(tablePageID).getRow(j).getPhysicalID() != -1) {
                    System.out.println("Страница " + page.getPageID() + " находится в ОЗУ" + '\n');
                    return;
                }
            }

        }
        Random random = new Random();
        System.out.println("В ОЗУ не достаточно памяти");
        System.out.println("Используем алгоритм NRU для нахождения наиболее бесполезной страницы ");
        while (true) {
            int delTable = random.nextInt(3);
            listTablePage.get(delTable).sort();
            if (listTablePage.get(delTable).getRow(0).getPhysicalID() != -1) {
                System.out.println("Алгоритм определил физическую страницу " + listTablePage.get(delTable).getRow(0).getPhysicalID() + " процесса " + delTable + " наиболее бесполезной");

                listPhysicalPage.set(listTablePage.get(delTable).getRow(0).getPhysicalID(), page);
                int cashID = listTablePage.get(delTable).getRow(0).getPhysicalID();
                for (int j = 0; j < 5; j++) { // кол-во рядов
                    if (listTablePage.get(tablePageID).getRow(j).pageID == page.getPageID()) {
                        listTablePage.get(tablePageID).getRow(j).setPhysicalID(cashID);
                        j = 5;
                    }

                }
                listTablePage.get(delTable).getRow(0).setPhysicalID(-1);
                listTablePage.get(delTable).getRow(0).setModifications(false);
                listTablePage.get(delTable).getRow(0).setRecentAppeals(false);

                System.out.println("Произошло удаление страницы");
                System.out.println("В ОЗУ подгружается с диска страница " + page.getPageID() + " процесса " + tablePageID  + '\n');
                return;
            }
        }
    }

    private List<TablePage> listTablePage = new ArrayList();

    public List<TablePage> getListTablePage() {
        return listTablePage;
    }

    public void addListTablePage(TablePage tablePage ){
        listTablePage.add(tablePage);
    }

    public void printListPhysicalPage(){
        System.out.println("Таблица физических страниц");
        System.out.println("Физ | Стр | Проц");
        for(int i = 0; i < listPhysicalPage.size(); i++){
            System.out.println(i + "     " + listPhysicalPage.get(i).getProcessID() + "     " + listPhysicalPage.get(i).getPageID());
        }
    }

    public void printListTablePage(){
        for(int i = 0; i < listTablePage.size(); i++){
            System.out.println("Таблица страниц процесса " + i);
            System.out.println(listTablePage.get(i).toString());
        }
    }


}
