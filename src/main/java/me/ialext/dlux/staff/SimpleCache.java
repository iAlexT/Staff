package me.ialext.dlux.staff;

import java.util.Set;

public interface SimpleCache<E> {

    Set<E> get();

    default void add(E element) {
        get().add(element);
    }

    default E find(E element) {
        while((get().iterator().hasNext()) && (get().iterator().next() != element)) {
            get().iterator().next();
        }

        return element;
    }
    default void remove(E element) {
        get().remove(element);
    }

    default boolean exists(E element) {
        return get().contains(element);
    }
}
