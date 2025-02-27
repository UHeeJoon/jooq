package com.example.jooqfirst.film;

import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.generated.tables.pojos.FilmActor;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 8:27
 */
public record FilmWithActor(
  Film film,
  FilmActor filmActor,
  Actor actor
) {

  public String title() {
    return film.getTitle();
  }

  public String actorFullName() {
    return actor.getFirstName() + " " + actor.getLastName();
  }

  public Long filmId() {
    return film.getFilmId();
  }

}
