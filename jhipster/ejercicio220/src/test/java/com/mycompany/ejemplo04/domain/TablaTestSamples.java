package com.mycompany.ejemplo04.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TablaTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Tabla getTablaSample1() {
        return new Tabla()
            .id("id1")
            .campo01("campo011")
            .campo02(1)
            .campo03(1L)
            .campo13(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static Tabla getTablaSample2() {
        return new Tabla()
            .id("id2")
            .campo01("campo012")
            .campo02(2)
            .campo03(2L)
            .campo13(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static Tabla getTablaRandomSampleGenerator() {
        return new Tabla()
            .id(UUID.randomUUID().toString())
            .campo01(UUID.randomUUID().toString())
            .campo02(intCount.incrementAndGet())
            .campo03(longCount.incrementAndGet())
            .campo13(UUID.randomUUID());
    }
}
