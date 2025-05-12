package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Weather;
import com.example.demo.model.WeatherHistory;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/weathers")
    public ResponseEntity<List<Weather>> listWeather(@RequestParam(required = false) String city,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        try {
            page = page == null ? 0 : page;
            size = size == null ? 10 : size;
            List<Weather> weathers = demoService.listWeather(city, page, size);
            if (CollectionUtils.isEmpty(weathers)) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(weathers);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/weather")
    public ResponseEntity<Weather> saveWeather(@RequestBody Weather weather) {
        try {
            weather.setDate(LocalDate.now());
            Weather savedWeather = demoService.saveWeather(weather);
            return ResponseEntity.ok(savedWeather);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/weather/{id}")
    public ResponseEntity<Weather> updateWeather(@PathVariable("id") Long id, @RequestBody Weather weather) {
        try {
            Weather existingWeather = demoService.getWeather(id);
            if (existingWeather == null) {
                return ResponseEntity.notFound().build();
            }

            existingWeather.setCity(weather.getCity());
            existingWeather.setTemp_hi(weather.getTemp_hi());
            existingWeather.setTemp_lo(weather.getTemp_lo());
            existingWeather.setPrcp(weather.getPrcp());
            existingWeather.setDate(weather.getDate());

            return ResponseEntity.ok(demoService.saveWeather(existingWeather));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/weather/{id}")
    public ResponseEntity<Boolean> deleteWeather(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(demoService.deleteWeather(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/city")
    public ResponseEntity<City> getCity(String name) {
        try {
            return ResponseEntity.ok(demoService.getCity(name));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/city")
    public ResponseEntity<City> saveCity(@RequestBody City city) {
        try {
            return ResponseEntity.ok(demoService.saveCity(city));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/city/{name}")
    public ResponseEntity<City> updateCity(@PathVariable("name") String name, @RequestBody City city) {
        try {
            City existingCity = demoService.getCity(name);
            if (existingCity == null) {
                return ResponseEntity.notFound().build();
            }

            existingCity.setName(city.getName());
            existingCity.setLocation(city.getLocation());

            return ResponseEntity.ok(demoService.saveCity(existingCity));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/weatherHistory")
    public ResponseEntity<List<WeatherHistory>> listWeatherHistory(@RequestParam(required = false) String city,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        try {
            page = page == null ? 0 : page;
            size = size == null ? 10 : size;
            List<WeatherHistory> weathers = demoService.listWeatherHistory(city, page, size);
            if (CollectionUtils.isEmpty(weathers)) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(weathers);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}