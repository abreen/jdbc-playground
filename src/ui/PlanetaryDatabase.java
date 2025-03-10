package ui;

import data.dao.DataAccessException;
import domain.planets.Planet;
import domain.planets.PlanetDao;

import java.io.InputStream;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;

public class PlanetaryDatabase {
  private PlanetDao planetDao;

  public PlanetaryDatabase(PlanetDao planetDao) {
    this.planetDao = planetDao;
  }

  public void takeUserInput(InputStream in) {
    Scanner s = new Scanner(in);

    Set<Planet> planets;
    try {
      planets = planetDao.getAll();
    } catch (DataAccessException e) {
      System.err.println("failed to fetch planets");
      return;
    }

    var fmt = new PlanetFormatter();

    planets
        .stream()
        .sorted(Comparator.comparing(Planet::distanceFromSun))
        .map(fmt)
        .forEach(System.out::println);

    System.out.print("planet or moon name (q to quit): ");

    while (s.hasNextLine()) {
      String line = s.nextLine();
      if (line.trim().equalsIgnoreCase("q")) {
        break;
      }

      System.out.print("planet or moon name: ");
    }

    System.out.println("goodbye");
  }
}
