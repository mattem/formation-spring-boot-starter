# Formation

### What is Formation?
Formation is a Spring Boot starter that helps build dynamic forms for your web front end. It will find and analyse your projects Pojo, or any object you point it to, and provide a form description that can be passed to Formations front end processing. 

### Sounds good, but I bet I have to annotate everything...
No, not necessarily. Formation tries to make good assumptions about how to generate your form, based on what it's found in the Pojo, however, for finer grained control there are annotations that can be added at class and method level. 

## Usage

### Spring Boot Project
Simply add the `@EnableFormation` annotation to your configuration class. 

#### Options
##### Scan a specific package or list of packages 

```java
@EnableFormation(basePackages={"com.bob.domain", "com.bob.objects"})
```

##### Include a Pojo to be scanned

```java
@FormationInclude
```

By default, Pojos have to include this annotation at the class level to be considered, however this behaviour can be customised by either creating a bean in your application context that either extends ```AbstractFormationCandidateResolver``` or implements ```FormationCandidateResolver```. The default implementation used by _Formation_ is the ```AnnotationDrivenCandidateResolver```  

## Credits

ReactJS Form Building: [tcomb-form](https://github.com/gcanti/tcomb-form)

## License

The MIT License (MIT)