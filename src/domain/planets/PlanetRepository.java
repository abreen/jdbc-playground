package domain.planets;

import data.DataAccessException;
import data.planets.MoonDao;
import data.planets.PlanetDao;
import domain.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

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
          .map(this::fetchAndSetMoonsForPlanet)
          .filter(Objects::nonNull)
          .toList();
    } catch (DataAccessException e) {
      System.err.println("failed to get all planets");
      return Collections.emptyList();
    }
  }

  public Optional<Planet> findByName(String planetName) {
    try {
      return planetSource.fetchOne(planetName).map(planetMapper::toModel).map(this::fetchAndSetMoonsForPlanet);
    } catch (DataAccessException e) {
      System.err.println("failed to get a planet: " + planetName);
      return Optional.empty();
    }
  }

  private Planet fetchAndSetMoonsForPlanet(Planet planet) {
    try {
      planet.setMoons(moonSource.fetchAllByPlanetName(planet.getName()).stream().map(moonMapper::toModel).toList());
      return planet;
    } catch (DataAccessException e) {
      System.err.println("failed to get moons for planet: " + planet);
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
