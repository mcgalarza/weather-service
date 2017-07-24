package com.redbee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redbee.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	Board findByName(String name);
}
