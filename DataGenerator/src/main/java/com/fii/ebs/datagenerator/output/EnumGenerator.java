package com.fii.ebs.datagenerator.output;

import java.security.SecureRandom;

public class EnumGenerator<E extends Enum<E>> {
    private final SecureRandom random;
    private final E[] enumValues;

    public EnumGenerator(SecureRandom random, Class<E> enumClass) {
        this.random = random;
        this.enumValues = enumClass.getEnumConstants();
    }

    public E generate() {
        return enumValues[random.nextInt(enumValues.length)];
    }
}