package com.example.demo.repository;

import com.example.demo.model.WeatherHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherHistoryRepository extends JpaRepository<WeatherHistory, Long> {

    @Query("SELECT w FROM WeatherHistory w " +
            " WHERE (:city is NULL OR :city = '' OR w.city = :city)" +
            " AND w.is_del = 0" +
            " ORDER BY w.city, w.date")
    List<WeatherHistory> listWeatherHistory(@Param("city") String city, Pageable pageable);
}