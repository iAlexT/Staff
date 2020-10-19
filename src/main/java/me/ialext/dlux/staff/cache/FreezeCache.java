package me.ialext.dlux.staff.cache;

import me.ialext.dlux.staff.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FreezeCache implements Cache<UUID, UUID> {

    private final Map<UUID, UUID> frozen = new HashMap<>();

    @Override
    public Map<UUID, UUID> get() {
        return frozen;
    }
}
