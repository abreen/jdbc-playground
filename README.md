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

## SQL snippets

```sql
SELECT m.*
FROM planets p
JOIN moons m ON p.planet_name = m.planet_name
WHERE p.distance_from_sun > 1
ORDER BY p.distance_from_sun DESC;
```
