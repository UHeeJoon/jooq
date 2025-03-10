package com.example.jooqfirst.actor;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Row2;
import org.jooq.generated.tables.JActor;
import org.jooq.generated.tables.JFilm;
import org.jooq.generated.tables.JFilmActor;
import org.jooq.generated.tables.daos.ActorDao;
import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.generated.tables.records.ActorRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

  // ====================
  // 조회
  // ====================
  
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

  // ====================
  // insert
  // ====================

  public Actor saveByDao(Actor actor) {
    // pk(actorId)가 actor에 추가 됨
    actorDao.insert(actor);
    return actor;
  }

  public ActorRecord saveByRecord(Actor actor) {
    ActorRecord actorRecord = dslContext.newRecord(ACTOR, actor);
    actorRecord.insert();
    return actorRecord;
  }

  public Long saveWithReturningPkOnly(Actor actor) {
    return dslContext.insertInto(
        ACTOR,
        ACTOR.FIRST_NAME,
        ACTOR.LAST_NAME
      ).values(
        actor.getFirstName(),
        actor.getLastName()
      ).returningResult(ACTOR.ACTOR_ID)
      .fetchOneInto(Long.class);
    //    return dslContext.insertInto(ACTOR)
//      .set(ACTOR.FIRST_NAME, actor.getFirstName())
//      .set(ACTOR.LAST_NAME, actor.getLastName())
//      .returningResult(ACTOR.ACTOR_ID)
//      .fetchOneInto(Long.class);
  }

  public Actor saveWithReturning(Actor actor) {
    return dslContext.insertInto(
        ACTOR,
        ACTOR.FIRST_NAME,
        ACTOR.LAST_NAME
      ).values(
        actor.getFirstName(),
        actor.getLastName()
      ).returning(ACTOR.fields())
      .fetchOneInto(Actor.class);
  }

  public void bulkInsertWithRows(List<Actor> actorList) {
    List<Row2<String, String>> rows = actorList.stream()
      .map(actor -> DSL.row(actor.getFirstName(), actor.getLastName()))
      .toList();

    dslContext.insertInto(
      ACTOR,
      ACTOR.FIRST_NAME,
      ACTOR.LAST_NAME
    ).valuesOfRows(
      rows
    ).execute();
  }

  // ====================
  // 데이터 조작
  // ====================

  public void update(Actor actor) {
    actorDao.update(actor);
  }

  public Actor findById(Long actorId) {
    return actorDao.findById(actorId);
  }

  public int updateWithDto(Long newActorId, ActorUpdateRequest request) {
    var firstName = StringUtils.hasText(request.firstName()) ? DSL.val(request.firstName()) : DSL.noField(ACTOR.FIRST_NAME);
    var lastName = StringUtils.hasText(request.lastName()) ? DSL.val(request.lastName()) : DSL.noField(ACTOR.LAST_NAME);

    return dslContext.update(ACTOR)
      .set(ACTOR.FIRST_NAME, firstName)
      .set(ACTOR.LAST_NAME, lastName )
      .where(ACTOR.ACTOR_ID.eq(newActorId))
      .execute();
  }

  public int updateWithRecord(Long newActorId, ActorUpdateRequest request) {
    ActorRecord record = dslContext.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(newActorId));

    if (StringUtils.hasText(request.firstName())) {
      record.setFirstName(request.firstName());
    }

    if(StringUtils.hasText(request.lastName())) {
      record.setLastName(request.lastName());
    }

    return dslContext.update(ACTOR)
      .set(record)
      .where(ACTOR.ACTOR_ID.eq(newActorId))
      .execute();

//    return record.update(); // record.store();

  }

  public int delete(Long newActorId) {
    return dslContext.deleteFrom(ACTOR)
      .where(ACTOR.ACTOR_ID.eq(newActorId))
      .execute();
  }

  public int deleteWithRecord(Long newActorId) {
    ActorRecord record = dslContext.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(newActorId));

    return Objects.requireNonNull(record).delete();
  }

  // ====================
  // ActiveRecord
  // ====================

  public ActorRecord findRecordByActorId(Long actorId) {
    /**
     * ActiveRecord 를 활용하기 위해서는 dslContext를 반드시 사용해야 한다.
     * new 키워드로 생성하게 되면 dslContext의 jdbc의 환경들이 적용되지 않는다.
     */
    return dslContext
      .fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(actorId));
  }
}
