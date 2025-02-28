package com.example.jooqfirst.film;

import com.example.jooqfirst.web.FilmWithActorPagedResponse;
import com.example.jooqfirst.web.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 8:25
 */
@Service
@RequiredArgsConstructor
public class FilmService {

  private final FilmRepository filmRepository;

  public FilmWithActorPagedResponse getFilmActorPageRespones(Long page, Long pagesize) {
    List<FilmWithActor> filmWithActorList = filmRepository.findFilmWithActorsList(page, pagesize);
    PagedResponse pagedResponse = new PagedResponse(page, pagesize);
    return new FilmWithActorPagedResponse(
      filmWithActorList,
      pagedResponse
    );
  }

}
