package net.barakiroth.lombokwithvalidations.supersub;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.barakiroth.lombokwithvalidations.validation.IValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.CategorizedValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.ValidationStrategyCollector;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.validation.Validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter(AccessLevel.PUBLIC)
@SuperBuilder(setterPrefix = "with")
public class MyDataObjectSub extends MyDataObjectSuper {

    private final String s;

    public static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> builder() {
        return MyDataObjectSub.builder(new HashSet<>());
    }

    public static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> builder(final CategorizedValidationStrategy<MyDataObjectSub>... categorizedValidationStrategies) {
        return MyDataObjectSub.builder(ValidationStrategyCollector.collect(categorizedValidationStrategies));
    }

    /**
     * All provided strategies will be supplied with an error severity.
     *
     * @param validationStrategies
     * @return
     */
    public static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> builder(final IValidationStrategy<MyDataObjectSub>... validationStrategies) {
        return MyDataObjectSub.builder(ValidationStrategyCollector.collect(validationStrategies));
    }

    private static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> builder(final Set<CategorizedValidationStrategy<MyDataObjectSub>> uniqueCategorizedValidationStrategies) {

        final MyDataObjectSub.MyDataObjectSubBuilderImpl dataObjectBuilder = new MyDataObjectSubBuilderImpl();
        dataObjectBuilder.setUniqueCategorizedValidationStrategies(uniqueCategorizedValidationStrategies);

        return dataObjectBuilder;
    }
    // -----------------------------------------------------------------------------------------------------------------
    public static abstract class MyDataObjectSubBuilder<C extends MyDataObjectSub, B extends MyDataObjectSubBuilder<C, B>> extends MyDataObjectSuperBuilder<C, B> {
        public abstract C build(final Collection<ConstraintViolation<MyDataObjectSub>> constraintViolations);
    }
    // -----------------------------------------------------------------------------------------------------------------
    private static final class MyDataObjectSubBuilderImpl extends MyDataObjectSubBuilder<MyDataObjectSub, MyDataObjectSubBuilderImpl> {

        private Set<CategorizedValidationStrategy<MyDataObjectSub>> uniqueCategorizedValidationStrategies;

        private void setUniqueCategorizedValidationStrategies(final Set<CategorizedValidationStrategy<MyDataObjectSub>> uniqueCategorizedValidationStrategies) {
            this.uniqueCategorizedValidationStrategies = uniqueCategorizedValidationStrategies;
        }

        public MyDataObjectSub build() {
            return build(null);
        }

        public MyDataObjectSub build(final Collection<ConstraintViolation<MyDataObjectSub>> constraintViolations) {
            return Validator.validate(new MyDataObjectSub(this), this.uniqueCategorizedValidationStrategies, constraintViolations);
        }
    }
    // -----------------------------------------------------------------------------------------------------------------
}