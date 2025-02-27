package com.example.jooqfirst.subquery;

import com.example.jooqfirst.film.FilmPriceSummary;
import com.example.jooqfirst.film.FilmRentalSummary;
import com.example.jooqfirst.film.FilmRepositoryHasA;
import org.assertj.core.api.Assertions;
import org.jooq.generated.tables.pojos.Film;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-22 오후 10:04
 */
@SpringBootTest
public class JooqSubqueryTest {

  @Autowired
  FilmRepositoryHasA filmRepositoryHasA;

  @Test
  @DisplayName("""
       영화별 대요료가 
       1.0 이하면 'Cheap'
       3.0 이하면 'Moderate'
       그 이상이면 'Expensive'로 분류하고,
       각 영화의 총 재고 수를 조회한다.
    """)
  void 스칼라_서브쿼리_예제() {
    //given
    String filmTitle = "EGG";
    //when
    List<FilmPriceSummary> priceSummaryList = filmRepositoryHasA.findFilmPriceSummaryByFilmTitle(filmTitle);
    //then
    Assertions.assertThat(priceSummaryList).hasSizeGreaterThan(1);
  }
  
  @Test
  @DisplayName("평균 대여 기간이 가장 긴 영화부터 정렬해서 조회한다.")
  void from절_서브쿼리_인라인뷰_예제() {
    //given
    String filmTitle = "EGG";
    //when
    List<FilmRentalSummary> filmRentalSummaryList = filmRepositoryHasA.findFilmRentalSummaryByFilmTitle(filmTitle);
    //then
    Assertions.assertThat(filmRentalSummaryList).hasSizeGreaterThan(1);
  }
  
  @Test
  @DisplayName("대여된 기록이 있는 영화만 조회")
  void 조건절_서브쿼리_예제() {
    //given
    String filmTitle = "EGG";
    //when
    List<Film> rentedFilmList = filmRepositoryHasA.findRentedFilmByTitle(filmTitle);
    //then
    Assertions.assertThat(rentedFilmList).isNotEmpty();
  }
  
  
}
