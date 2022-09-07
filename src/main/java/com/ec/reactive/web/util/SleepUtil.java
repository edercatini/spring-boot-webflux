package com.ec.reactive.web.util;

import lombok.SneakyThrows;

public class SleepUtil {

    @SneakyThrows
    public static void sleepSeconds(Integer seconds) {
        Thread.sleep(seconds * 1000);
    }
}