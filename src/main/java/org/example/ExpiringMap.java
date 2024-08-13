package org.example;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ExpiringMap<K, V> {
        private final Map<K, TimedEntry<V>> map = new ConcurrentHashMap<>();
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        public ExpiringMap(  long checkInterval, TimeUnit checkIntervalUnit) {

            // 定期检查过期条目
            scheduler.scheduleAtFixedRate(this::cleanupExpiredEntries, checkInterval, checkInterval, checkIntervalUnit);
        }

        public void put(K key, V value,long expirationTime) {
            map.compute(key, (k, entry) -> {
                if (entry == null) {
                    return new TimedEntry<>(value,expirationTime);
                } else {
                    entry.setValue(value); //更新值，但不改变过期时间
                    return entry;
                }
            });
        }
        public <T> Set<K> findKeys(Function<K, T> fieldExtractor, T valueToMatch) {
            Set<K> matchingKeys = new HashSet<>();
            for (Map.Entry<K, TimedEntry<V>> entry : map.entrySet()) {
                if (Objects.equals(fieldExtractor.apply(entry.getKey()), valueToMatch)) {
                    matchingKeys.add(entry.getKey());
                }
            }
            return matchingKeys;
        }
        public void put(K key, V value, long expirationTime, TimeUnit timeUnit) {
            map.compute(key, (k, entry) -> {
                if (entry == null) {
                    return  new TimedEntry<>(value, expirationTime, timeUnit);
                } else {
                    entry.setValue(value); //更新值，但不改变过期时间
                    return entry;
                }
            });
            // 使用自定义过期时间
        }

        public V get(K key) {
            TimedEntry<V> entry = map.get(key);
            if (entry == null ) {
                map.remove(key);
                return null;
            } else if (entry.isExpired()){
                storeInDatabase(key, entry.getValue());
                map.remove(key);
                return null;
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
            private V value;
            private final long creationTime; // 记录条目创建时间
            private final long expirationMillis;

            public TimedEntry(V value, long expirationTime) {
                this(value, expirationTime, TimeUnit.MILLISECONDS); // 使用默认时间单位
            }

            public TimedEntry(V value, long expirationTime, TimeUnit timeUnit) {
                this.value = value;
                this.creationTime = System.currentTimeMillis();
                this.expirationMillis = timeUnit.toMillis(expirationTime); // 计算过期时间
            }

            public V getValue() {
                return value;
            }

            public void setValue(V value) {
                this.value = value;
            }

            public boolean isExpired() {
                System.out.println("isExpired");
                return System.currentTimeMillis() - creationTime > expirationMillis;
            }

        }
}