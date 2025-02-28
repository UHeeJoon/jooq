package com.example.jooqfirst.joinTest;

import com.example.jooqfirst.film.FilmRepository;
import com.example.jooqfirst.film.FilmWithActor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-28 오전 11:34
 */
@SpringBootTest
public class JooqJoinShortCutTest {

  @Autowired
  FilmRepository filmRepository;

  @Test
  @DisplayName("implicitPathJoin_테스트")
  void implicitPathJoin_테스트() {

    List<FilmWithActor> original = filmRepository.findFilmWithActorsList(1L, 10L);
    List<FilmWithActor> implicit = filmRepository.findFilmWithActorsListImplicitPathJoin(1L, 10L);

    Assertions.assertThat(original)
      .usingRecursiveFieldByFieldElementComparator()
      .isEqualTo(implicit);
  }

  @Test
  @DisplayName("explicitPathJoin_테스트")
  void explicitPathJoin_테스트() {

    List<FilmWithActor> original = filmRepository.findFilmWithActorsList(1L, 10L);
    List<FilmWithActor> implicit = filmRepository.findFilmWithActorsListExplicitPathJoin(1L, 10L);

    Assertions.assertThat(original)
      .usingRecursiveFieldByFieldElementComparator()
      .isEqualTo(implicit);
  }
}