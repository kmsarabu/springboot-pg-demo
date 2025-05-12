package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "weather")
public class Weather implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private Integer temp_hi;

    private Integer temp_lo;

    private Float prcp;

    private Integer is_del;

    private LocalDate date;
}