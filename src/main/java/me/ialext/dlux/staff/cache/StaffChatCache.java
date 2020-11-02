package me.ialext.dlux.staff.cache;

import me.ialext.dlux.staff.SimpleCache;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffChatCache implements SimpleCache<UUID> {

    private final Set<UUID> staffChat = new HashSet<>();

    @Override
    public Set<UUID> get() {
        return staffChat;
    }
}
