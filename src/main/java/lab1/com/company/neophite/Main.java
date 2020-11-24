package lab1.com.company.neophite;


import lab1.com.company.neophite.io.FileReader;
import lab1.com.company.neophite.threads.FileFinderThread;
import lab1.com.company.neophite.io.WriterFile;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {

    private static final String ROOT  = "D:\\";
    private static final String FILE_TO_WRITE_RESULT = ROOT+"file";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file ;
        while (true) {
            System.out.print("Enter the directory : ");
            String dir =ROOT + scanner.nextLine();
            file = new File(dir);
            if (file.exists()) {
                new WriterFile(FILE_TO_WRITE_RESULT).writeIntoFile(getResultOfFinding(dir ,Executors.newFixedThreadPool(10)));
                scanner.close();
                break;
            }
        }
        Arrays.stream(new FileReader(FILE_TO_WRITE_RESULT).readFile()).forEach(System.out::println);
    }

    public static StringBuilder getResultOfFinding(String startDirName , ExecutorService executorService) {
        FileFinderThread fileFinderThread = new FileFinderThread(startDirName, executorService);
        FutureTask<String> arrayListFutureTask = new FutureTask<>(fileFinderThread);
        executorService.submit(arrayListFutureTask);
        StringBuilder str = null;
        try {
            str= new StringBuilder(arrayListFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Thread was interrupted");
        } finally {
            executorService.shutdown();

        }
        return str;
    }

}


