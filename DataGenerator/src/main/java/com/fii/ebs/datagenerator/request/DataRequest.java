package com.fii.ebs.datagenerator.request;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class DataRequest {
    protected DataRequestType dataRequestType;
    protected Integer parallelizationFactor;
    protected Integer totalNumberOfRecords;
    
    public static enum DataRequestType {
        PUBLICATION,
        SUBSCRIPTION
    }
}