package me.ialext.dlux.staff.module;

import me.ialext.dlux.staff.CacheMap;
import me.ialext.dlux.staff.CacheSet;
import me.ialext.dlux.staff.cache.FreezeCache;
import me.ialext.dlux.staff.cache.StaffCache;
import me.ialext.dlux.staff.cache.VanishCache;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.bind.AbstractModule;
import team.unnamed.inject.identity.Key;
import team.unnamed.inject.identity.type.TypeReference;
import team.unnamed.inject.name.Names;

import java.util.UUID;

public class CacheModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Key.of(new TypeReference<CacheMap<UUID, UUID>>() {
            }, Names.named("freeze"))).to(FreezeCache.class).singleton();

        bind(Key.of(new TypeReference<CacheMap<UUID, ItemStack[]>>() {
            }, Names.named("staff"))).to(StaffCache.class).singleton();

        bind(Key.of(new TypeReference<CacheSet<UUID>>() {
            }, Names.named("vanish"))).to(VanishCache.class).singleton();
    }
}
