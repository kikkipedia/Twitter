package com.kicki.backend.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kicki.backend.crud.model.Tweet;

@Repository
public interface TweetsRepository extends JpaRepository<Tweet, Integer> {
	Optional<Tweet> findById(Integer id);
}
