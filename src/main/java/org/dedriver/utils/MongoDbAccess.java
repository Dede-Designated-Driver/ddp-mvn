package org.dedriver.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbAccess {

    private final MongoDatabase db;
    private final MongoCollection collection;
    private final MongoClient client;

    public MongoCollection getCollection() {
        return collection;
    }

    public MongoDbAccess(String Uri, String database, String collectionName) {
        this.client = new MongoClient(new MongoClientURI(Uri));
        this.db = client.getDatabase(database);
        this.collection = db.getCollection(collectionName);
    }
}
