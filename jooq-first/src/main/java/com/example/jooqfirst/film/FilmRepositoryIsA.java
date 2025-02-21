package com.example.jooqfirst.film;

import lombok.RequiredArgsConstructor;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.generated.tables.JActor;
import org.jooq.generated.tables.JFilm;
import org.jooq.generated.tables.JFilmActor;
import org.jooq.generated.tables.daos.FilmDao;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 8:04
 */
@Repository
public class FilmRepositoryIsA extends FilmDao {

  private final DSLContext dsl;
  // 메인으로 사용할 테이블 명시
  private final JFilm FILM = JFilm.FILM;

  // Configuration 주입
  public FilmRepositoryIsA(Configuration configuration, DSLContext dsl) {
    super(configuration);
    this.dsl = dsl;
  }

  public SimpleFilmInfo findSimpleFilmInfoById(Long id) {
    return dsl.select(FILM.FILM_ID, FILM.TITLE, FILM.DESCRIPTION)
      .from(FILM)
      .where(FILM.FILM_ID.eq(id))
      .fetchOneInto(SimpleFilmInfo.class);
  }

  public List<FilmWithActor> findFilmWithActorList(Long page, Long pagesize) {
    JFilmActor FILM_ACTOR = JFilmActor.FILM_ACTOR;
    JActor ACTOR = JActor.ACTOR;
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


}
