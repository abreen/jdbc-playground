package domain.planets;

import data.DataAccessException;
import data.planets.MoonDao;
import data.planets.PlanetDao;
import domain.Repository;

import java.util.Collection;
import java.util.Objects;

public class PlanetRepository implements Repository<Planet> {

  private final PlanetDao planetSource;

  private final MoonDao moonSource;

  private final PlanetDtoMapper planetMapper;

  private final MoonDtoMapper moonMapper;

  public PlanetRepository(
      PlanetDao planetSource,
      MoonDao moonSource,
      PlanetDtoMapper planetMapper,
      MoonDtoMapper moonMapper
  ) {
    this.planetSource = planetSource;
    this.moonSource = moonSource;
    this.planetMapper = planetMapper;
    this.moonMapper = moonMapper;
  }

  @Override
  public Collection<Planet> findAll() {
    try {
      return planetSource.fetchAll().stream()
          .map(planetMapper::toModel)
          .map(p -> {
            try {
              p.setMoons(
                  moonSource.fetchAllByPlanetName(p.getName())
                      .stream()
                      .map(moonMapper::toModel)
                      .toList()
              );
              return p;
            } catch (DataAccessException e) {
              System.err.println("failed to get moons for planet: " + p);
              System.err.println(e);
              return null;
            }
          })
          .filter(Objects::nonNull)
          .toList();
    } catch (DataAccessException e) {
      System.err.println("failed to get all planets");
      System.err.println(e);
      return null;
    }
  }

  @Override
  public void save(Planet model) {
    // TODO persist all the planet's moons
    // TODO persist the planet
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(Planet model) {
    // TODO delete the moons for this planet
    // TODO delete the planet
    throw new UnsupportedOperationException();
  }
}
