package com.example.jooqfirst.slowQueryTest;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-27 오후 2:24
 */
@SpringBootTest
public class JooqSlowQueryTest {

  @Autowired
  DSLContext dslContext;

  @Test
  @DisplayName("SLOW 쿼리 탐지테스트")
  void 슬로우쿼리_탐지_테스트() {
    dslContext.select(DSL.field("SLEEP(4)"))
      .from(DSL.dual())
      .execute();
  }

}
