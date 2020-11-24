package lab1.com.company.neophite.threads;

import lab1.com.company.neophite.io.FileReader;
import lab1.com.company.neophite.utils.NumbersExtractor;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;

public class FileFinderThread implements Callable<String> {

    private final String dir;
    private final ExecutorService executorService ;

    public FileFinderThread(String dirName,ExecutorService executorService) {
        this.dir = dirName;
        this.executorService = executorService;
    }

    @Override
    public String call() throws Exception {
        StringBuilder str = new StringBuilder();
        try {
            File[] files = new File(dir).listFiles();
            ArrayList<FutureTask<String>> arrayList = new ArrayList<>();
            for(File f : files){
                if(f.isDirectory()){
                    FileFinderThread fileFinderThread = new FileFinderThread(dir+"\\"+f.getName(),executorService);
                    FutureTask<String> task = new FutureTask<>(fileFinderThread);
                    arrayList.add(task);
                    executorService.submit(task);
                }else if(f.isFile() && f.getName().contains(".txt")) {
                    FileReader fileReader = new FileReader(dir+"\\"+f.getName());
                    str.append("File name : " + f.getName() + " . Count of numbers : " + NumbersExtractor.findNumbersInWholeFile(fileReader.readFile()) + '\n' );
                }
            }
            for (Future<String> arr : arrayList){
                str.append(arr.get());
            }

        }catch (ExecutionException executionException){
            System.out.println("Error with execution service ");
        }
        return str.toString();
    }


}