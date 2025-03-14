/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.generated.tables.JLanguage;
import org.jooq.generated.tables.pojos.Language;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LanguageRecord extends UpdatableRecordImpl<LanguageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LANGUAGE.LANGUAGE_ID</code>.
     */
    public LanguageRecord setLanguageId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>LANGUAGE.LANGUAGE_ID</code>.
     */
    public Long getLanguageId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LANGUAGE.LAST_UPDATE</code>.
     */
    public LanguageRecord setLastUpdate(LocalDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>LANGUAGE.LAST_UPDATE</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>LANGUAGE.NAME</code>.
     */
    public LanguageRecord setName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>LANGUAGE.NAME</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LanguageRecord
     */
    public LanguageRecord() {
        super(JLanguage.LANGUAGE);
    }

    /**
     * Create a detached, initialised LanguageRecord
     */
    public LanguageRecord(Long languageId, LocalDateTime lastUpdate, String name) {
        super(JLanguage.LANGUAGE);

        setLanguageId(languageId);
        setLastUpdate(lastUpdate);
        setName(name);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised LanguageRecord
     */
    public LanguageRecord(Language value) {
        super(JLanguage.LANGUAGE);

        if (value != null) {
            setLanguageId(value.getLanguageId());
            setLastUpdate(value.getLastUpdate());
            setName(value.getName());
            resetChangedOnNotNull();
        }
    }
}
