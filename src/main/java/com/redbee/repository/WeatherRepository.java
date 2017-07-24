package com.redbee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redbee.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

}
