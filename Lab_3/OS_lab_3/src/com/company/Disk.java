package com.company;

import java.util.ArrayList;
import java.util.List;

public class Disk {
    private List<Process> listProcess = new ArrayList<>();
    public void addProcess(Process process){
        listProcess.add(process);
    }

    public Process getProcess(int ID){
        for(int i = 0; i < listProcess.size(); i++){
            if(listProcess.get(i).getID() == ID){
                return listProcess.get(i);
            }
        }
        return null;
    }
}
