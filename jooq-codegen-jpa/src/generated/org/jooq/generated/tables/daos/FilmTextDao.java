/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.daos;


import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.generated.tables.JFilmText;
import org.jooq.generated.tables.pojos.FilmText;
import org.jooq.generated.tables.records.FilmTextRecord;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FilmTextDao extends DAOImpl<FilmTextRecord, FilmText, Integer> {

    /**
     * Create a new FilmTextDao without any configuration
     */
    public FilmTextDao() {
        super(JFilmText.FILM_TEXT, FilmText.class);
    }

    /**
     * Create a new FilmTextDao with an attached configuration
     */
    public FilmTextDao(Configuration configuration) {
        super(JFilmText.FILM_TEXT, FilmText.class, configuration);
    }

    @Override
    public Integer getId(FilmText object) {
        return object.getFilmId();
    }

    /**
     * Fetch records that have <code>FILM_ID BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FilmText> fetchRangeOfJFilmId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(JFilmText.FILM_TEXT.FILM_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>FILM_ID IN (values)</code>
     */
    public List<FilmText> fetchByJFilmId(Integer... values) {
        return fetch(JFilmText.FILM_TEXT.FILM_ID, values);
    }

    /**
     * Fetch a unique record that has <code>FILM_ID = value</code>
     */
    public FilmText fetchOneByJFilmId(Integer value) {
        return fetchOne(JFilmText.FILM_TEXT.FILM_ID, value);
    }

    /**
     * Fetch a unique record that has <code>FILM_ID = value</code>
     */
    public Optional<FilmText> fetchOptionalByJFilmId(Integer value) {
        return fetchOptional(JFilmText.FILM_TEXT.FILM_ID, value);
    }

    /**
     * Fetch records that have <code>DESCRIPTION BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FilmText> fetchRangeOfJDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(JFilmText.FILM_TEXT.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>DESCRIPTION IN (values)</code>
     */
    public List<FilmText> fetchByJDescription(String... values) {
        return fetch(JFilmText.FILM_TEXT.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>TITLE BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FilmText> fetchRangeOfJTitle(String lowerInclusive, String upperInclusive) {
        return fetchRange(JFilmText.FILM_TEXT.TITLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>TITLE IN (values)</code>
     */
    public List<FilmText> fetchByJTitle(String... values) {
        return fetch(JFilmText.FILM_TEXT.TITLE, values);
    }
}
