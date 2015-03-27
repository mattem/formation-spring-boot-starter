# Formation

[![Build Status](https://travis-ci.org/mattem/Formation.svg?branch=master)](https://travis-ci.org/mattem/Formation)

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

### Frontend UI
_Formation_ currently has a UI helper for projects using ReactJS. To use this, simply pass the name of the java object to the _Formation_ class.

```html
<Formation domain={this.state.domainName}/>
```

An AngularJS helper is _coming soon..._

Refer to the [Formation Wiki](https://github.com/mattem/Formation/wiki) for more options and examples.
 

## Credits

ReactJS Form Building: [tcomb-form](https://github.com/gcanti/tcomb-form)

## License

The MIT License (MIT)