package me.ialext.staff.plugin.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import me.ialext.staff.api.config.ConfigurationWrapper;

import javax.inject.Inject;
import javax.inject.Provider;

public class MongoDatabaseProvider implements Provider<MongoDatabase> {

  @Inject private MongoClient mongoClient;
  @Inject private ConfigurationWrapper config;

  @Override public MongoDatabase get() {
    return mongoClient.getDatabase(
        config.getString(
            "mongo.credentials.database",
            "Staff"
        )
    );
  }
}
