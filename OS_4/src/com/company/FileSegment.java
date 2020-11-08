package com.company;

import java.io.*;

public class FileSegment implements Serializable{
    private int indexInMemory;
    private int nextIndexMemory = -1;
    private int segmentNumber;
    private boolean select = false;
    public FileSegment(int indexInMemory, int segmentNumber){
        this.indexInMemory = indexInMemory;
        this.segmentNumber = segmentNumber;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getIndexInMemory(){
        return indexInMemory;
    }

    public void setIndexInMemory(int indexInMemory) {
        this.indexInMemory = indexInMemory;
    }

    public int getNextIndexMemory() {
        return nextIndexMemory;
    }

    public void setNextIndexMemory(int nextIndexMemory) {
        this.nextIndexMemory = nextIndexMemory;
    }

    public FileSegment clone() throws CloneNotSupportedException{

        return (FileSegment) super.clone();
    }

    public FileSegment clone2(FileSegment fileSegment) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);


        ous.writeObject(fileSegment);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);

        FileSegment cloneSegment = (FileSegment)ois.readObject();
        cloneSegment.setNextIndexMemory(-1);
        cloneSegment.setIndexInMemory(-1);
        return  cloneSegment;
    }

    @Override
    public String toString(){
        return "Сегмент " + segmentNumber;
    }
}
