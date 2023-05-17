import java.nio.file.*;
import java.util.List;
import java.util.concurrent.*;

public class WordCount {


    public static Callable<Integer> countWords(String fileName){
        return () ->{
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            int count = 0;
            for(String line: lines) {
                if(!line.isEmpty()) {
                    count++;
                }
            }
            return count;
        };
    }


    public static void main(String[] args) throws Exception{
        String[] fileNames = {"hello.txt", "cool.txt", "dank.txt"};
        ExecutorService service = Executors.newCachedThreadPool();
        Callable<Integer> task;
        for(String fileName: fileNames){
            task = countWords(fileName);
            Future<Integer> resultSolo = service.submit(task);
            System.out.println(fileName + " has " + resultSolo.get());
        }



        service.shutdown();




    }
}
