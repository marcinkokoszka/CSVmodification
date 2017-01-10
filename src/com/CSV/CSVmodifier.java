package com.CSV;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * modifies the data loaded from csv file
 */
public class CSVmodifier {

    private String[] headers;
    private String[][] data;

    public CSVmodifier(String[][] input) {
        headers = input[0].clone();
        data = new String[input.length - 1][];
        for (int i = 0; i < input.length - 1; i++){
            data[i] = new String[input[0].length];
            System.arraycopy(input[i+1], 0, data[i], 0, input[i+1].length);
        }
    }

    public String[][] getData() {
        return data;
    }

    public String[] getHeaders() {
        return headers;
    }

    private void changeHeaders(String... strings){
        headers = strings.clone();
    }

    public void makeTransformations(){
        changeHeaders("name", "offerurl", "price", "published", "description");
        removeQuoteCharacters("\"");
        makeOfferurl();
        makePrice();
        makePublished();
    }

    private void removeQuoteCharacters(String quoteCharacter) {
        for (String[] s: data){
            for (int i = 0; i < data[0].length; i++){
                if (s[i] != null) s[i] = s[i].replace(quoteCharacter, "");
                else s[i] = "";
            }
        }
    }

    private void makeOfferurl(){
        for (String[] s: data){
            s[1] = s[1] + "?id=" + s[2];
        }
    }

    private void makePublished() {
        for (String[] s: data){
            s[3] = convertDate(getDate(s[4], false), false);
            if (s[3].equals("")) {
                s[3] = convertDate(getDate(s[4], true), true);
            }
        }
    }

    private String convertDate(String date, boolean isAmerican) {
        if (date.length() > 0) {
            String[] arr = date.split("\\.");
            if (arr[0].length() < 2) {
                arr[0] = "0" + arr[0];
            }
            if (arr[1].length() < 2) {
                arr[1] = "0" + arr[1];
            }
            if (isAmerican){
                return arr[1] + "." + arr[0] + "." + arr[2];
            } else {
                return arr[0] + "." + arr[1] + "." + arr[2];
            }
        } else {
            return "";
        }
    }

    private String getDate(String str, boolean isAmerican){
        String pattern;
        if (!isAmerican) pattern = "([1-9]|0[1-9]|[12][0-9]|3[01])[- /.]([1-9]|1[012]|0[1-9]|1[012])[- /.](19|20)\\d\\d";
        else pattern = "(0[1-9]|1[012])[- /.]([1-9]|0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d";
        Matcher m = Pattern.compile(pattern).matcher(str);
        if (m.find()) {
            str = m.group().replaceAll("/", "\\.").replaceAll("-", "\\.");
        } else {
            str = "";
        }
        return str;
    }

    private void makePrice() {
        for (String[] s: data) {
            s[2] = s[3].replaceAll("[^0-9.,]+", ""); // delete all but digits, dots and commas
            s[2] = s[2].replaceAll(",", "."); //replace commas with dots

            // count number of dot appearances:
            int counter = 0;
            for (int i = 0; i < s[2].length(); i++) {
                if (s[2].charAt(i) == '.') {
                    counter++;
                }
            }
            // delete all but the last dot:
            while (counter > 1) {
                s[2] = s[2].replaceFirst("\\.", "");
                counter -= counter;
            }

            s[2] = String.format(Locale.US, "%.2f", Double.parseDouble(s[2]));
        }
    }

}
