package com.example.jooqfirst.update;

import com.example.jooqfirst.actor.ActorRepository;
import com.example.jooqfirst.actor.ActorUpdateRequest;
import org.assertj.core.api.Assertions;
import org.jooq.generated.tables.pojos.Actor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-24 오후 5:40
 */
@Transactional
@SpringBootTest
public class JooqUpdateTest {

  @Autowired
  ActorRepository actorRepository;

  @Test
  @DisplayName("pojo를 사용한 update")
  void 업데이트_with_pojo() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("Tom");
    actor.setLastName("Cruise");

    Actor savedActor = actorRepository.saveWithReturning(actor);

    //when
    savedActor.setFirstName("Suri");
    actorRepository.update(savedActor);

    //then
    Actor updatedActor = actorRepository.findById(savedActor.getActorId());
    Assertions.assertThat(updatedActor.getFirstName()).isEqualTo("Suri");

  }

  @Test
  @DisplayName("일부 필드만 update - DTO 활용")
  void 업데이트_일부_필드만() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("Tom");
    actor.setLastName("Cruise");

    //when
    Long newActorId = actorRepository.saveWithReturningPkOnly(actor);
    ActorUpdateRequest request = ActorUpdateRequest.builder()
      .firstName("Suri")
      .build();

    actorRepository.updateWithDto(newActorId, request);

    //then
    Assertions.assertThat(actorRepository.findById(newActorId).getFirstName()).isEqualTo("Suri");


  }

  @Test
  @DisplayName("일부 필드만 update - DTO 활용")
  void 업데이트_일부_필드만_with_record() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("Tom");
    actor.setLastName("Cruise");

    //when
    Long newActorId = actorRepository.saveWithReturningPkOnly(actor);
    ActorUpdateRequest request = ActorUpdateRequest.builder()
      .firstName("Suri")
      .build();

    actorRepository.updateWithRecord(newActorId, request);

    //then
    Assertions.assertThat(actorRepository.findById(newActorId).getFirstName()).isEqualTo("Suri");


  }

  @Test
  @DisplayName("delete 예제")
  void delete_예제() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("Tom");
    actor.setLastName("Cruise");

    Long newActorId = actorRepository.saveWithReturningPkOnly(actor);

    //when

    int result = actorRepository.delete(newActorId);

    //then
    Assertions.assertThat(result).isEqualTo(1);
  }

  @Test
  @DisplayName("delete 예제 - with active record")
  void delete_with_active_record_예제() {
    //given
    Actor actor = new Actor();
    actor.setFirstName("Tom");
    actor.setLastName("Cruise");

    Long newActorId = actorRepository.saveWithReturningPkOnly(actor);

    //when
    int result = actorRepository.deleteWithRecord(newActorId);

    // then
    Assertions.assertThat(result).isEqualTo(1);

  }


}
