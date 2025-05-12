package com.example.demo.model;

import com.example.demo.PGpointType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.postgresql.geometric.PGpoint;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@TypeDef(name = "point", typeClass = PGpointType.class)
@Table(name = "cities")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Type(type = "point")
    private PGpoint location;
}