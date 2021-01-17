package net.barakiroth.lombokwithvalidations.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.barakiroth.lombokwithvalidations.validation.CategorizedValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.validation.IValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.ValidationStrategyCollector;
import net.barakiroth.lombokwithvalidations.validation.Validator;

import java.util.HashSet;
import java.util.Set;

@Builder(
        setterPrefix = "with",
        builderClassName = "DataObjectBuilder",
        access = AccessLevel.PUBLIC,
        buildMethodName = "internalBuild",
        builderMethodName = "internalBuilder")
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
@ToString
public class MyDataObject {

    private final int i;
    private final String s;

    public static DataObjectBuilder builder() {
        return MyDataObject.internalBuilder(new HashSet<>());
    }

    @SafeVarargs
    public static DataObjectBuilder builder(final CategorizedValidationStrategy<MyDataObject>... categorizedValidationStrategies) {
        return MyDataObject.internalBuilder(ValidationStrategyCollector.collect(categorizedValidationStrategies));
    }
    // AND/OR:
    /**
     * All provided strategies will be supplied with an error severity.
     * @param validationStrategies
     * @return
     */
    @SafeVarargs
    public static DataObjectBuilder builder(final IValidationStrategy<MyDataObject>... validationStrategies) {
        return MyDataObject.internalBuilder(ValidationStrategyCollector.collect(validationStrategies));
    }

    private static DataObjectBuilder internalBuilder(final Set<CategorizedValidationStrategy<MyDataObject>> uniqueCategorizedValidationStrategies) {
        final DataObjectBuilder dataObjectBuilder = MyDataObject.internalBuilder();
        dataObjectBuilder.setUniqueCategorizedValidationStrategies(uniqueCategorizedValidationStrategies);

        return dataObjectBuilder;
    }

    private static DataObjectBuilder internalBuilder() {
        return new DataObjectBuilder();
    }

    public static class DataObjectBuilder {

        private Set<CategorizedValidationStrategy<MyDataObject>> uniqueCategorizedValidationStrategies;

        private DataObjectBuilder() {}

        public MyDataObject build() {
            return this.build(null);
        }

        public MyDataObject build(final Set<ConstraintViolation<MyDataObject>> constraintViolations) {

            // First construct an unvalidated instance for the validator
            // to be able to get the builder's values because the builder
            // does not provide getters of its fields.
            return Validator.validate(internalBuild(), this.uniqueCategorizedValidationStrategies, constraintViolations);
        }

        // By letting lombok generate this method, it becomes public
        // Please notify me if I am wrong.
        private MyDataObject internalBuild() {
            return new MyDataObject(this.i, this.s);
        }

        private void setUniqueCategorizedValidationStrategies(final Set<CategorizedValidationStrategy<MyDataObject>> uniqueCategorizedValidationStrategies) {
            this.uniqueCategorizedValidationStrategies = uniqueCategorizedValidationStrategies;
        }
    }
}
