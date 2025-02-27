package com.example.jooqfirst.actor;

import lombok.Builder;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-24 오후 5:40
 */
@Builder
public record ActorUpdateRequest(
  String firstName,
  String lastName
) {
}
