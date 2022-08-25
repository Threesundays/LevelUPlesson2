package Home.task.concurrency;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Main2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        ArrayList<Double> doubles = calculateAsync(2, 10);
        String path = "C:\\Users\\Three\\IdeaProjects\\LevelUPlesson2\\src\\save1.txt";
        FileWriter fileWriter = new FileWriter(path, StandardCharsets.UTF_8);
        for (Double aDouble : doubles) {
            fileWriter.write(aDouble + "\n");
            fileWriter.flush();
        }
        doubles.forEach(System.out::println);


    }

    public static ArrayList<Double> calculateAsync (int threads, int count) throws ExecutionException, InterruptedException {
        ArrayList<Future<Double>> futures = new ArrayList<>();
        ArrayList<Double> result = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            Future future = service.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    return Math.sqrt(finalI);
                }

            });

            futures.add(future);
        }
        for (Future<Double> future : futures) {
            Double aDouble = future.get();
            result.add(aDouble);
        }
        service.shutdown();
        return result;
    }


}
