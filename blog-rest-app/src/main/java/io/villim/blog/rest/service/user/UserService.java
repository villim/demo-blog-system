package io.villim.blog.rest.service.user;

import io.villim.blog.domain.user.User;
import io.villim.blog.rest.schema.UserRequest;

import java.util.Optional;

public interface UserService {

    Optional<User> getById(long id);

    long create(UserRequest userRequest);

    long update(UserRequest userRequest);

    void delete(long id);

}
