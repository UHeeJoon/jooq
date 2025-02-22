package com.example.jooqfirst.film;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
  PriceCategory priceCategory,
  Long totalInventory
) {

  @Getter
  @AllArgsConstructor
  public enum PriceCategory {
    CHEAP("Cheap"),
    MODERATE("Moderate"),
    EXPENSIVE("Expensive");

    private final String code;

    public static PriceCategory findByCode(String code) {
      for (PriceCategory value : PriceCategory.values()) {
        if (value.code.equals(code)) {
          return value;
        }
      }
      return null;
    }

  }
}
