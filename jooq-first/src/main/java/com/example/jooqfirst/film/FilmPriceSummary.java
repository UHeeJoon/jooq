package com.example.jooqfirst.film;

import java.math.BigDecimal;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-22 오후 10:03
 */
public record FilmPriceSummary(
  Long filmId,
  String title,
  BigDecimal rentalRate,
  String priceCategory,
  Long totalInventory
) {
}
