package com.fii.ebs.datagenerator.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
public class SubscriptionDataRequest extends DataRequest {
    protected String requestFilePath;
    // TODO: add subscription fields configuration and weight factors
}