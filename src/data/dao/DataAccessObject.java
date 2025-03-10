package data.dao;

import data.entity.Entity;

import java.util.Optional;
import java.util.Set;

public interface DataAccessObject<T extends Entity<K>, K> {
  /** Get all entities of the desired type from the database */
  Set<T> getAll() throws DataAccessException;

  /** Get the entity from the database by its key, if it exists */
  Optional<T> getOne(K key) throws DataAccessException;

  /** Create this entity in the database */
  void create(T entity) throws DataAccessException;

  /** Update this entity in the database */
  void update(T entity) throws DataAccessException;

  /** Delete this entity from the database */
  void delete(T entity) throws DataAccessException;

  /** Delete an entity by its key */
  void delete(K key) throws DataAccessException;
}
