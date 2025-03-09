# jdbc-review

A demo Java app for playing with JDBC.

- No Java build tools
- A multi-stage `Dockerfile` that builds the app
- A `docker-compose.yml` that uses Postgres 17.4
- A copy of PostgreSQL JDBC 42.7.5 (the database driver)

On startup, the app runs the SQL in `data/` to seed the database.

```sql
SELECT m.*
FROM planets p
JOIN moons m ON p.planet_name = m.planet_name
WHERE p.distance_from_sun > 1
ORDER BY p.distance_from_sun DESC;
```