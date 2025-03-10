package ui;

import domain.planets.Planet;
import domain.planets.PlanetRepository;

import java.io.InputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;

public class PlanetaryDatabase {
  private final PlanetRepository repo;

  public PlanetaryDatabase(PlanetRepository repo) {
    this.repo = repo;
  }

  public void takeUserInput(InputStream in) {
    Scanner s = new Scanner(in);

    Collection<Planet> planets = repo.findAll();
    if (planets == null) {
      System.err.println("failed to get all planets");
      return;
    }

    var fmt = new PlanetFormatter();

    planets
        .stream()
        .sorted(Comparator.comparing(Planet::getDistanceFromSun))
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
