package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findUserById(Long id) {
    return this.userRepository.findById(id).orElseThrow();
  }
}
