package com.example.jooqfirst.film;

import com.example.jooqfirst.config.converter.PriceCategoryConverter;
import org.jooq.*;
import org.jooq.generated.tables.*;
import org.jooq.generated.tables.daos.FilmDao;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import static org.jooq.impl.DSL.*;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 8:04
 */
@Repository
public class FilmRepositoryHasA {

  private final DSLContext dsl;
  private final FilmDao filmDao;
  // 메인으로 사용할 테이블 명시
  private final JFilm FILM = JFilm.FILM;
  private final DefaultDSLContext dslContext;

  public FilmRepositoryHasA(DSLContext dsl, Configuration configuration, DefaultDSLContext dslContext) {
    this.dsl = dsl;
    this.filmDao = new FilmDao(configuration);
    this.dslContext = dslContext;
  }

  public Film findById(Long id) {
    return filmDao.findById(id);
  }

  public List<Film> findByRangeBetween(Integer from, Integer to) {
    return filmDao.fetchRangeOfJLength(from, to);
  }

  public SimpleFilmInfo findSimpleFilmInfoById(Long id) {
    return dsl.select(FILM.FILM_ID, FILM.TITLE, FILM.DESCRIPTION)
      .from(FILM)
      .where(FILM.FILM_ID.eq(id))
      .fetchOneInto(SimpleFilmInfo.class);
  }

  public List<FilmWithActor> findFilmWithActorList(Long page, Long pagesize) {
    final JFilmActor FILM_ACTOR = JFilmActor.FILM_ACTOR;
    final JActor ACTOR = JActor.ACTOR;
    return dsl.select(
        DSL.row(FILM.fields()),
        DSL.row(FILM_ACTOR.fields()),
        DSL.row(ACTOR.fields())
      ).from(FILM)
      .join(FILM_ACTOR).on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
      .join(ACTOR).on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
      .offset((page - 1) * pagesize)
      .limit(pagesize)
      .fetchInto(FilmWithActor.class);
  }


  public List<FilmPriceSummary> findFilmPriceSummaryByFilmTitle(String filmTitle) {
    final JInventory INVENTORY = JInventory.INVENTORY;

    return dslContext.select(
        FILM.FILM_ID,
        FILM.TITLE,
        FILM.RENTAL_RATE,
        case_()
          .when(FILM.RENTAL_RATE.le(BigDecimal.valueOf(1.0)), "Cheap")
          .when(FILM.RENTAL_RATE.le(BigDecimal.valueOf(3.0)), "Moderate")
          .else_("Expensive").as("price_category").convert(new PriceCategoryConverter()),
        DSL.selectCount().from(INVENTORY).where(INVENTORY.FILM_ID.eq(FILM.FILM_ID)).asField("totalInventory")
      ).from(FILM)
      .where(FILM.TITLE.like("%%%s%%".formatted(filmTitle)))
      .fetchInto(FilmPriceSummary.class);
  }

  public List<FilmRentalSummary> findFilmRentalSummaryByFilmTitle(String filmTitle) {
    final JInventory INVENTORY = JInventory.INVENTORY;
    final JRental RENTAL = JRental.RENTAL;

    // subquery
    final Table<Record2<Long, BigDecimal>> rentalDurationInfoSubquery = select(
      INVENTORY.FILM_ID,
      avg(localDateTimeDiff(DatePart.DAY, RENTAL.RENTAL_DATE, RENTAL.RETURN_DATE)).as("average_rental_duration")
    ).from(RENTAL)
      .join(INVENTORY)
      .on(RENTAL.INVENTORY_ID.eq(INVENTORY.INVENTORY_ID))
      .where(RENTAL.RETURN_DATE.isNotNull())
      .groupBy(INVENTORY.FILM_ID)
      .asTable("rental_duration_info");

    // return
    return dslContext
      .select(
        FILM.FILM_ID,
        FILM.TITLE,
        rentalDurationInfoSubquery.field("average_rental_duration")
      ).from(FILM)
      .join(rentalDurationInfoSubquery)
      .on(FILM.FILM_ID.eq(rentalDurationInfoSubquery.field(INVENTORY.FILM_ID)))
      .where(FILM.TITLE.like("%%%s%%".formatted(filmTitle)))
      .orderBy(field(name("average_rental_duration")).desc())
      .fetchInto(FilmRentalSummary.class);


  }

  public List<Film> findRentedFilmByTitle(String filmTitle) {
    final JInventory INVENTORY = JInventory.INVENTORY;
    final JRental RENTAL = JRental.RENTAL;

    // subquery
    SelectConditionStep<Record1<Integer>> existSubquery = selectOne()
      .from(RENTAL)
      .join(INVENTORY)
      .on(RENTAL.INVENTORY_ID.eq(INVENTORY.INVENTORY_ID))
      .where(INVENTORY.FILM_ID.eq(FILM.FILM_ID));

    return dslContext
      .select(FILM.fields())
      .from(FILM)
      .whereExists(existSubquery) // exist는 whereExist로 사용 가능
      .and(FILM.TITLE.like("%%%s%%".formatted(filmTitle)))
      .fetchInto(Film.class);
  }
}
