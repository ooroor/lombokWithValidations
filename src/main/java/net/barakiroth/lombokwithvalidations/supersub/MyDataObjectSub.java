package net.barakiroth.lombokwithvalidations.supersub;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.barakiroth.lombokwithvalidations.validation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter(AccessLevel.PUBLIC)
@SuperBuilder(
        setterPrefix = "with",
        buildMethodName = "internalBuild",
        builderMethodName = "internalBuilder")
public class MyDataObjectSub extends MyDataObjectSuper {

    private final String s;

    public static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> builder() {
        return MyDataObjectSub.internalBuilder(new HashSet<>());
    }

    @SafeVarargs
    public static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> builder(final CategorizedValidationStrategy<MyDataObjectSub>... categorizedValidationStrategies) {
        return MyDataObjectSub.internalBuilder(ValidationStrategyCollector.collect(categorizedValidationStrategies));
    }

    /**
     * All provided strategies will be supplied with an error severity.
     *
     * @param validationStrategies
     * @return
     */
    @SafeVarargs
    public static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> builder(final IValidationStrategy<MyDataObjectSub>... validationStrategies) {
        return MyDataObjectSub.internalBuilder(ValidationStrategyCollector.collect(validationStrategies));
    }

    private static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> internalBuilder(final Set<CategorizedValidationStrategy<MyDataObjectSub>> uniqueCategorizedValidationStrategies) {

        final MyDataObjectSub.MyDataObjectSubBuilderImpl dataObjectBuilder =
                (MyDataObjectSub.MyDataObjectSubBuilderImpl) MyDataObjectSub.internalBuilder();
        dataObjectBuilder.setUniqueCategorizedValidationStrategies(uniqueCategorizedValidationStrategies);

        return dataObjectBuilder;
    }

    public static MyDataObjectSub.MyDataObjectSubBuilder<?, ?> internalBuilder() {
        return new MyDataObjectSubBuilderImpl();
    }

    public static abstract class MyDataObjectSubBuilder<C extends MyDataObjectSub, B extends MyDataObjectSubBuilder<C, B>> extends MyDataObjectSuperBuilder<C, B> {

        public C build() {
            return internalBuild();
        }

        public C build(final Collection<ConstraintViolation<MyDataObjectSub>> constraintViolations) {
            return internalBuild(constraintViolations);
        }

        protected abstract C internalBuild(final Collection<ConstraintViolation<MyDataObjectSub>> constraintViolations);
    }

    private static final class MyDataObjectSubBuilderImpl extends MyDataObjectSubBuilder<MyDataObjectSub, MyDataObjectSubBuilderImpl> {

        private Set<CategorizedValidationStrategy<MyDataObjectSub>> uniqueCategorizedValidationStrategies;

        private void setUniqueCategorizedValidationStrategies(final Set<CategorizedValidationStrategy<MyDataObjectSub>> uniqueCategorizedValidationStrategies) {
            this.uniqueCategorizedValidationStrategies = uniqueCategorizedValidationStrategies;
        }

        public MyDataObjectSub internalBuild() {
            return internalBuild(null);
        }

        public MyDataObjectSub internalBuild(final Collection<ConstraintViolation<MyDataObjectSub>> constraintViolations) {
            return Validator.validate(new MyDataObjectSub(this), this.uniqueCategorizedValidationStrategies, constraintViolations);
        }
    }
}
