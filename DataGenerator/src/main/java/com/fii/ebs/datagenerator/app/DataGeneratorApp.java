package com.fii.ebs.datagenerator.app;

import static com.fii.ebs.datagenerator.request.DataRequest.DataRequestType.PUBLICATION;
import static com.fii.ebs.datagenerator.request.DataRequest.DataRequestType.SUBSCRIPTION;

import com.fii.ebs.datagenerator.generators.PublicationDataGenerator;
import com.fii.ebs.datagenerator.request.DataRequest.DataRequestType;
import com.fii.ebs.datagenerator.request.PublicationDataRequest;
import com.fii.ebs.datagenerator.request.SubscriptionDataRequest;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataGeneratorApp {
    public static void main(String[] args) {
        log.info("Starting the Data Generator.");
        
        if (args == null || args.length == 0 || args.length % 2 != 0) {
            throw new IllegalArgumentException( "Invalid input parameters were given for the Data Generator!");
        }
        
        Properties appInputParams = new Properties();
        for (int i = 0; i < args.length; i += 2) {
            appInputParams.setProperty(args[i], args[i + 1]);
        }
        
        DataRequestType dataRequestType = null;
        try {
            dataRequestType = DataRequestType.valueOf(appInputParams.getProperty("--dataRequestType").toUpperCase());
        } catch(Exception e) {
            throw new IllegalArgumentException( "Invalid data request type!", e);
        }
        
        int parallelizationFactor = Integer.parseInt(appInputParams.getProperty("--parallelizationFactor"));
        if (parallelizationFactor <= 0 || parallelizationFactor > 50) {
            throw new IllegalArgumentException( "Invalid parallelization factor!");
        }
        
        int totalNumberOfRecords = Integer.parseInt(appInputParams.getProperty("--totalNumberOfRecords"));
        if (totalNumberOfRecords <= 0 || totalNumberOfRecords > 1000000) {
            throw new IllegalArgumentException( "Invalid total number of records!");
        }
        
        try {
            switch (dataRequestType) {
                case PUBLICATION:
                    startPublicationGenerator(dataRequestType, parallelizationFactor, totalNumberOfRecords);
                    break;
                case SUBSCRIPTION:
                    startSubscriptionGenerator(dataRequestType, parallelizationFactor, totalNumberOfRecords, appInputParams);
                    break;
                default:
                    throw new IllegalArgumentException( "Invalid data request type!");
            }
        } catch(Exception e) {
            log.error("The Data Generator failed to process the data request!", e);
        }
    }
    
    private static void startPublicationGenerator(DataRequestType dataRequestType, int parallelizationFactor,
            int totalNumberOfRecords) throws Exception {
        PublicationDataRequest dataRequest = PublicationDataRequest.builder()
            .dataRequestType(dataRequestType)
            .parallelizationFactor(parallelizationFactor)
            .totalNumberOfRecords(totalNumberOfRecords)
            .build();
        
        PublicationDataGenerator generator = new PublicationDataGenerator();
        generator.process(dataRequest);
    }
    
    private static void startSubscriptionGenerator(DataRequestType dataRequestType, int parallelizationFactor,
            int totalNumberOfRecords, Properties appInputParams) throws Exception {
        String requestFilePath = appInputParams.getProperty("--requestFilePath");
        if (requestFilePath == null || requestFilePath.isEmpty()) {
            throw new IllegalArgumentException( "Invalid request file path!");
        }
        
        SubscriptionDataRequest dataRequest = SubscriptionDataRequest.builder()
            .dataRequestType(dataRequestType)
            .parallelizationFactor(parallelizationFactor)
            .totalNumberOfRecords(totalNumberOfRecords)
            .requestFilePath(requestFilePath)
            .build();
        
        // TODO: add call to SubscriptionDataGenerator.process();
    }
}