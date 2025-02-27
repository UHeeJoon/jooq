package com.example.jooqfirst.config.converter;

import com.example.jooqfirst.film.FilmPriceSummary;
import org.jooq.impl.EnumConverter;
import org.springframework.stereotype.Component;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-23 오전 3:04
 */
public class PriceCategoryConverter extends EnumConverter<String, FilmPriceSummary.PriceCategory> {
  public PriceCategoryConverter() {
    super(String.class, FilmPriceSummary.PriceCategory.class, FilmPriceSummary.PriceCategory::getCode);
  }
}
