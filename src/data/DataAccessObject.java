package data;

import java.util.Collection;
import java.util.Optional;

/** An abstraction representing a class that can do database access */
public interface DataAccessObject<T extends DataTransferObject<K>, K> {
  /** Fetch all DTOs from the database */
  Collection<T> fetchAll() throws DataAccessException;

  /** Fetch a DTO by its key, if it exists */
  Optional<T> fetchOne(K key) throws DataAccessException;

  /** Create a row in the database */
  void create(T dto) throws DataAccessException;

  /** Update a row in the database */
  void update(T dto) throws DataAccessException;

  /** Delete a row in the database */
  void delete(T dto) throws DataAccessException;
}
