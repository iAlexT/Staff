package me.ialext.dlux.staff;

import java.util.Set;

public interface CacheSet<E> {

    Set<E> get();

    default void add(E element) {
        get().add(element);
    }

    default void remove(E element) {
        get().remove(element);
    }

    default boolean exists(E element) {
        return get().contains(element);
    }
}
