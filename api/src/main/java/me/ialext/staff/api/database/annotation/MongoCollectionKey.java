package me.ialext.staff.api.database.annotation;

import com.mongodb.client.MongoCollection;
import me.ialext.staff.api.model.SavableModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the annotated
 * {@link SavableModel}'s {@link MongoCollection}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MongoCollectionKey {
}
