package com.example.jooqfirst;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.generated.tables.JActor;
import org.jooq.generated.tables.records.ActorRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JooqFirstTest {

  @Autowired
  DSLContext dslContext;

  @Test
  void contextLoads() {
    dslContext.selectFrom(JActor.ACTOR)
      .limit(10)
      .fetch();
  }



}
