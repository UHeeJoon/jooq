/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.daos;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.generated.tables.JActor;
import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.records.ActorRecord;
import org.jooq.impl.AutoConverter;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.19.5",
        "schema version:1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActorDao extends DAOImpl<ActorRecord, Actor, Long> {

    /**
     * Create a new ActorDao without any configuration
     */
    public ActorDao() {
        super(JActor.ACTOR, Actor.class);
    }

    /**
     * Create a new ActorDao with an attached configuration
     */
    public ActorDao(Configuration configuration) {
        super(JActor.ACTOR, Actor.class, configuration);
    }

    @Override
    public Long getId(Actor object) {
        return object.getActorId();
    }

    /**
     * Fetch records that have <code>actor_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Actor> fetchRangeOfJActorId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(JActor.ACTOR.ACTOR_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>actor_id IN (values)</code>
     */
    public List<Actor> fetchByJActorId(Long... values) {
        return fetch(JActor.ACTOR.ACTOR_ID, values);
    }

    /**
     * Fetch a unique record that has <code>actor_id = value</code>
     */
    public Actor fetchOneByJActorId(Long value) {
        return fetchOne(JActor.ACTOR.ACTOR_ID, value);
    }

    /**
     * Fetch a unique record that has <code>actor_id = value</code>
     */
    public Optional<Actor> fetchOptionalByJActorId(Long value) {
        return fetchOptional(JActor.ACTOR.ACTOR_ID, value);
    }

    /**
     * Fetch records that have <code>first_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Actor> fetchRangeOfJFirstName(String lowerInclusive, String upperInclusive) {
        return fetchRange(JActor.ACTOR.FIRST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<Actor> fetchByJFirstName(String... values) {
        return fetch(JActor.ACTOR.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Actor> fetchRangeOfJLastName(String lowerInclusive, String upperInclusive) {
        return fetchRange(JActor.ACTOR.LAST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<Actor> fetchByJLastName(String... values) {
        return fetch(JActor.ACTOR.LAST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Actor> fetchRangeOfJLastUpdate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(JActor.ACTOR.LAST_UPDATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<Actor> fetchByJLastUpdate(LocalDateTime... values) {
        return fetch(JActor.ACTOR.LAST_UPDATE, values);
    }
}
