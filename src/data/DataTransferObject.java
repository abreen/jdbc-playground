package data;

/**
 * An abstraction representing data coming from/going to a database
 */
public interface DataTransferObject<K> {
  K getKey();
}
