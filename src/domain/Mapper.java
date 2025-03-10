package domain;

import data.DataTransferObject;

public interface Mapper<D extends DataTransferObject<?>, T extends Model> {
  T toModel(D dto);
}
