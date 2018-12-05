package io.github.villim.blog.infrastructure.springdata;

import io.github.villim.blog.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

}
