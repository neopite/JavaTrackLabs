package lab1.com.company.neophite.io;

import java.io.FileWriter;
import java.io.IOException;

public class WriterFile {

    private final String fileName;

    public WriterFile(String name) {
        fileName = name;
    }

    public void writeIntoFile(StringBuilder str){
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write(str.toString());
        } catch (IOException ignored) {

        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("FileWriter cannot be closed");
            }
        }
    }
}
