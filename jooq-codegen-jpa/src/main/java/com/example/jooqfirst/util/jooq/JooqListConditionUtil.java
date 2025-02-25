package com.example.jooqfirst.util.jooq;

import lombok.NoArgsConstructor;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-21 오후 9:30
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class JooqListConditionUtil {

  // 커스텀 조건 util로 제작
  public static <T> Condition inIfNotEmpty(Field<T> id, List<T> idList) {
    // null이거나 비어 있으면 조건 제거 - 전체 조회
    if(CollectionUtils.isEmpty(idList)) {
      return DSL.noCondition();
    }
    return id.in(idList);
  }

  public static <T> Condition containsIfNotBlank(Field<T> field, T inputValue) {
    if(ObjectUtils.isEmpty(inputValue)) {
      return DSL.noCondition();
    }
    return field.like("%%%s%%".formatted(inputValue));
  }
}
