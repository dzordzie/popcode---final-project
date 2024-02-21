package com.greenfox.avuspopcode.repository;


import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserRepositoryTests {

  @Autowired
  private UserRepository userRepository;

  private User user;

  @BeforeEach
  public void setupTestDate() {
    // Given : Setup object or precondition
    user = new User();
    user.setName("Superman");
    user.setEmail("klark.kent@gmail.cool");
    user.setAdmin(true);
  }

  // JUnit Test for save user operation
  @Test
  @DisplayName("Unit Test for save user operation")
  public void givenUserObject_whenSave_thenReturnSaveUser() {

    // When : Action of behaviours that we are going to test
    User saveUser = userRepository.save(user);

    // Then : Verify the output

    assertThat(saveUser).isNotNull();
    assertThat(saveUser.getId()).isGreaterThan(0);
  }


  // JUnit test for get User List operation
  @Test
  @DisplayName("JUnit test for get User List")
  public void givenUserList_whenFindAll_thenUserList() {

    // Given : Setup object or precondition
    User user1 = new User();
    user1.setName("Batman");
    user1.setEmail("bruce.wayne@gmail.cool");
    user1.setAdmin(true);

    User user2 = new User();
    user2.setName("Spiderman");
    user2.setEmail("peter.parker@gmail.cool");
    user2.setAdmin(false);

    userRepository.save(user1);
    userRepository.save(user2);

    // When : Action of behaviours that we are going to test
    List<User> users = userRepository.findAll();

    // Then : Verify the output
    assertThat(users).isNotEmpty();
    assertThat(users).size().isEqualTo(2);
  }

  @Test
  @DisplayName("JUnit test for get User By Id")
  public void givenUserObject_whenFindById_thenReturnUserObject() {

    // Given : Setup object or precondition
    userRepository.save(user);

    // When : Action of behaviours that we are going to test
    User getUser = userRepository.findById(user.getId()).get();

    // Then : Verify the output
    assertThat(getUser).isNotNull();
  }

}
