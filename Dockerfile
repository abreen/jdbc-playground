# A multi-stage build for a demo Java app with no build tool

# First stage: need a JDK to build Java bytecode
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

COPY ./src ./src

# Need to use find since globbing didn't work (e.g., javac "src/**/*.java")
RUN find ./src -name "*.java" > sources.txt && javac -d ./bin @sources.txt

# Second stage: just need a JRE to run bytecode
FROM eclipse-temurin:21-jre-jammy AS runtime

WORKDIR /app

# Copy bytecode from the build stage
COPY --from=builder /app/bin ./bin

# Copy SQL data files into image
RUN mkdir ./data
COPY ./data/*.sql ./data

# Copy Postgres DB driver from this repo (instead of using Maven or Gradle)
COPY ./postgresql-42.7.5.jar ./postgres.jar

CMD ["java", "-cp", "postgres.jar:./bin", "Playground"]