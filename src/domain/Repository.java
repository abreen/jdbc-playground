package domain;

import java.util.Collection;

public interface Repository<T extends Model> {
  Collection<T> findAll();
  void save(T model);
  void delete(T model);
}
