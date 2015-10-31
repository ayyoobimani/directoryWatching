package client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by vandermonde on 10/25/15.
 */
public abstract class Client implements Runnable, Cloneable{
    String path, name;

    public void consumeFile(){};

    public void setFile(String path, String name){
        this.path = path;
        this.name = name;
    }

    public Object clone(){
        Object cloned = null;
        try {
            cloned = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }

    @Override
    public void run() {
        consumeFile();
    }

    BufferedReader getFileReader(String path, String name) {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(path +  "/" + name));
        } catch (FileNotFoundException e) {
            System.out.println("could not access the file");
            e.printStackTrace();
        }
        return bf;
    }
}
