# Spring Jdbc tests

This project aims to compare different approaches to load and store (batch) data in spring.

This is just a simple comparison, focusing only on a single table, see model module.

I focused for now on an older version of JDK and Spring, because a related project has that version.
I may compare later on more recent versions

## Performance Tests

Here are the results on my setup. The results may differ on your setup.

* Database: Local SqlServer Docker container (mcr.microsoft.com/mssql/server:2019-latest)
* Host: Windows 11 Pro 25H2
  * CPU: AMD Ryzen 7 9800X3D 8-Core Processor (4.70 GHz)
  * RAM: (64.0 GB)
* JDK: Oracle OpenJDK 17.0.20
* IDE: IntelliJ IDEA 2026.2.2

For now, I only focused on execution time. Later I will also compare CPU and RAM usage.

### Reading

Fetching 1'200'000 contact records.

#### JPA Simple

| findAll | request \[s\] | transaction \[s\] |
|---------|---------------|-------------------|
| jpa     | 9.576         | 6.791             |
| records | 7.265         | 4.653             | 
| native  | 7.940         | 5.371             |

#### JDBC Template

| findAll | request \[s\] | transaction \[s\] |
|---------|---------------|-------------------|
|         | 5.598         | 3.116             |

### Syncing

Inserting 1'200'000 contact records.

#### JPA Simple

| statements    | time \[min\] |
|---------------|--------------|
| insert*       | 4 \[s\]      |
| insert        | 12.966       |
| delete+insert | 25.631       |

\* without `repository.flush()`, transaction was longer

#### JPA Batched

| batch size | insert \[s\] | delete+insert \[s\] |
|------------|--------------|---------------------|
| 50         | 69.962       | 70.557              |
| 100        | 47.312       | 47.603              |
| 200        | 35.803       | 35.960              |

delete round 730 ms

#### JDBC Template

| batch size | insert \[s\] | delete+insert \[s\] |
|------------|--------------|---------------------|
| 50         | 25.425       | 25.545              |
| 100        | 19.873       | 20.071              |
| 200        | 16.539       | 17.203              |

delete around 750 ms

#### JDBC Template Named

| type            | batch size | insert \[s\] | delete+insert \[s\] |
|-----------------|------------|--------------|---------------------|
| ParameterSource | 50         | 59.653       | 60.175              |
| ParameterSource | 100        | 38.113       | 38.691              |
| ParameterSource | 200        | 27.270       | 27.618              |

delete around 780 ms

#### JDBC Operations

| type            | batch size | insert \[s\] | delete+insert \[s\] |
|-----------------|------------|--------------|---------------------|
| ParameterSource | 50         | 59.387       | 59.525              |
| ParameterSource | 100        | 37.789       | 38.026              |
| ParameterSource | 200        | 26.740       | 27.029              |

delete around 780 ms