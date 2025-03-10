package data.planets.postgres;

import data.DataAccessException;
import data.planets.PlanetDao;
import data.planets.PlanetDto;
import data.postgres.PostgresDao;

import java.sql.*;
import java.util.*;

/** A Postgres-specific DAO for handling planet DTOs */
public class PostgresPlanetDao extends PostgresDao<PlanetDto, String> implements PlanetDao {
  private final Connection cxn;

  public PostgresPlanetDao(Connection cxn) {
    this.cxn = cxn;
  }

  public Collection<PlanetDto> fetchAll() throws DataAccessException {
    String sql = """
        SELECT
          planet_name,
          orbital_period_days,
          day_length_hours,
          distance_from_sun
        FROM planets;
        """;

    try (Statement stmt = cxn.createStatement()) {
      ResultSet results = stmt.executeQuery(sql);
      return mapResultSet(results);
    } catch (SQLException e) {
      throw mapToException(e);
    }
  }

  @Override
  public Optional<PlanetDto> fetchOne(String key) throws DataAccessException {
    String sql = """
        SELECT
          planet_name,
          orbital_period_days,
          day_length_hours,
          distance_from_sun
        FROM planets
        WHERE planet_name = ?;
        """;

    try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
      stmt.setString(1, key);

      var results = stmt.executeQuery();
      var planets = mapResultSet(results);

      if (planets.size() > 1) {
        throw new DataAccessException("multiple results");
      } else if (planets.isEmpty()) {
        return Optional.empty();
      } else {
        return Optional.of(planets.getFirst());
      }
    } catch (SQLException e) {
      throw mapToException(e);
    }
  }

  @Override
  public void create(PlanetDto dto) throws DataAccessException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(PlanetDto dto) throws DataAccessException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(PlanetDto dto) throws DataAccessException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<PlanetDto> mapResultSet(ResultSet results) throws DataAccessException {
    List<PlanetDto> dtos = new ArrayList<>();

    try {
      while (results.next()) {
        dtos.add(new PostgresPlanetDto(
            results.getString("planet_name"),
            results.getDouble("orbital_period_days"),
            results.getDouble("day_length_hours"),
            results.getDouble("distance_from_sun")
        ));
      }
    } catch (SQLException e) {
      throw mapToException(e);
    }

    return dtos;
  }
}
