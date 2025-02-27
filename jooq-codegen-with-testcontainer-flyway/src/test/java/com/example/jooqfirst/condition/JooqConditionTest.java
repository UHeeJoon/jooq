package com.example.jooqfirst.condition;

import com.example.jooqfirst.actor.ActorFilmography;
import com.example.jooqfirst.actor.ActorFimographySearchOption;
import com.example.jooqfirst.actor.ActorRepository;
import org.assertj.core.api.Assertions;
import org.jooq.generated.tables.pojos.Actor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-21 오후 9:08
 */
@SpringBootTest
class JooqConditionTest {

  @Autowired
  ActorRepository actorRepository;

  @Test
  @DisplayName("and 조건 검색 - firstName과 lastName이 일치하는 배우 조회")
  void AND조건_1() {
    //given
    String firstName = "ED";
    String lastName = "CHASE";

    //when
    List<Actor> actorList = actorRepository.findByFirstNameAndLastName(firstName, lastName);

    //then
    Assertions.assertThat(actorList).hasSize(1);
  }

  @Test
  @DisplayName("or 조건 검색 - firstName또는 lastName이 일치하는 배우 조회")
  void or조건_1() {
    //given
    String firstName = "ED";
    String lastName = "CHASE";

    //when
    List<Actor> actorList = actorRepository.findByFirstNameOrLastName(firstName, lastName);

    //then
    Assertions.assertThat(actorList).hasSizeGreaterThan(1);
  }

  @Test
  @DisplayName("in절 - 동적 조건 검색")
  void in절_동적_조건_검색_1() {
    //given
    List<Long> idList = List.of(1L, 2L);

    //when
    // in절에 emptyList, null가 들어가면 where절에 false가 들어가 쿼리는 싫행되지만 아무것도 가져오지 않는다.
    List<Actor> actorList = actorRepository.findByActorIdIn(idList);

    //then
    Assertions.assertThat(actorList).hasSize(2);
  }

  @Test
  @DisplayName("in절 - 동적 조건 검색 - empty list시 제외")
  void in절_동적_조건_검색_2() {
    //given
    List<Long> idList = Collections.emptyList();

    //when
    List<Actor> actorList = actorRepository.findByActorIdIn(idList);

    //then
    Assertions.assertThat(actorList).hasSizeGreaterThan(1);

  }
  
  @Test
  @DisplayName("다중 조건 검색 - 배우 이름으로 검색")
  void 다중_조건_검색_1() {
    //given
    ActorFimographySearchOption searchOption = ActorFimographySearchOption.builder()
      .actorName("LOLLOBRIGIDA").build();

    //when
    List<ActorFilmography> actorFilmographies = actorRepository.findActorFilmography(searchOption);

    //then
    Assertions.assertThat(actorFilmographies).hasSize(1);

  }
  
  @Test
  @DisplayName("다중 조건 검색 - 배우 이름과 영화 제목으로 검색")
  void 다중_조건_검색_2() {
    //given
    ActorFimographySearchOption searchOption = ActorFimographySearchOption.builder()
      .actorName("LOLLOBRIGIDA").filmTitle("COMMANDMENTS EXPRESS").build();

    //when
    List<ActorFilmography> actorFilmographies = actorRepository.findActorFilmography(searchOption);

    //then
    Assertions.assertThat(actorFilmographies).hasSize(1);
    Assertions.assertThat(actorFilmographies.get(0).filmList()).hasSize(1);
    
  }

}