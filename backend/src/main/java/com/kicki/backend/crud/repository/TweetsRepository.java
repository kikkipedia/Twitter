package com.kicki.backend.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kicki.backend.crud.model.Tweet;

@Repository
public interface TweetsRepository extends JpaRepository<Tweet, Integer> {
//	Page<Tweets> findByUserId(Long tweetsId, Pageable pageable);
//	Optional<Tweets> findByIdAndUserId(Integer id, Integer userId);
//	List<Tweets> findByUserId(Integer userId);
}
