package data.planets;

import data.DataAccessException;
import data.DataAccessObject;

import java.util.Collection;

/**
 * An abstraction for any DAO that handles moon DTOs
 */
public interface MoonDao extends DataAccessObject<MoonDto, String> {
  Collection<MoonDto> fetchAllByPlanetName(String planetName) throws DataAccessException;
}
