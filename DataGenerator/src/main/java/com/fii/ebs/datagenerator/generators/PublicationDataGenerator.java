package com.fii.ebs.datagenerator.generators;

import com.fii.ebs.datagenerator.output.PublicationRecord;
import com.fii.ebs.datagenerator.request.PublicationDataRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class PublicationDataGenerator {
    public void process(PublicationDataRequest request) throws Exception {
        Instant startTime = Instant.now();
        File file = new File("./DataGenerated/");
        file.mkdirs();
        File outputFile = new File("./DataGenerated/publications.txt");
        if (outputFile.exists()) {
            outputFile.delete();
        }
        
        int parallelizationFactor = request.getParallelizationFactor();
        int totalNumberOfRecords = request.getTotalNumberOfRecords();
        int minRecordsPerTask = totalNumberOfRecords / parallelizationFactor;
        List<PublicationDataGeneratorTask> tasks = new ArrayList<>();
        PublicationDataGeneratorTask task;
        for (int i = 0; i < parallelizationFactor; i++) {
            if (i == (parallelizationFactor - 1)) {
                task = new PublicationDataGeneratorTask(minRecordsPerTask + (totalNumberOfRecords % parallelizationFactor));
            } else {
                task = new PublicationDataGeneratorTask(minRecordsPerTask);
            }
            tasks.add(task);
        }
        
        ExecutorService pool = Executors.newFixedThreadPool(tasks.size());
        
        try {
            List<Future<PublicationDataGeneratorTaskResult>> futureTasksResults = pool.invokeAll(tasks);
            for (Future<PublicationDataGeneratorTaskResult> futureTaskResult: futureTasksResults) {
                PublicationDataGeneratorTaskResult taskResult = futureTaskResult.get();
                if (taskResult.getStatus()) {
                    log.info("Task = {} completed successfully!", taskResult.getTaskId());
                } else {
                    log.error("Task = {} failed!", taskResult.getTaskId());
                }
            }
        } catch (Exception e) {
            log.error("PublicationDataGenerator failed to output the publication records.", e);
            throw e;
        } finally {
            pool.shutdown();
        }
        
        Instant endTime = Instant.now();
        Duration executionTime = Duration.between(startTime, endTime);
        log.info("PublicationDataGenerator completed with execution time = {}", executionTime);
    }
    
    @RequiredArgsConstructor
    @Slf4j
    public static class PublicationDataGeneratorTask implements Callable<PublicationDataGeneratorTaskResult> {
        private final int numberOfRecords;
        
        @Override
        public PublicationDataGeneratorTaskResult call() throws Exception {
            Instant startTime = Instant.now();
            String threadName = Thread.currentThread().getName();
            log.info("PublicationDataGeneratorTask handled in thread = {} for numberOfRecords = {}",
                threadName, numberOfRecords);
            
            File outputFile = new File("./DataGenerated/publications.txt");
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
                for (int i = 0; i < numberOfRecords; i++) {
                    writer.append(PublicationRecord.generateRecord().toString()).append("\n");
                    writer.flush();
                }
            } catch (Exception e) {
                log.error("Failed to output the publication records in thread = {}", threadName, e);
                return new PublicationDataGeneratorTaskResult(threadName, false);
            }
                                
            Instant endTime = Instant.now();
            Duration executionTime = Duration.between(startTime, endTime);
            log.info("PublicationDataGeneratorTask completed in thread = {} with execution time = {}",
                    threadName, executionTime);
            
            return new PublicationDataGeneratorTaskResult(threadName, true);
        }
    }
    
    @AllArgsConstructor
    @Getter
    public static class PublicationDataGeneratorTaskResult {
        private final String taskId;
        private final Boolean status;
    }
}