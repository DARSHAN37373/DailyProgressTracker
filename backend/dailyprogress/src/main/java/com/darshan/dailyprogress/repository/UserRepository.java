package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}