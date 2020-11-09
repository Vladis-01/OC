package com.company;

import java.util.ArrayList;
import java.util.List;

public class Disk {
    private List<Page> listPage = new ArrayList<>();
    public void addPage(Page page){
        listPage.add(page);
    }

    public Page getPage(int pageID, int processID){
        for(int i = 0; i < listPage.size(); i++){
            if(listPage.get(i).getPageID() == pageID && listPage.get(i).getProcessID() == processID){
                return listPage.get(i);
            }
        }
        return null;
    }
}
