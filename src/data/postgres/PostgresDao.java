package data.postgres;

import data.dao.DataAccessException;
import data.dao.DataAccessObject;
import data.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class PostgresDao<T extends Entity<K>, K>
    implements DataAccessObject<T, K>
{
  public DataAccessException mapToException(SQLException e) {
    // TODO maybe create subclasses of DataAccessException based on Postgres
    return new DataAccessException(e);
  }

  public abstract List<T> mapResultSet(ResultSet results) throws DataAccessException;
}
