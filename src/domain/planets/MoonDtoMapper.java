package domain.planets;

import data.planets.MoonDto;
import domain.Mapper;

public class MoonDtoMapper implements Mapper<MoonDto, Moon> {

  @Override
  public Moon toModel(MoonDto dto) {
    return new Moon(
        dto.moonName(),
        dto.orbitalPeriod(),
        dto.diameter(),
        dto.distanceFromPlanet()
    );
  }
}
