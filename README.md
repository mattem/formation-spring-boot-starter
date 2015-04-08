# formation-spring-boot-starter

[![Build Status](https://travis-ci.org/mattem/formation-spring-boot-starter.svg?branch=master)](https://travis-ci.org/mattem/formation-spring-boot-starter)

> Formation is currently under development, so not all features listed may not work as advertised 

### What is Formation?
Formation is a Spring Boot starter that helps build dynamic forms for your web front end. It will find and analyse your projects Pojo, or any object you point it to, and provide a form description that can be passed to Formations front end processing. 

### Sounds good, but I bet I have to annotate everything...
No, not necessarily. Formation tries to make good assumptions about how to generate your form, based on what it's found in the Pojo, however, for finer grained control there are annotations that can be added at class and method level. 

## Quick Start

### Spring Boot Project
Simply add the `@EnableFormation` annotation to your configuration class. 

Then include an object to be scanned

```java
@FormationInclude
```

Or ignore a field on an object

```java
@FormationExclude
```

Refer to the [formation-spring-boot-starter Wiki](https://github.com/mattem/formation-spring-boot-starter/wiki) for more options and examples.

### Frontend UI
_Formation_ currently has a directive for projects using AngularJS. To use this, simply pass the name of the java object to the _formation_ directive.

```html
<formation domain="'MyJavaObject'"></formation>
```
Head over to the [ng-formation](https://github.com/mattem/ng-formation) project to see more info on how to use the _formation_ directive

## License

The MIT License (MIT)
