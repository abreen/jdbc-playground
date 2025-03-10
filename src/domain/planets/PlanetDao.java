package domain.planets;

import data.dao.DataAccessException;
import data.dao.DataAccessObject;
import data.postgres.PostgresDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PlanetDao extends PostgresDao<Planet, String> implements DataAccessObject<Planet, String> {
  private final Connection cxn;

  public PlanetDao(Connection cxn) {
    this.cxn = cxn;
  }

  @Override
  public Set<Planet> getAll() throws DataAccessException {
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
      List<Planet> allPlanets = mapResultSet(results);
      return new HashSet<>(allPlanets);
    } catch (SQLException e) {
      throw mapToException(e);
    }
  }

  @Override
  public Optional<Planet> getOne(String key) throws DataAccessException {
    return Optional.empty();
  }

  @Override
  public void create(Planet entity) throws DataAccessException {

  }

  @Override
  public void update(Planet entity) throws DataAccessException {

  }

  @Override
  public void delete(Planet entity) throws DataAccessException {

  }

  @Override
  public void delete(String key) throws DataAccessException {

  }

  @Override
  public List<Planet> mapResultSet(ResultSet results) throws DataAccessException {
    var planets = new ArrayList<Planet>();

    try {
      while (results.next()) {
        planets.add(new Planet(
            results.getString("planet_name"),
            results.getDouble("orbital_period_days"),
            results.getDouble("day_length_hours"),
            results.getDouble("distance_from_sun")
        ));
      }
    } catch (SQLException e) {
      throw mapToException(e);
    }

    return planets;
  }
}
