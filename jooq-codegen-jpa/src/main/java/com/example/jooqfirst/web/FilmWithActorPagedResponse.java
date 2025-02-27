package com.example.jooqfirst.web;

import com.example.jooqfirst.film.FilmWithActor;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 8:40
 */
public record FilmWithActorPagedResponse(
  PagedResponse page,
  List<FilmActorResponse> filmActorList
) {

  public FilmWithActorPagedResponse(List<FilmWithActor> filmWithActorList, PagedResponse pageResponse) {
    this(pageResponse, filmWithActorList.stream().map(FilmActorResponse::new).toList());
  }

  public record FilmActorResponse(
    String filmTitle,
    String actorFullName,
    Long filmId
  ) {
    public FilmActorResponse(FilmWithActor filmWithActor) {
      this(
        filmWithActor.title(),
        filmWithActor.actorFullName(),
        filmWithActor.filmId()
      );
    }
  }
}
