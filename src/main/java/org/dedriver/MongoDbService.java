package org.dedriver;

import de.swingbe.ifleet.model.Entity;

/**
 * Inserts a {@link Entity} into the Dede MongoDb using Dede Protocol (ddp)
 */
public interface MongoDbService {

    /**
     * Inserts a IVU Location Message from an {@link Entity} into the Dede MongoDb using Dede Protocol (ddp)
     *
     * @param entity the IVU Location Message as {@link Entity} to be inserted
     */
    void insertMsgIvuLct(Entity entity);

    /**
     * Inserts a Dede On-board Unit Message from an {@link Entity} into the Dede MongoDb using Dede Protocol (ddp)
     *
     * @param entity the Dede On-bard Unit Message as {@link Entity} to be inserted
     */
    void insertMsgDedeObu(Entity entity);
}
