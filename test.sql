CREATE DATABASE testdb;
CREATE USER newuser PASSWORD 'newuser' ;

\c testdb

CREATE TABLE IF NOT EXISTS weather (
    id              serial primary key,
    city            varchar(80),
    temp_lo         int,           -- low temperature
    temp_hi         int,           -- high temperature
    prcp            real,          -- precipitation
    date            date,
    is_del          int default 0
);
ALTER TABLE weather OWNER TO newuser;

CREATE TABLE IF NOT EXISTS cities (
    id              serial primary key,
    name            varchar(80),
    location        point
);
ALTER TABLE cities OWNER TO newuser;

INSERT INTO weather (city, temp_lo, temp_hi, prcp, date) VALUES ('San Francisco', 43, 57, 0.0, '1994-11-29');
INSERT INTO weather (city, temp_lo, temp_hi, prcp, date) VALUES ('San Francisco', 46, 50, 0.25, '1994-11-27');
INSERT INTO weather (city, temp_lo, temp_hi, prcp, date) VALUES ('Hayward', 54, 37, 0.0, '1994-11-29');
INSERT INTO cities (name, location) VALUES ('San Francisco', '(-194.0, 53.0)');
INSERT INTO cities (name, location) VALUES ('Austin', '(30.266666, -97.733330)');

CREATE TABLE IF NOT EXISTS weatherhistory (
    id              serial primary key,
    city            varchar(80),
    temp_lo         int,           -- low temperature
    temp_hi         int,           -- high temperature
    prcp            real,          -- precipitation
    date            date,
    is_del          int default 0
);
ALTER TABLE weatherhistory OWNER TO newuser;

INSERT INTO weatherhistory(city, temp_lo, temp_hi, prcp, date)
SELECT 'Austin', floor(random() * (50-1+1) + 1)::int, floor(random() * (100-50+1) + 50)::int, random()::numeric(10, 2), clock_timestamp()
FROM generate_series(1, 1000000) t;

VACUUM ANALYZE weather;
VACUUM ANALYZE cities;
VACUUM ANALYZE weatherhistory;

