package me.ialext.staff.plugin.cache;

import me.ialext.staff.api.cache.Cache;
import me.ialext.staff.api.database.Repository;
import me.ialext.staff.api.model.SavableModel;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CacheImpl<O extends SavableModel> implements Cache<O> {

  private final Map<String, O> cacheMap = new ConcurrentHashMap<>();
  private final Repository<O> repository;

  @Inject private CacheImpl(
      Repository<O> repository
  ) {
    this.repository = repository;
  }

  @Override public Optional<O> findOne(String id) {
    return Optional.ofNullable(
        cacheMap.get(id)
    );
  }

  @Override public Optional<O> findOrGet(String id) {
    return (findOne(id).isPresent())
        ? findOne(id)
        : repository.findOneSync(id);
  }

  @Override public Set<O> getAll() {
    return new HashSet<>(
        cacheMap.values()
    );
  }

  @Override public void cache(O o) {
    cacheMap.put(
        o.getId(),
        o
    );
  }

  @Override public void save(String id) {
    findOne(id).ifPresent(repository::saveOrReplace);
  }

  @Override public void saveAll() {
    cacheMap
        .values()
        .stream()
        .map(SavableModel::getId)
        .forEach(this::save);
  }

  @Override public void delete(String id) {
    cacheMap.remove(id);
  }

  @Override public void throwAway() {
    cacheMap.clear();
  }
}
