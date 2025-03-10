/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.records;


import java.math.BigDecimal;

import javax.annotation.processing.Generated;

import org.jooq.generated.tables.JSalesByFilmCategory;
import org.jooq.generated.tables.pojos.SalesByFilmCategory;
import org.jooq.impl.TableRecordImpl;


/**
 * VIEW
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
public class SalesByFilmCategoryRecord extends TableRecordImpl<SalesByFilmCategoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>sakila.sales_by_film_category.category</code>.
     */
    public SalesByFilmCategoryRecord setCategory(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>sakila.sales_by_film_category.category</code>.
     */
    public String getCategory() {
        return (String) get(0);
    }

    /**
     * Setter for <code>sakila.sales_by_film_category.total_sales</code>.
     */
    public SalesByFilmCategoryRecord setTotalSales(BigDecimal value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>sakila.sales_by_film_category.total_sales</code>.
     */
    public BigDecimal getTotalSales() {
        return (BigDecimal) get(1);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SalesByFilmCategoryRecord
     */
    public SalesByFilmCategoryRecord() {
        super(JSalesByFilmCategory.SALES_BY_FILM_CATEGORY);
    }

    /**
     * Create a detached, initialised SalesByFilmCategoryRecord
     */
    public SalesByFilmCategoryRecord(String category, BigDecimal totalSales) {
        super(JSalesByFilmCategory.SALES_BY_FILM_CATEGORY);

        setCategory(category);
        setTotalSales(totalSales);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised SalesByFilmCategoryRecord
     */
    public SalesByFilmCategoryRecord(SalesByFilmCategory value) {
        super(JSalesByFilmCategory.SALES_BY_FILM_CATEGORY);

        if (value != null) {
            setCategory(value.getCategory());
            setTotalSales(value.getTotalSales());
            resetChangedOnNotNull();
        }
    }
}
