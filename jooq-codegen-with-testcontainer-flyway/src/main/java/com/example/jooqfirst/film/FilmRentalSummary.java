package com.example.jooqfirst.film;

import java.math.BigDecimal;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-22 오후 10:21
 */
public record FilmRentalSummary(
  Long filmId,
  String filmTitle,
  BigDecimal averageRentalDuration
) {
}
