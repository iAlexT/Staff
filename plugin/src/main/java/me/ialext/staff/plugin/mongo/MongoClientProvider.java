package me.ialext.staff.plugin.mongo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoDriverInformation;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import me.ialext.staff.api.config.ConfigurationWrapper;
import me.ialext.staff.plugin.gsoncodec.GsonCodecProvider;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import javax.inject.Inject;
import javax.inject.Provider;

public class MongoClientProvider implements Provider<MongoClient> {

  @Inject private ConfigurationWrapper config;

  @Override public MongoClient get() {
    CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
        CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry()
        ),
        CodecRegistries.fromProviders(
            new GsonCodecProvider(
                new Gson(),
                new BsonDocumentCodec()
            )
        )
    );
    MongoClientSettings mongoClientSettings = MongoClientSettings
        .builder()
        .applyConnectionString(
            new ConnectionString(
                config.getString("mongo.credentials.uri")
            )
        )
        .codecRegistry(codecRegistry)
        .credential(
            MongoCredential.createCredential(
                config.getString("mongo.credentials.username"),
                config.getString(
                    "mongo.credentials.database",
                    "Staff"
                ),
                config.getString("mongo.credentials.password").toCharArray()
            )
        )
        .build();
    MongoDriverInformation mongoDriverInformation = MongoDriverInformation
        .builder()
        .driverName("sync")
        .driverVersion("4.2.2")
        .build();

    return MongoClients.create(
        mongoClientSettings,
        mongoDriverInformation
    );
  }
}
