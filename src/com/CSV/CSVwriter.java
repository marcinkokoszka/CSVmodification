package com.CSV;

import java.io.*;

/**
 * saves the modified data to csv file
 */
public class CSVwriter {

    private String fileName;

    public CSVwriter(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean writeCSV(String[] headers, String[][] data, String quoteCharacter, String delimiter, String fileEncoding) {

            try (FileOutputStream fos = new FileOutputStream(fileName);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, fileEncoding);
                 BufferedWriter bw = new BufferedWriter(osw)) {

                String content = arrayToString(headers, quoteCharacter, delimiter);
                content = content + tableToString(data, quoteCharacter, delimiter);
                bw.write(content);

                return true;

            } catch (IOException e) {

                e.printStackTrace();

            }

            return false;
        }

    private String tableToString(String[][] m, String quoteCharacter, String delimiter) {
        String content = "";

        for (String[] d : m) {
            for (String i : d) {
                content = content + quoteCharacter + i + quoteCharacter + delimiter;
            }
            content = content + "\n";
        }
        return content;
    }

    private String arrayToString(String[] m, String quoteCharacter, String delimiter) {
        String content = "";

        for (String d : m) {
                content = content + quoteCharacter + d + quoteCharacter + delimiter;
        }
        content = content + "\n";
        return content;
    }

    }
