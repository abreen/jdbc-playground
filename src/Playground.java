import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;
import java.util.stream.Stream;

public class Playground {
  public static void main(String[] args) {
    Connection cxn;
    try {
      cxn = getConnection();
    } catch (SQLException e) {
      System.err.println("failed to connect to database: " + e);
      return;
    }

    Map<String, String> allSql = getSchemaSql();
    for (Map.Entry<String, String> entry : allSql.entrySet()) {
      String fileName = entry.getKey();
      String sql = entry.getValue();

      try {
        cxn.createStatement().execute(sql);
        System.out.println("executed " + fileName);

      } catch (SQLException e) {
        System.err.println("failed to run SQL: " + e);
      }
    }

    System.out.println("ready to go");
  }

  private static Map<String, String> getSchemaSql() {
    Map<String, String> files = new HashMap<>();

    try (Stream<Path> paths = Files.walk(Path.of("data"), 1)) {
      paths
          .filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".sql"))
          .forEach(p -> {
            try {
              String content = Files.readString(p);
              files.put(p.toString(), content);
            } catch (IOException e) {
              System.err.printf("failed to read SQL schema file %s: %s%n", p, e);
            }
          });

    } catch (IOException e) {
      System.err.println("failed to get SQL schema files: " + e);
      return Collections.emptyMap();
    }

    return files;
  }

  private static Connection getConnection() throws SQLException {
    String username = System.getenv("POSTGRES_USER");
    String password = System.getenv("POSTGRES_PASSWORD");
    String databaseName = System.getenv("POSTGRES_DB");
    String hostname = System.getenv("POSTGRES_HOSTNAME");

    Objects.requireNonNull(username, "DB username is required");
    Objects.requireNonNull(password, "DB password is required");
    Objects.requireNonNull(databaseName, "DB name is required");
    Objects.requireNonNull(hostname, "DB hostname is required");

    String url = String.format("jdbc:postgresql://%s:5432/%s", hostname, databaseName);

    return DriverManager.getConnection(url, username, password);
  }
}
