package org.example.logic;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong counter = new AtomicLong();

    public static int getId() {
        return (int) counter.incrementAndGet();
    }
}
