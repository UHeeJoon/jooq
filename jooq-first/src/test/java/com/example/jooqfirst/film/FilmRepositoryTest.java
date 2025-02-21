package com.example.jooqfirst.film;

import com.example.jooqfirst.web.FilmWithActorPagedResponse;
import com.example.jooqfirst.web.PagedResponse;
import org.jooq.generated.tables.pojos.Film;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 8:15
 */
@SpringBootTest
class FilmRepositoryTest {

  @Autowired
  FilmRepository filmRepository;

  @Autowired
  FilmService filmService;

  @Test
  @DisplayName("1) 영화 정보 조회")
  void test() {
    Film film = filmRepository.findById(1L);
    assertThat(film).isNotNull();
  }


  @Test
  @DisplayName("2) 영화 정보 간략 조회")
  void test2() {
    SimpleFilmInfo film = filmRepository.findSimpleFilmInfoById(1L);
    assertThat(film).hasNoNullFieldsOrProperties();
  }

  @Test
  @DisplayName("3) 영화에 출연한 배우 정보를 페이징 해서 조회")
  void test3() {

//    List<FilmWithActor> filmWithActorList = filmRepository.findFilmWithActorList(1L, 10L);
//    filmWithActorList.forEach(System.out::println);

    FilmWithActorPagedResponse filmActorPageResponseList = filmService.getFilmActorPageRespones(1L, 10L);

    assertThat(filmActorPageResponseList.filmActorList()).hasSize(10);
    assertThat(filmActorPageResponseList.filmActorList()).allSatisfy(filmActor -> {
      assertThat(filmActor).hasNoNullFieldsOrProperties();
    });
    assertThat(filmActorPageResponseList.page()).hasNoNullFieldsOrProperties();
    assertThat(filmActorPageResponseList.page().page()).isEqualTo(1L);
    assertThat(filmActorPageResponseList.page().pageSize()).isEqualTo(10L);


  }


}