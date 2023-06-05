package com.emte.usermanager.dao;

import com.emte.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT u.password FROM User u WHERE u.login = :login")
    String getPasswordByLogin(@Param("login") String login);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.login = :login")
    boolean hasAnExistingLogin(@Param("login") String login);
}
