# Example on how to combine lombok data objects with validation
## Intention

### This _IS_...:
  - ... a library
### This is _NOT_...:

### Usage
```
final Fnr fnr = new Fnr("19125632753");
```
```
try {
    final Fnr fnr = new Fnr("19125632754");
} catch (Cdv11StringFormatException | DateBasedCdv11StringFormatException e) {
    log.error(e.toString());
}
```
### Future enhancements
- Random generation
- Create from localDate and counter
### Writing your own code

### Useful commands
- ```mvn versions:display-dependency-updates``` 
# Refs: