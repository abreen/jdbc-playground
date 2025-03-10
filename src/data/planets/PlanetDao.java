package data.planets;

import data.DataAccessObject;

/**
 * An abstraction for any DAO that handles planet DTOs
 */
public interface PlanetDao extends DataAccessObject<PlanetDto, String> {

}
