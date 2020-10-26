package com.company;

public class Page {
    private int memorySize = 4; // 4 кб
    private int processID;
    private int pageID;


    public Page(int processID, int pageID) {
        this.processID = processID;
        this.pageID = pageID;

    }

    public int getPageID(){
        return pageID;
    }

    public int getProcessID() {
        return processID;
    }
}
