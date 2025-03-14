/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.records;


import org.jooq.Record1;
import org.jooq.generated.tables.JFilmText;
import org.jooq.generated.tables.pojos.FilmText;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FilmTextRecord extends UpdatableRecordImpl<FilmTextRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>FILM_TEXT.FILM_ID</code>.
     */
    public FilmTextRecord setFilmId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>FILM_TEXT.FILM_ID</code>.
     */
    public Integer getFilmId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>FILM_TEXT.DESCRIPTION</code>.
     */
    public FilmTextRecord setDescription(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>FILM_TEXT.DESCRIPTION</code>.
     */
    public String getDescription() {
        return (String) get(1);
    }

    /**
     * Setter for <code>FILM_TEXT.TITLE</code>.
     */
    public FilmTextRecord setTitle(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>FILM_TEXT.TITLE</code>.
     */
    public String getTitle() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FilmTextRecord
     */
    public FilmTextRecord() {
        super(JFilmText.FILM_TEXT);
    }

    /**
     * Create a detached, initialised FilmTextRecord
     */
    public FilmTextRecord(Integer filmId, String description, String title) {
        super(JFilmText.FILM_TEXT);

        setFilmId(filmId);
        setDescription(description);
        setTitle(title);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised FilmTextRecord
     */
    public FilmTextRecord(FilmText value) {
        super(JFilmText.FILM_TEXT);

        if (value != null) {
            setFilmId(value.getFilmId());
            setDescription(value.getDescription());
            setTitle(value.getTitle());
            resetChangedOnNotNull();
        }
    }
}
