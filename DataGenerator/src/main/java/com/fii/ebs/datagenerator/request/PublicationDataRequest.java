package com.fii.ebs.datagenerator.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
public class PublicationDataRequest extends DataRequest {
    // TODO: add publication fields configuration
}