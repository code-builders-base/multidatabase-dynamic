package com.pifrans.multidatabasedynamic.configurations.datasource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataSourceContext {
    @Value("${datasource.dynamic.default.name}")
    public static String DEFAULT_DATABASE;
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
