package lab1.com.company.neophite.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    private final String fileName ;

    public FileReader(String name){
        fileName = name;
    }

    public String[] readFile(){
        ArrayList<String> fileInArray = new ArrayList<>();
        try(Scanner scanner = new Scanner(new FileInputStream(fileName))){
            while(scanner.hasNextLine()){
                fileInArray.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return fileInArray.toArray(new String[]{});
    }

}
