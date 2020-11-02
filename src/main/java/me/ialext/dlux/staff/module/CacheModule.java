package me.ialext.dlux.staff.module;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.SimpleCache;
import me.ialext.dlux.staff.cache.*;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.bind.AbstractModule;
import team.unnamed.inject.identity.Key;
import team.unnamed.inject.identity.type.TypeReference;
import team.unnamed.inject.name.Names;

import java.util.UUID;

public class CacheModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Key.of(new TypeReference<Cache<UUID, UUID>>() {
            }, Names.named("freeze"))).to(FreezeCache.class).singleton();

        bind(Key.of(new TypeReference<SimpleCache<UUID>>() {
            }, Names.named("staff"))).to(StaffCache.class).singleton();

        bind(Key.of(new TypeReference<SimpleCache<UUID>>() {
            }, Names.named("vanish"))).to(VanishCache.class).singleton();

        bind(Key.of(new TypeReference<Cache<UUID, ItemStack[]>>() {
            }, Names.named("items"))).to(ItemsCache.class).singleton();

        bind(Key.of(new TypeReference<SimpleCache<UUID>>() {
            }, Names.named("staff-chat"))).to(StaffChatCache.class).singleton();
    }
}
