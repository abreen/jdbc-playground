package ui;

import domain.planets.Planet;
import domain.planets.PlanetRepository;
import ui.fmt.DetailedPlanetFormatter;
import ui.fmt.SimplePlanetFormatter;

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

    var simpleFmt = new SimplePlanetFormatter();
    planets.stream().map(simpleFmt).forEach(System.out::println);

    System.out.print("planet or moon name (q to quit): ");

    var detailedFmt = new DetailedPlanetFormatter();

    while (s.hasNextLine()) {
      String line = s.nextLine().trim();
      if (line.equalsIgnoreCase("q")) {
        break;
      }

      System.out.println(repo.findByName(line).map(detailedFmt).orElse("no results"));

      System.out.print("planet or moon name: ");
    }

    System.out.println("goodbye");
  }
}
