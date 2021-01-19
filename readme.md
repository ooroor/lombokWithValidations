# Example on how to combine lombok data objects with validation
## Intention
- ... to use lombok ornamentation for the boiler plate of an immutable data object using a builder, getters etc.
- ... to be able to instantiate only valid instances of an immutable data object
- ... to be able to validate the same object with different validation strategies depending on the needs of a specific use case
### This _IS_...:
  - ... a source of inspiration and for copy and paste
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
    final MyDataObject myDataObject =
        MyDataObject
            .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
            .build();
} catch (ConstraintViolationException e) {
    log.error(e.toString());
}
```
```
final Set<ConstraintViolation<MyDataObject>> constraintViolations = new HashSet<>();
MyDataObject.builder(
        CategorizedValidationStrategy
                .ofWarn(MyDataObjectValidationStrategy.I_IS_7),
        CategorizedValidationStrategy
                .ofWarn(MyDataObjectValidationStrategy.S_IS_4_LONG),
        CategorizedValidationStrategy
                .ofWarn(MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG)
)
        .withS("1bcdefgh9")
        .build(constraintViolations);
if (constraintViolations.size() > 0) {
    log.warn(constraintViolations.toString());
}
```
### Future enhancements
- Less code in the data object
### Writing your own code
1. Write your own data object class by copying and tweeking ```MyDataObject```. (NB! Implement the ```internalBuild()``` method carefully.)
0. Write your own data object constraint violation strategy by copying and tweeking ```MyDataObjectValidationStrategy```
### Useful commands
- ```mvn versions:display-dependency-updates``` 
# Refs:
[Be Careful With Lombok](https://levelup.gitconnected .com/be-careful-with-lombok-2e2edfc01110)