package com.example.demo.repository;

import com.example.demo.model.Weather;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query("SELECT w FROM Weather w " +
            " WHERE (:city is NULL OR :city = '' OR w.city = :city)" +
            " AND w.is_del = 0")
    List<Weather> listWeather(@Param("city") String city, Pageable pageable);
}