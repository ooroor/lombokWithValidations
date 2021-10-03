package net.barakiroth.lombokwithvalidations.superonly;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.barakiroth.lombokwithvalidations.validation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter(AccessLevel.PUBLIC)
@SuperBuilder(setterPrefix = "with")
public class MyDataObjectSuperOnly {
    private final int i;
    private final String s;

    public static MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilder<?, ?> builder() {
        return MyDataObjectSuperOnly.builder(new HashSet<>());
    }

    public static MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilder<?, ?> builder(final CategorizedValidationStrategy<MyDataObjectSuperOnly>... categorizedValidationStrategies) {
        return MyDataObjectSuperOnly.builder(ValidationStrategyCollector.collect(categorizedValidationStrategies));
    }

    /**
     * All provided strategies will be supplied with an error severity.
     *
     * @param validationStrategies
     * @return
     */
    public static MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilder<?, ?> builder(final IValidationStrategy<MyDataObjectSuperOnly>... validationStrategies) {
        return MyDataObjectSuperOnly.builder(ValidationStrategyCollector.collect(validationStrategies));
    }

    private static MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilder<?, ?> builder(final Set<CategorizedValidationStrategy<MyDataObjectSuperOnly>> uniqueCategorizedValidationStrategies) {

        final MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilderImpl dataObjectBuilder = new MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilderImpl();
        dataObjectBuilder.setUniqueCategorizedValidationStrategies(uniqueCategorizedValidationStrategies);

        return dataObjectBuilder;
    }
    // -----------------------------------------------------------------------------------------------------------------
    private static final class MyDataObjectSuperOnlyBuilderImpl extends MyDataObjectSuperOnlyBuilder<MyDataObjectSuperOnly, MyDataObjectSuperOnlyBuilderImpl> {

        private Set<CategorizedValidationStrategy<MyDataObjectSuperOnly>> uniqueCategorizedValidationStrategies;

        private void setUniqueCategorizedValidationStrategies(final Set<CategorizedValidationStrategy<MyDataObjectSuperOnly>> uniqueCategorizedValidationStrategies) {
            this.uniqueCategorizedValidationStrategies = uniqueCategorizedValidationStrategies;
        }

        public MyDataObjectSuperOnly build() {
            return build(null);
        }

        public MyDataObjectSuperOnly build(final Collection<ConstraintViolation<MyDataObjectSuperOnly>> constraintViolations) {
            return Validator.validate(new MyDataObjectSuperOnly(this), this.uniqueCategorizedValidationStrategies, constraintViolations);
        }
    }
    // -----------------------------------------------------------------------------------------------------------------
}
