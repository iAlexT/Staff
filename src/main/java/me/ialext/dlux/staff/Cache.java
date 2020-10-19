package me.ialext.dlux.staff;

import java.util.Map;
import java.util.Optional;

public interface Cache<K, V> {
    Map<K, V> get();

    default Optional<V> find(K key) {
        return Optional.ofNullable(get().get(key));
    }

    default void add(K key, V value) {
        get().put(key, value);
    }

    default void remove(K key) {
        get().remove(key);
    }
    default boolean exists(K key) {
        return get().containsKey(key);
    }

}
