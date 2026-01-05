package com.leeviding.leevinote.user.application;

import com.leeviding.leevinote.user.domain.model.User;

import java.util.Optional;

public interface UserUseCase {
    User register(User user);
    Optional<User> findByUsername(String username);
}
