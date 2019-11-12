package com.mvtlabs.api.core.util;

import java.util.UUID;

public class UUIDUtil {
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
