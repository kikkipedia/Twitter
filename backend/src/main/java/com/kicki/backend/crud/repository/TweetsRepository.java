package com.kicki.backend.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kicki.backend.crud.model.Tweets;

@Repository
public interface TweetsRepository extends JpaRepository<Tweets, Integer> {
//	Page<Tweets> findByUserId(Long tweetsId, Pageable pageable);
//	Optional<Tweets> findByIdAndUserId(Long id, Long userId);
}
