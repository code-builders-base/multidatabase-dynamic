package com.pifrans.multidatabasedynamic.configurations.datasource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataSourceContext {
    public static final String DEFAULT_DATABASE = "data_01";
    private static final ThreadLocal<String> currentDatabase = ThreadLocal.withInitial(() -> DEFAULT_DATABASE);


    public static void set(String dataSource) {
        currentDatabase.set(dataSource);
    }

    public static String get() {
        return currentDatabase.get();
    }

    public static void clear() {
        currentDatabase.remove();
    }
}
