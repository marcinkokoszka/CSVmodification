package com.CSV;

/**
 * Loads the products.csv file, performs the transformations and writes the modified data to result.csv
 */
public class Main {
    public static void main(String args[]) {

        CSVloader loader = new CSVloader("products.csv");

        CSVmodifier mod = new CSVmodifier(loader.getTable("\"", ";"));

        mod.makeTransformations();

        CSVwriter writer = new CSVwriter("result.csv");

        if (writer.writeCSV(mod.getHeaders(), mod.getData(), "\'", "|", "ISO-8859-1"))
            System.out.println("File " + writer.getFileName() + " generated correctly.");;

    }


}
