# Example on how to combine lombok data objects with validation
## Intention
- ... to use lombok for the boiler plate ornamentation of an immutable data object such as a builder, getters etc.
- ... to instantiate only valid instances of an immutable data object
- ... to make it impossible to create an instance of an immutable data object that does not satisfy a set of constraints (i.e. You do not and cannot first create an object and then check it for validity)
- ... to validate the same object with different validation strategies depending on the needs of a specific use case
### This _IS_...:
  - ... a source of inspiration
### This is _NOT_...:
- ... a library
### Usage
```
final MyDataObject myDataObject =
    MyDataObject
        .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
        .withS("abcd")
        .withI(7)
        .build();
```
```
try {
    MyDataObject
        .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
        .build();
} catch (ConstraintViolationException e) {
    log.error(e.toString());
}
```

### Writing your own code
1. Write your own data object class by copying and tweeking ```MyDataObject```. (NB! Do not forget the ```internalBuild()``` method!)
0. Write your own data object constraint violation strategy by copying and tweeking ```MyDataObjectValidationStrategy```
### Useful commands
- ```mvn versions:display-dependency-updates``` 
# Refs:
[Be Careful With Lombok](https://levelup.gitconnected .com/be-careful-with-lombok-2e2edfc01110)