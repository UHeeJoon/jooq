package com.example.jooqfirst.config;

import org.jooq.ExecuteContext;
import org.jooq.ExecuteListener;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.tools.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-27 오후 2:18
 */
public class PerformanceListener implements ExecuteListener {
  private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
  private StopWatch stopWatch;
  private static final Duration SLOW_QUERY_LIMIT = Duration.ofMillis(3000);

  @Override
  public void executeStart(ExecuteContext ctx) {
    stopWatch = new StopWatch();
  }

  @Override
  public void executeEnd(ExecuteContext ctx) {
    final long queryTimeNano = stopWatch.split();

    if (queryTimeNano > SLOW_QUERY_LIMIT.toNanos()) {
      Query query = ctx.query();
      Duration executeTime = Duration.ofNanos(queryTimeNano);
      log.warn(
        String.format(
          """
          ### Slow SQL 탐지 >>
          경고: jOOQ로 실행된 쿼리 중 %d초 이상 실행된 쿼리가 있습니다.
          실행시간: %s초
          실행쿼리: %s
          """
          , SLOW_QUERY_LIMIT.toSeconds()
          , millisToSeconds(executeTime)
          , query
        )
      );
    }
  }

  private String millisToSeconds(Duration duration) {
    return String.format("%.1f", duration.toMillis() / 1000.0);
  }
}
