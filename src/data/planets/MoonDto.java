package data.planets;

import data.DataTransferObject;

public interface MoonDto extends DataTransferObject<String> {
  String moonName();

  String planetName();

  Double orbitalPeriod();

  Double diameter();

  Double distanceFromPlanet();

  @Override
  default String getKey() {
    return moonName();
  }
}
