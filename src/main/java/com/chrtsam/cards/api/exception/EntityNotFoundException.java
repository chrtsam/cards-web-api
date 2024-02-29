package com.chrtsam.cards.api.exception;

/**
 *
 * @author Chris
 */
public class EntityNotFoundException extends RuntimeException {

    private Integer entityId;

    public EntityNotFoundException(Integer entityId) {
        super("Entity with id " + entityId + " not found");
        this.entityId = entityId;
    }

    public Integer getEntityId() {
        return entityId;
    }

}
