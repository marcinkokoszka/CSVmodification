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

    private String[][] linesToTable(String delimiter){
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

    private void removeQuoteCharacters(String quoteCharacter, String[][] data) {
        for (String[] s: data){
            for (int i = 0; i < data[0].length; i++){
                if (s[i] != null) s[i] = s[i].replace(quoteCharacter, "");
                else s[i] = "";
            }
        }
    }

    public String[][] getTable(String quoteCharacter, String delimiter){
        String[][] input = linesToTable(delimiter);
        String[][] data = new String[input.length][];
        for (int i = 0; i < input.length; i++){
            data[i] = new String[input[0].length];
            System.arraycopy(input[i], 0, data[i], 0, input[i].length);
        }
        removeQuoteCharacters(quoteCharacter, data);
        return data;
    }
}
