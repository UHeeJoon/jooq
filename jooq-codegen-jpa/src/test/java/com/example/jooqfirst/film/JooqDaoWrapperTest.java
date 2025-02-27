package com.example.jooqfirst.film;

import org.assertj.core.api.Assertions;
import org.jooq.generated.tables.pojos.Film;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-21 오후 8:45
 */
@SpringBootTest
class JooqDaoWrapperTest {

  @Autowired
  FilmRepositoryIsA filmRepositoryIsA; // 상속

  @Autowired
  FilmRepositoryHasA filmRepositoryHasA; // 커포지트


  @Test
  void test() {
    /**
     *
     * 상속 해서 사용할 경우 노출 시키고 싶지 않은 메서드들 모두 노출시키게 된다.
     * 또한 커스텀한 컬럼 아이디도 따라붙는 이슈가 있다(3.20버전 부터는 해결됨)
     */
    filmRepositoryIsA.fetchRangeOfJLanguageId(1L, 10L);
    Film byId = filmRepositoryIsA.findById(10L);
  }

  @Test
  @DisplayName("상속) 자동 생성 DAO 사용 - 영화 길이가 100 ~ 180 분 사이인 영화")
  void 상속_DAO_1() {
    //given
    Integer start = 100;
    Integer end = 180;
    //when
    List<Film> films = filmRepositoryIsA.fetchRangeOfJLength(start, end);
    //then
    Assertions.assertThat(films).allSatisfy(film -> {
      Integer length = film.getLength();
      Assertions.assertThat(length).isBetween(start, end);
    });

  }

  @Test
  @DisplayName("컴포지션) 자동 생성 DAO 사용 - 영화 길이가 100 ~ 180 분 사이인 영화")
  void 상속_DAO_2() {
    //given
    Integer start = 100;
    Integer end = 180;
    //when
    List<Film> films = filmRepositoryHasA.findByRangeBetween(start, end);
    //then
    Assertions.assertThat(films).allSatisfy(film -> {
      Integer length = film.getLength();
      Assertions.assertThat(length).isBetween(start, end);
    });

  }

}