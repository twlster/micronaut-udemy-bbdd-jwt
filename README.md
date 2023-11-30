## Micronaut 4.2.0 Documentation

- [User Guide](https://docs.micronaut.io/4.2.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.2.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.2.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)

---
## Before starting remember to create a postgres DB with these parameters:

POSTGRES_PASSWORD=secret \
POSTGRES_DB=mn-stock-broker

### In case of using Docker, use this command:

docker run --name my-postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=mn-stock-broker -p 5432:5432 -d postgres:12.4

## Feature security-jwt documentation

- [Micronaut Security JWT documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html)


## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)


## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


