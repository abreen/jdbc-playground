package domain.planets;

import data.planets.PlanetDto;
import domain.Mapper;

public class PlanetDtoMapper implements Mapper<PlanetDto, Planet> {

  @Override
  public Planet toModel(PlanetDto dto) {
    return new Planet(
        dto.planetName(),
        dto.orbitalPeriod(),
        dto.dayLength(),
        dto.distanceFromSun()
    );
  }
}
