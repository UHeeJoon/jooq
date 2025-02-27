package com.example.jooqfirst.activeRecord;

import com.example.jooqfirst.actor.ActorRepository;
import org.assertj.core.api.Assertions;
import org.jooq.DSLContext;
import org.jooq.generated.tables.JActor;
import org.jooq.generated.tables.records.ActorRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-24 오후 9:06
 */
@Transactional
@SpringBootTest
public class JooqActiveRecordTest {

  @Autowired
  ActorRepository actorRepository;

  @Autowired
  DSLContext dslContext;

  @Test
  @DisplayName("SELECT절 예제")
  void activeRecord_조회_예제() {
    //given
    Long actorId = 1L;

    //when
    ActorRecord actorRecord = actorRepository.findRecordByActorId(actorId);

    //then
    Assertions.assertThat(actorRecord).hasNoNullFieldsOrProperties();
    
  }
  
  @Test
  @DisplayName("activeRecord refresh 예제")
  void activeRecord_refresh_예제() {
    //given
    Long actorId = 1L;
    ActorRecord actorRecord = actorRepository.findRecordByActorId(actorId);
    actorRecord.setFirstName(null);

    //when
    // db에서 다시 조회 해서 초기화
    actorRecord.refresh(); 
//    actorRecord.refresh(JActor.ACTOR.FIRST_NAME); // 특정 필드만 다시 조회할 수도 있음

    //then
    Assertions.assertThat(actorRecord.getFirstName()).isNotBlank();
    
  }
  
  @Test
  @DisplayName("activeRecord store 예제 - insert")
  void activeRecord_insert_예제() {
    //given
    ActorRecord record = dslContext.newRecord(JActor.ACTOR); // Record 생성

    //when
    record.setFirstName("John");
    record.setLastName("Doe");
    record.store();  // insert로 할 경우 같은 필드가 존재하면 insert, 없으면 update ≒ upsert
//    record.insert();
    record.refresh(); // 변경된 내용이 record에는 적용 되어있지 않아서 refresh

    //then
    Assertions.assertThat(record.getLastUpdate()).isNotNull();
    
  }
  
  @Test
  @DisplayName("activeRecord store 예제 - update")
  void activeRecord_update_예제() {
    //given
    Long actorId = 1L;
    String newName = "test";
    ActorRecord actor = actorRepository.findRecordByActorId(actorId);

    //when
    actor.setFirstName(newName);
    actor.store(); // or actor.update();

    //then
    Assertions.assertThat(actor.getFirstName()).isEqualTo(newName);
    
  }
  
  @Test
  @DisplayName("activeRecord delete 예제")
  void activeRecord_delete_예제() {
    //given
    ActorRecord record = dslContext.newRecord(JActor.ACTOR); // Record 생성
    record.setFirstName("John");
    record.setLastName("Doe");
    record.store();  // insert로 할 경우 같은 필드가 존재하면 insert, 없으면 update ≒ upsert

    //when
    int result = record.delete();

    //then
    Assertions.assertThat(result).isEqualTo(1);
  }



}
