package com.CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * loads the information from csv file
 */
public class CSVloader {

    private String fileName;

    public CSVloader(String fileName){
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private ArrayList<String> getLines() {

        try (FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr)){

            ArrayList<String> sLines = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                sLines.add(line);
            }

            return sLines;

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

    public String[][] getTable(String delimiter){
        ArrayList<String> lines = getLines();
        if (lines != null) {
            String[][] table = new String[lines.size()][];
            for (int i = 0; i < lines.size(); i++){
                table[i] = lines.get(i).split(delimiter);
            }
            return table;
        }
        return null;
    }
}
