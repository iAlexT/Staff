package me.ialext.dlux.staff.cache;

import me.ialext.dlux.staff.CacheSet;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishCache implements CacheSet<UUID> {

    private final Set<UUID> vanish = new HashSet<>();

    @Override
    public Set<UUID> get() {
        return vanish;
    }
}
