package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class DiskPanel extends JPanel {
    public DiskPanel() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }


    private void draw(Graphics g) {

        int coordX = 0;
        int coordY = 0;
        int count = 0;
        for(int i = 0; i < Disk.memoryDisk.length; i++){
            count++;
            if(Disk.memoryDisk[i] == null){
                g.setColor(Color.GRAY);
            }

            if(Disk.memoryDisk[i] != null){
                g.setColor(Color.BLUE);
                if(Disk.memoryDisk[i].isSelect()){
                    g.setColor(Color.RED);
                }
            }
            g.fillRect(coordX, coordY, 10, 10);
            coordX += 15;;

            if(count % 35 == 0){
                coordY += 15;
                coordX = 0;
                count = 0;
            }
        }
    }
}
