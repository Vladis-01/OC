package com.company;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private List<Page> listPage = new ArrayList<>();

    private int quantityPage = 5;
    private int ID;

    public int getQuantityPage(){
        return quantityPage;
    }

    public List<Page> getListPage() {
        return listPage;
    }

    public Process(int ID){
        this.ID = ID;
        createPage();
    }

    public int getID() {
        return ID;
    }

    private void createPage(){
        for(int i = 0; i < quantityPage; i++){
            System.out.println("     Создаем странцу " + i);
            Page page = new Page(ID, i);

            listPage.add(page);
        }
    }

    public Page getPage(int ID){
        for(int i = 0; i < listPage.size(); i++){
            if(listPage.get(i).getPageID() == ID){
                return listPage.get(i);
            }
        }
        return null;
    }




}
