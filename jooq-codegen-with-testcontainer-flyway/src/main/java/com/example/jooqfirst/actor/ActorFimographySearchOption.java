package com.example.jooqfirst.actor;

import lombok.Builder;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-21 오후 9:35
 */
@Builder
public record ActorFimographySearchOption(
  String actorName,
  String filmTitle
) {
}
