package com.redbee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redbee.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	List<Location> findByBoardName(String name);
}
