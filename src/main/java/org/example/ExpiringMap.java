package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpiringMap<K, V> {
    private final Map<K, TimedEntry<V>> map = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final long expirationTime; // 每个条目的有效时间
    private final TimeUnit timeUnit;   // 时间单位

    public ExpiringMap(long expirationTime, TimeUnit timeUnit, long checkInterval, TimeUnit checkIntervalUnit) {
        this.expirationTime = expirationTime;
        this.timeUnit = timeUnit;
        // 定期检查过期条目
        scheduler.scheduleAtFixedRate(this::cleanupExpiredEntries, checkInterval, checkInterval, checkIntervalUnit);
    }

    public void put(K key, V value) {
        map.compute(key, (k, entry) -> {
            if (entry == null) {
                return new TimedEntry<>(value);
            } else {
                entry.setValue(value); //更新值，但不改变过期时间
                return entry;
            }
        });
    }

    public V get(K key) {
        TimedEntry<V> entry = map.get(key);
        if (entry == null ) {
            map.remove(key);
            return null;
        } else if (entry.isExpired()){
            storeInDatabase(key, entry.getValue());
            map.remove(key);
        }
        return entry.getValue();
    }

    private void cleanupExpiredEntries() {
        for (Map.Entry<K, TimedEntry<V>> entry : map.entrySet()) {
            if (entry.getValue().isExpired()) {
                // 将过期条目存储到数据库
                storeInDatabase(entry.getKey(), entry.getValue().getValue());
                // 移除已过期条目
                map.remove(entry.getKey());
            }

        }
    }

    private void storeInDatabase(K key, V value) {
        // 实现存储逻辑，例如使用 JDBC 将数据存储到数据库中
        System.out.println("Storing in database: " + key + " -> " + value);
    }

    private class TimedEntry<V> {
        private  V value;
        private final long creationTime; // 记录条目创建时间
        private final long expirationMillis;

        public TimedEntry(V value) {
            this.value = value;
            this.creationTime = System.currentTimeMillis();
            this.expirationMillis = timeUnit.toMillis(expirationTime); // 计算过期时间
        }

        public V getValue() {
            return value;
        }
        public void setValue(V value){
            this.value=value;
        }

        public boolean isExpired() {
            System.out.println("isExpired");
            return System.currentTimeMillis() - creationTime > expirationMillis;
        }
    }

//    public void shutdown() {
//        scheduler.shutdown();
//        try {
//            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
//                scheduler.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            scheduler.shutdownNow();
//            Thread.currentThread().interrupt();
//        }
//    }
}