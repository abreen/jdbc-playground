package data.planets.postgres;

import data.DataAccessException;
import data.planets.MoonDao;
import data.planets.MoonDto;
import data.postgres.PostgresDao;

import java.sql.*;
import java.util.*;

/**
 * A Postgres-specific DAO for handling moon DTOs
 */
public class PostgresMoonDao extends PostgresDao<MoonDto, String> implements MoonDao {
  private final Connection cxn;

  public PostgresMoonDao(Connection cxn) {
    this.cxn = cxn;
  }

  public Collection<MoonDto> fetchAll() throws DataAccessException {
    String sql = """
        SELECT
          moon_name,
          planet_name,
          orbital_period_days,
          diameter_km,
          distance_from_planet_km
        FROM moons;
        """;

    try (Statement stmt = cxn.createStatement()) {
      ResultSet results = stmt.executeQuery(sql);
      return mapResultSet(results);
    } catch (SQLException e) {
      throw mapToException(e);
    }
  }

  @Override
  public Collection<MoonDto> fetchAllByPlanetName(String planetName) throws DataAccessException {
    String sql = """
        SELECT
          moon_name,
          planet_name,
          orbital_period_days,
          diameter_km,
          distance_from_planet_km
        FROM moons
        WHERE planet_name = ?;
        """;

    try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
      stmt.setString(1, planetName);

      var results = stmt.executeQuery();
      return mapResultSet(results);
    } catch (SQLException e) {
      throw mapToException(e);
    }
  }

  @Override
  public Optional<MoonDto> fetchOne(String key) throws DataAccessException {
    String sql = """
        SELECT
          moon_name,
          planet_name,
          orbital_period_days,
          diameter_km,
          distance_from_planet_km
        FROM moons
        WHERE moon_name = ?;
        """;

    try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
      stmt.setString(1, key);

      ResultSet results = stmt.executeQuery();
      var moons = mapResultSet(results);

      if (moons.size() > 1) {
        throw new DataAccessException("multiple results");
      } else if (moons.isEmpty()) {
        return Optional.empty();
      } else {
        return Optional.of(moons.getFirst());
      }
    } catch (SQLException e) {
      throw mapToException(e);
    }
  }

  @Override
  public void create(MoonDto dto) throws DataAccessException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(MoonDto dto) throws DataAccessException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(MoonDto dto) throws DataAccessException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<MoonDto> mapResultSet(ResultSet results) throws DataAccessException {
    List<MoonDto> dtos = new ArrayList<>();

    try {
      while (results.next()) {
        dtos.add(new PostgresMoonDto(
            results.getString("moon_name"),
            results.getString("planet_name"),
            results.getDouble("orbital_period_days"),
            results.getDouble("diameter_km"),
            results.getDouble("distance_from_planet_km")
        ));
      }
    } catch (SQLException e) {
      throw mapToException(e);
    }

    return dtos;
  }
}

