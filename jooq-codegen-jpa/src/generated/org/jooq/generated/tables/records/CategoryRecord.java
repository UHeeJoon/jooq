/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.generated.tables.JCategory;
import org.jooq.generated.tables.pojos.Category;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CategoryRecord extends UpdatableRecordImpl<CategoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>CATEGORY.CATEGORY_ID</code>.
     */
    public CategoryRecord setCategoryId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>CATEGORY.CATEGORY_ID</code>.
     */
    public Long getCategoryId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>CATEGORY.LAST_UPDATE</code>.
     */
    public CategoryRecord setLastUpdate(LocalDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>CATEGORY.LAST_UPDATE</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>CATEGORY.NAME</code>.
     */
    public CategoryRecord setName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>CATEGORY.NAME</code>.
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
     * Create a detached CategoryRecord
     */
    public CategoryRecord() {
        super(JCategory.CATEGORY);
    }

    /**
     * Create a detached, initialised CategoryRecord
     */
    public CategoryRecord(Long categoryId, LocalDateTime lastUpdate, String name) {
        super(JCategory.CATEGORY);

        setCategoryId(categoryId);
        setLastUpdate(lastUpdate);
        setName(name);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised CategoryRecord
     */
    public CategoryRecord(Category value) {
        super(JCategory.CATEGORY);

        if (value != null) {
            setCategoryId(value.getCategoryId());
            setLastUpdate(value.getLastUpdate());
            setName(value.getName());
            resetChangedOnNotNull();
        }
    }
}
