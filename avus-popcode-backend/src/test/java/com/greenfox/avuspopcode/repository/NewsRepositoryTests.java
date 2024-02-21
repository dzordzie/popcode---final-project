package com.greenfox.avuspopcode.repository;

import com.greenfox.avuspopcode.entities.News;
import com.greenfox.avuspopcode.repositories.NewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class NewsRepositoryTests {

  @Autowired
  private NewsRepository newsRepository;

  private News news;


  @BeforeEach
  public void setupTestDate() {
    // Given : Setup object or precondition
    news = new News();
    news.setTitle("Superman again");
    news.setText("klark Kent is not as cool as superman");
  }

  // JUnit Test for save news operation
  @Test
  @DisplayName("Unit Test for save news operation")
  public void givenNewsObject_whenSave_thenReturnSaveNews() {

    // When : Action of behaviours that we are going to test
    News saveNews = newsRepository.save(news);

    // Then : Verify the output

    assertThat(saveNews).isNotNull();
    assertThat(saveNews.getId()).isGreaterThan(0);
  }


  // JUnit test for get News List operation
  @Test
  @DisplayName("JUnit test for get News List")
  public void givenNewsList_whenFindAll_thenNewsList() {

    // Given : Setup object or precondition
    News news1 = new News();
    news1.setTitle("Batman forever");
    news1.setText("Cat woman in town - bruce wayne is cool");

    News news2 = new News();
    news2.setTitle("Spiderman fools Mary Jane");
    news2.setText("Peter Parker is furious - not mention his aunt");

    newsRepository.save(news1);
    newsRepository.save(news2);

    // When : Action of behaviours that we are going to test
    List<News> newsList = newsRepository.findAll();

    // Then : Verify the output
    assertThat(newsList).isNotEmpty();
    assertThat(newsList).size().isEqualTo(2);
  }

  @Test
  @DisplayName("JUnit test for get news By Id")
  public void givenNewsObject_whenFindById_thenReturnNewsObject() {

    // Given : Setup object or precondition
    newsRepository.save(news);

    // When : Action of behaviours that we are going to test
    News getNews = newsRepository.findById(news.getId()).get();

    // Then : Verify the output
    assertThat(getNews).isNotNull();
  }
}
