# Example on how to combine lombok data objects with validation
## Intention
- ... to use lombok for the boiler plate ornamentation of an immutable data object such as a builder, getters etc.
- ... to instantiate only valid instances of an immutable data object
- ... to make it impossible to create an instance of an immutable data object that does not satisfy a set of constraints (i.e. You do not and cannot first create an object and then check it for validity)
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
    final MyDataObject myDataObject2 =
            MyDataObject
                    .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                    .build();
} catch (MyDataObjectConstraintViolationException e) {
    e.printStackTrace();
}
```
### Writing your own code
1. Write your own data object class by copying and tweeking ```MyDataObject```
0. Write your own data object constraint violation strategy enum by copying and tweeking ```MyDataObjectValidationStrategy```
0. Write your own data object constraint violation exception by copying and tweeking ```MyDataObjectConstraintViolationException``` 
# Refs:
[Be Careful With Lombok](https://levelup.gitconnected .com/be-careful-with-lombok-2e2edfc01110)