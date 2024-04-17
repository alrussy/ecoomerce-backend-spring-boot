package com.alrussy.idantityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.idantityservice.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByEmail(String email);
}
