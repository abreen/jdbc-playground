package data.postgres;

import data.DataAccessException;
import data.DataAccessObject;
import data.DataTransferObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class PostgresDao<T extends DataTransferObject<K>, K>
    implements DataAccessObject<T, K> {
  public DataAccessException mapToException(SQLException e) {
    // TODO maybe create subclasses of DataAccessException based on Postgres
    return new DataAccessException(e);
  }

  public abstract List<T> mapResultSet(ResultSet results) throws DataAccessException;
}
