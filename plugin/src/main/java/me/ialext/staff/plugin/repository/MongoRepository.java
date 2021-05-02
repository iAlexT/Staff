package me.ialext.staff.plugin.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.ialext.staff.api.database.Repository;
import me.ialext.staff.api.database.annotation.MongoCollectionKey;
import me.ialext.staff.api.model.SavableModel;
import me.yushust.inject.key.TypeReference;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepository<O extends SavableModel> implements Repository<O> {

  private final MongoCollection<O> mongoCollection;
  private final Class<O> clazz;

  @Inject private MongoRepository(
      MongoDatabase mongoDatabase,
      TypeReference<O> typeReference
  ) {
    String collectionKey = typeReference
        .getRawType()
        .isAnnotationPresent(
            MongoCollectionKey.class
        )
        ? typeReference
        .getRawType()
        .getAnnotation(
            MongoCollectionKey.class
        ).value()
        : typeReference
            .getRawType()
            .getSimpleName();
    this.mongoCollection = mongoDatabase.getCollection(
        collectionKey,
        typeReference.getRawType()
    );
    this.clazz = typeReference.getRawType();
  }

  @Override public Optional<O> findOneSync(String id) {
    return Optional.ofNullable(
        mongoCollection.find(
            eq(
                "_id",
                id
            )
        )
        .first()
    );
  }

  @Override public Set<O> findAllSync() {
    return mongoCollection
        .find()
        .into(
            new HashSet<>()
        );
  }

  @Override public void deleteSync(String id) {
    mongoCollection.deleteOne(
        eq(
            "_id",
            id
        )
    );
  }

  @Override public void replaceSync(
      String id,
      O o
  ) {
    mongoCollection.replaceOne(
        eq(
            "_id",
            id
        ),
        o
    );
  }

  @Override public void saveSync(O o) {
    mongoCollection.insertOne(o);
  }
}
