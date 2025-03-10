# JDBC Playground

A simple Java app for playing with JDBC.

- No Java build tools
- A multi-stage `Dockerfile` that builds the app
- A `docker-compose.yml` that uses Postgres 17.4
- A copy of PostgreSQL JDBC 42.7.5 (the database driver)

On startup, the app runs the SQL in `data/` to seed the database.

## How to run

There are at least three ways to run this project:

1. Do `docker compose up` from the command line and use `docker attach jdbc-playground`.
2. In IntelliJ IDEA, use the Run Configuration called "Docker Compose". You will need to use `docker attach` to
   interact with the Java app.
3. In IntelliJ IDEA, if the Postgres DB is already accessible over `localhost:5432`, use the Run Configuration
   called "Build and run" (useful if you want to use IntelliJ's debugger).

## Transcript

Here is a sample interaction:

```text
executed data/usa.sql
executed data/planets.sql
ready to go
Mercury (0.39 AU)
Venus (0.72 AU)
Earth (1.00 AU)
Mars (1.52 AU)
Jupiter (5.20 AU)
Saturn (9.54 AU)
Uranus (19.19 AU)
Neptune (30.07 AU)
planet or moon name (q to quit): Mars
Planet name: Mars
	Distance from the Sun: 227,987,155.40 km
	Orbital period: 686.98 days
	Length of day: 24.60 hours
	Moons:
		Moon name: Phobos
		Distance from planet: 9,376.00 km
		Orbital period: 0.32 days
		Diameter: 22.40 km

		Moon name: Deimos
		Distance from planet: 23,460.00 km
		Orbital period: 1.26 days
		Diameter: 12.40 km
planet or moon name: q
goodbye
```

## SQL snippets

```sql
SELECT m.*
FROM planets p
JOIN moons m ON p.planet_name = m.planet_name
WHERE p.distance_from_sun > 1
ORDER BY p.distance_from_sun DESC;
```
