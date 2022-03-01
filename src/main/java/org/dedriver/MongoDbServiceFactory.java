package org.dedriver;

/**
 * Factory for creating instances of {@link MongoDbService}.
 */
public class MongoDbServiceFactory {

    /**
     * Creates an instance of {@link MongoDbService}
     *
     * @return the new instance
     */
    public static MongoDbService createMongo() {
        return new MongoDbServiceImpl();
    }
}
