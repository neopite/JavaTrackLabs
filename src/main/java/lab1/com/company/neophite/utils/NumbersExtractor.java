package lab1.com.company.neophite.utils;

import java.util.ArrayList;

public class NumbersExtractor {

    public static String findNumbersInWholeFile(String[] arrayOfRows) {
        int countOfWords = 0 ;
        for (String arrayOfRow : arrayOfRows) {
            countOfWords+=findNumbersInRow(arrayOfRow).size();
        }
        return Integer.toString(countOfWords);
    }


    public static ArrayList<String> findNumbersInRow(String row) {
        String[] arrayOfWords = row.split(" ");

        ArrayList<String> arrayOfNumbers = new ArrayList<>();
        for (int itter = 0; itter < arrayOfWords.length; itter++) {
            String word = arrayOfWords[itter];
            if (Utils.isNumber(word)) {
                arrayOfNumbers.add(word);
            }
        }
        return arrayOfNumbers;
    }


}
