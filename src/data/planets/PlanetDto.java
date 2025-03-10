package data.planets;

import data.DataTransferObject;

/**
 * An abstraction for any DTO that represents a planet
 */
public interface PlanetDto extends DataTransferObject<String> {
  String planetName();

  Double orbitalPeriod();

  Double dayLength();

  Double distanceFromSun();

  @Override
  default String getKey() {
    return planetName();
  }
}
