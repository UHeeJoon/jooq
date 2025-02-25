package com.example.jooqfirst.actor;

import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-21 오후 9:33
 */
public record ActorFilmography(
  Actor actor,
  List<Film> filmList
) {
  public static ActorFilmography create(Actor actor, List<Film> filmList) {
    return new ActorFilmography(actor, filmList);
  }
}
