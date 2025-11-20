### Description

Core extension starter for [Spring](https://spring.io) apps.

[![Java CI with Maven](https://github.com/WildDev/spring-starter-core-ext/actions/workflows/maven.yml/badge.svg)](https://github.com/WildDev/spring-starter-core-ext/actions/workflows/maven.yml)

### What it carries

* `FutureCalculator`, `PastCalculator` - beans to forward datetime values.
For example, `futureCalculator.calc(LocalDateTime.now(), "20s")` is equal to `LocalDateTime.now().plusSeconds(20)`
* `MessageService` - is a customized version of Spring's `MessageSource` with fewer parameters
* `ExpiredRecordsCollector` - an accumulative interface for expired data collection
* `Queue`, `Slicer`, `Task` - interfaces to implement custom queues, slicers and tasks respectively
* `PublishingPoller` - polling based abstraction to process large datasets
* `DefaultDurationConverter` - bean to convert duration values to a different time unit. For example, `durationConverter.convert(new DurationValue(1200, TimeUnit.SECONDS), TimeUnit.MINUTES)` produces the converted value `DurationValue(value=20, timeUnit=MINUTES)`

### Get started

Build requirements:
* latest JDK and Maven

Also available in Maven central:

```xml
<dependency>
    <groupId>fun.wilddev.lib</groupId>
    <artifactId>spring-starter-core-ext</artifactId>
    <version>1.0.2.RELEASE</version>
</dependency>
```

### License

T*his project is licensed under the Apache License 2.0.*

Dependencies:

- Spring Boot (Apache 2.0)
- Lombok (MIT)

See [LICENSE](LICENSE) and [NOTICE](NOTICE) files for details.
