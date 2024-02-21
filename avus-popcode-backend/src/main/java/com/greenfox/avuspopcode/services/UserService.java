package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.entities.User;

public interface UserService {
  User findUserById(Long id);
}
