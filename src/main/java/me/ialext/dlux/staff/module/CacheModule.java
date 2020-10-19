package me.ialext.dlux.staff.module;

import me.ialext.dlux.staff.Cache;
import me.ialext.dlux.staff.cache.FreezeCache;
import me.ialext.dlux.staff.cache.StaffCache;
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

        bind(Key.of(new TypeReference<Cache<UUID, ItemStack[]>>() {
            }, Names.named("staff"))).to(StaffCache.class).singleton();
    }
}
