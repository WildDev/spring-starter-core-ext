### Description

Core extension starter for [Spring](https://spring.io) apps.

[![Java CI with Maven](https://github.com/WildDev/spring-starter-core-ext/actions/workflows/maven.yml/badge.svg)](https://github.com/WildDev/spring-starter-core-ext/actions/workflows/maven.yml)

### What it carries

* `FutureCalculator`, `PastCalculator` - beans to forward datetime values.
For example, `futureCalculator.calc(LocalDateTime.now(), "20s")` is equal to `LocalDateTime.now().plusSeconds(20)`
* `MessageService` - is a customized version of Spring's `MessageSource` with fewer parameters
* `ExpiredRecordsCollector` - an accumulative interface for expired data collection
