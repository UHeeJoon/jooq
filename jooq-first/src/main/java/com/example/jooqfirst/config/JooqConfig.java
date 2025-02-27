package com.example.jooqfirst.config;

import org.jooq.conf.ExecuteWithoutWhere;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 6:39
 */
@Configuration
public class JooqConfig {

  @Bean
  public DefaultConfigurationCustomizer configurationCustomizer() {
    return c -> {
      c.set(PerformanceListener::new);
      c.settings()
        // 조건절 없이 delete 할 경우 throw
        .withExecuteDeleteWithoutWhere(ExecuteWithoutWhere.THROW)
        // 조건절 없이 update 할 경우 throw
        .withExecuteUpdateWithoutWhere(ExecuteWithoutWhere.THROW)
        // 쿼리에서 스키마 이름 빼고 나오게 하기
        .withRenderSchema(false);
    };
  }

}
