package com.company;

import java.util.*;

public class TablePage {


    public class Row implements Comparable<Row>{
        Random random = new Random();
        int physicalID = -1;
        int pageID;
        boolean modifications = false;
        boolean recentAppeals = false;
        public Row(int pageID){
            this.pageID = pageID;
        }

        public void setPhysicalID(int physicalID) {
            this.physicalID = physicalID;
        }

        public void setRecentAppeals(boolean recentAppeals) {
            this.recentAppeals = recentAppeals;
        }

        public void setModifications(boolean modifications) {
            this.modifications = modifications;
        }

        public int getPhysicalID(){
            return physicalID;
        }

        @Override
        public String toString() {
            String str;
            if(physicalID != -1){
               str = physicalID + "     " + pageID + "      " + recentAppeals + "  " + modifications;
            } else{
                str = "-" + "     " + pageID + "      " + recentAppeals + "  " + modifications;
            }

            return str;
        }

        @Override
        public int compareTo(Row row) {
            int ratingThis = 0;
            int ratingPage = 0;

            if(this.physicalID == -1){
                ratingThis -= 10;
            }
            if(row.physicalID == -1){
                ratingPage -= 10;
            }

            if(!this.recentAppeals && row.recentAppeals){
                ratingThis += 2;
            }
            if(this.recentAppeals && !row.recentAppeals){
                ratingPage += 2;
            }

            if(!this.modifications && row.modifications){
                ratingThis++;
            }
            if(this.modifications && !row.modifications){
                ratingPage++;
            }

            if(ratingThis > ratingPage){
                return -1;
            }
            if(ratingThis < ratingPage){
                return 1;
            }else{
                return 0;
            }
        }
    }

    public TablePage(int processID){
        this.processID = processID;
    }

    int processID;
    private List<Row> listRow = new ArrayList<>();

    public void addRow(int pageID){
        Row row = new Row(pageID);
        listRow.add(row);
    }
    public Row getRow(int index){
        return listRow.get(index);
    }

    public void setRowPhysicalID(int pageID, int physicalID){
        listRow.get(pageID).setPhysicalID(physicalID);
    }


    public int getRowPhysicalID(int pageID) {
        return  listRow.get(pageID).physicalID;
    }

    public void setModifications(int pageID, boolean bool){
        listRow.get(pageID).setModifications(bool);
    }

    public void setRecentAppeals(int pageID, boolean bool){
        listRow.get(pageID).setRecentAppeals(bool);
    }


    public void sort(){
        Collections.sort(listRow);
    }

    @Override
    public String toString() {
        String str = "Физ | Вирт | Обращ | Мод" + '\n';
        for(int i = 0; i < listRow.size(); i++){
            str += listRow.get(i).toString() + '\n';
        }

        return str;

    }

}
