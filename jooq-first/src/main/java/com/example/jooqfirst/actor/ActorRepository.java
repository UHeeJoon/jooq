package com.example.jooqfirst.actor;

import com.example.jooqfirst.util.jooq.JooqListConditionUtil;
import org.jooq.*;
import org.jooq.generated.tables.JActor;
import org.jooq.generated.tables.JFilm;
import org.jooq.generated.tables.JFilmActor;
import org.jooq.generated.tables.daos.ActorDao;
import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.generated.tables.records.FilmRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import static com.example.jooqfirst.util.jooq.JooqListConditionUtil.containsIfNotBlank;
import static com.example.jooqfirst.util.jooq.JooqListConditionUtil.inIfNotEmpty;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-21 오후 9:06
 */
@Repository
public class ActorRepository {

  private final DSLContext dslContext;
  private final ActorDao actorDao;
  private final JActor ACTOR = JActor.ACTOR;

  public ActorRepository(DSLContext dslContext, Configuration configuration) {
    this.actorDao = new ActorDao(configuration);
    this.dslContext = dslContext;
  }

  public List<Actor> findByFirstNameAndLastName(String firstName, String lastName) {
    // and 조건
    // 방법 1 - method chaining
    //    return dslContext.selectFrom(ACTOR)
//      .where(ACTOR.FIRST_NAME.eq(firstName).and(ACTOR.LAST_NAME.eq(lastName)))
//      .fetchInto(Actor.class);

    // 방법 2 - parameter
    return dslContext.selectFrom(ACTOR)
      .where(
        ACTOR.FIRST_NAME.eq(firstName),
        ACTOR.LAST_NAME.eq(lastName)
      )
      .fetchInto(Actor.class);


  }

  public List<Actor> findByFirstNameOrLastName(String firstName, String lastName) {
    return dslContext.selectFrom(ACTOR)
      .where(
        ACTOR.FIRST_NAME.eq(firstName).or(ACTOR.LAST_NAME.eq(lastName))
      )
      .fetchInto(Actor.class);
  }

  public List<Actor> findByActorIdIn(List<Long> idList) {
    return dslContext
      .selectFrom(ACTOR)
//      .where(ACTOR.ACTOR_ID.in(idList))
      // 조건 커스텀
      .where(inIfNotEmpty(ACTOR.ACTOR_ID, idList))
      .fetchInto(Actor.class);
  }

  public List<ActorFilmography> findActorFilmography(ActorFimographySearchOption searchOption) {
    final JFilmActor FILM_ACTOR = JFilmActor.FILM_ACTOR;
    final JFilm FILM = JFilm.FILM;

    // jooq의 단점 : 1: N 매칭의 경우 타입 변환이 조금 번거롭다.
    Map<Actor, List<Film>> actorListMap = dslContext.select(
        DSL.row(ACTOR.fields()).as("actor"),
        DSL.row(FILM.fields()).as("film")
      )
      .from(ACTOR)
      .join(FILM_ACTOR).on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
      .join(FILM).on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
      .where(
        containsIfNotBlank(ACTOR.FIRST_NAME.concat(" ").concat(ACTOR.LAST_NAME), searchOption.actorName()),
        containsIfNotBlank(FILM.TITLE, searchOption.filmTitle())
      )
      .fetchGroups(
        record -> record.get("actor", Actor.class),
        record -> record.get("film", Film.class)
      );

    return actorListMap.entrySet().stream()
      .map(entry -> ActorFilmography.create(entry.getKey(), entry.getValue()))
      .toList();

  }


}
