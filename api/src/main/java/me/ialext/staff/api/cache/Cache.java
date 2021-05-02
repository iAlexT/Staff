package me.ialext.staff.api.cache;

import me.ialext.staff.api.model.SavableModel;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * A common cache and simple cache layer that is
 * thought for store {@link SavableModel}s, which
 * are models that can be stored into an {@link Repository}.
 *
 * @param <O> The model type to be cached.
 */
public interface Cache<O extends SavableModel> {

  /**
   * Searches for an {@link O} in the cache,
   * using its {@link SavableModel#getId()}.
   *
   * @param id The {@link O}'s id.
   * @return An optional {@link O} value,
   * result of the search.
   */
  Optional<O> findOne(String id);

  /**
   * Similar as {@link Cache#findOne(String)},
   * searches for an {@link O} in the cache,
   * using its {@link SavableModel#getId()},
   * but, if it isn't present, searches in the
   * {@link Repository}.
   *
   * @param id The {@link O}'s id.
   * @return An optional {@link O} value,
   * result of the cache/object repository search.
   */
  Optional<O> findOrGet(String id) throws ExecutionException, InterruptedException;

  /**
   * Get all cached {@link O}s by this {@link Cache}.
   *
   * @return All cached {@link O}s.
   */
  Set<O> getAll();

  /**
   * Caches an {@link O} into the cache.
   *
   * @param o The object to be cached.
   */
  void cache(O o);

  /**
   * Searches for an {@link O} into the
   * {@link Cache}'s, and saves it into
   * the {@link Repository} if present.
   *
   * @param id The {@link O}'s id.
   */
  void save(String id);

  /**
   * Saves all the cached {@link O}s by this {@link Cache}
   * into its {@link Repository}.
   */
  void saveAll();

  /**
   * Deletes an {@link O} from this {@link Cache}.
   *
   * @param id The {@link O}'s id.
   */
  void delete(String id);

  /**
   * Throws away all the
   * cached {@link O}s.
   */
  void throwAway();

}