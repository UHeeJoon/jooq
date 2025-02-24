package com.example.jooqfirst.insert;

import com.example.jooqfirst.actor.ActorRepository;
import org.assertj.core.api.Assertions;
import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.records.ActorRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-24 오전 9:55
 */
@Transactional
@SpringBootTest
public class JooqInsertTest {

  @Autowired
  ActorRepository actorRepository;

  @Test
  @DisplayName("자동생성된 DAO를 통한 insert")
  void insert_dao() {
    // given
    Actor actor = new Actor();
    actor.setFirstName("John");
    actor.setLastName("Doe");

    // when
    Actor actor1 = actorRepository.saveByDao(actor);

    // then
    System.out.println(actor);
    Assertions.assertThat(actor.getActorId()).isNotNull();
    Assertions.assertThat(actor).isEqualTo(actor1);
  }

  @Test
  @DisplayName("ActiveRecord를 통한 insert")
  void insert_by_record() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("John");
    actor.setLastName("Doe");

    // when
    ActorRecord actorRecord = actorRepository.saveByRecord(actor);

    // then
    Assertions.assertThat(actorRecord.getActorId()).isNotNull();
  }

  @Test
  @DisplayName("SQL 실행 후 PK만 반환")
  void insert_with_returning_pk() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("John");
    actor.setLastName("Doe");

    // when
    Long pk = actorRepository.saveWithReturningPkOnly(actor);

    // then
    Assertions.assertThat(pk).isNotNull();

  }

  @Test
  @DisplayName("SQL 실행 후 해당 ROW 반환")
  void insert_with_returning() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("John");
    actor.setLastName("Doe");

    // when
    Actor insertActor = actorRepository.saveWithReturning(actor);

    // then
    Assertions.assertThat(insertActor).hasNoNullFieldsOrProperties();
  }

  @Test
  @DisplayName("bulk insert 예제")
  void bulk_insert() {
    //given
    Actor actor1 = new Actor();
    actor1.setFirstName("John");
    actor1.setLastName("Doe");

    Actor actor2 = new Actor();
    actor2.setFirstName("John2");
    actor2.setLastName("Doe2");

    List<Actor> actorList = List.of(actor1, actor2);

    // when && then
    actorRepository.bulkInsertWithRows(actorList);

  }

}
