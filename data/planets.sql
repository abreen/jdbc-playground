CREATE TABLE IF NOT EXISTS planets (
  planet_name VARCHAR(128) PRIMARY KEY,
  orbital_period_days NUMERIC(10, 2) NOT NULL,
  day_length_hours NUMERIC(10, 2) NOT NULL,
  distance_from_sun NUMERIC(10, 6) NOT NULL
);

INSERT INTO planets (
  planet_name,
  orbital_period_days,
  day_length_hours,
  distance_from_sun
) VALUES
('Mercury', 87.97, 1407.5, 0.387),
('Venus', 224.70, 5832.5, 0.723),
('Earth', 365.26, 24.0, 1.000),
('Mars', 686.98, 24.6, 1.524),
('Jupiter', 4332.59, 9.9, 5.203),
('Saturn', 10759.22, 10.7, 9.537),
('Uranus', 30688.50, 17.2, 19.191),
('Neptune', 60182.00, 16.1, 30.069)
ON CONFLICT (planet_name) DO NOTHING;

CREATE TABLE IF NOT EXISTS moons (
  moon_name VARCHAR(128) PRIMARY KEY,
  planet_name VARCHAR(128) NOT NULL REFERENCES planets(planet_name),
  orbital_period_days NUMERIC(10, 2) NOT NULL,
  diameter_km NUMERIC(10, 2) NOT NULL,
  distance_from_planet_km NUMERIC(20, 2) NOT NULL
);

INSERT INTO moons (
  moon_name,
  planet_name,
  orbital_period_days,
  diameter_km,
  distance_from_planet_km
) VALUES
('Moon', 'Earth', 27.32, 3474.8, 384400),
('Phobos', 'Mars', 0.32, 22.4, 9376),
('Deimos', 'Mars', 1.26, 12.4, 23460),
('Io', 'Jupiter', 1.77, 3643.2, 421700),
('Europa', 'Jupiter', 3.55, 3121.6, 671034),
('Ganymede', 'Jupiter', 7.15, 5268.2, 1070400),
('Callisto', 'Jupiter', 16.69, 4820.6, 1882700),
('Titan', 'Saturn', 15.95, 5149.5, 1221870),
('Enceladus', 'Saturn', 1.37, 504.2, 238040),
('Rhea', 'Saturn', 4.52, 1527.6, 527070),
('Iapetus', 'Saturn', 79.33, 1468.6, 3560840),
('Triton', 'Neptune', -5.88, 2706.8, 354760)
ON CONFLICT (moon_name) DO NOTHING;
