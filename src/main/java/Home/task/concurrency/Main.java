package Home.task.concurrency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {



    public static void main(String[] args) throws IOException, InterruptedException {
        String path = ("C:\\Users\\Three\\IdeaProjects\\LevelUPlesson2\\src\\save.txt");
        FileWriter fileWriter = new FileWriter(path);
        List<Double> resultList = Collections.synchronizedList(new ArrayList<>());

        int sliceSize = 10 / 2;
        for (int threedNumber = 0; threedNumber < 2; threedNumber++) {
            int start = sliceSize * threedNumber;
            int end = start + sliceSize;
            new Thread(() -> {
                for (int i = start; i < end; i++) {
                    double result = Math.sqrt(i);
                    resultList.add(result);
//                    System.out.println(result);
                }
//                System.out.println(resultList);
            }).start();
        }
        Thread.sleep(1);
        for (Double aDouble : resultList) {
            fileWriter.write(aDouble + "\n");
            fileWriter.flush();

        }
        System.out.println(resultList);
    }
}

