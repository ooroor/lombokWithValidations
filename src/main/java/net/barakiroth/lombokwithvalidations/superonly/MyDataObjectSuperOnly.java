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

    /*
    protected MyDataObjectSuperOnly(MyDataObjectSuperOnlyBuilder<?, ?> b) {
        this.i = b.i;
        this.s = b.s;
    }

    public static MyDataObjectSuperOnlyBuilder<?, ?> builder() {
        return new MyDataObjectSuperOnlyBuilderImpl();
    }
*/
    // -----------------------------------------------------------------------------------------------------------------
    public static abstract class MyDataObjectSuperOnlyBuilder<C extends MyDataObjectSuperOnly, B extends MyDataObjectSuperOnlyBuilder<C, B>> {


        //public abstract C build();
/*
        public C build(final Collection<ConstraintViolation<MyDataObjectSuperOnly>> constraintViolations) {
            return build(constraintViolations);
        }
*/
    /*
        private int i;
        private String s;

        public B withI(int i) {
            this.i = i;
            return self();
        }

        public B withS(String s) {
            this.s = s;
            return self();
        }

        protected abstract B self();


        public String toString() {
            return "MyDataObjectSub.MyDataObjectSuperOnlyBuilder(i=" + this.i + ", s=" + this.s + ")";
        }
     */
    }
    // -----------------------------------------------------------------------------------------------------------------
    private static final class MyDataObjectSuperOnlyBuilderImpl extends MyDataObjectSuperOnlyBuilder<MyDataObjectSuperOnly, MyDataObjectSuperOnlyBuilderImpl> {

        private Set<CategorizedValidationStrategy<MyDataObjectSuperOnly>> uniqueCategorizedValidationStrategies;

        private void setUniqueCategorizedValidationStrategies(final Set<CategorizedValidationStrategy<MyDataObjectSuperOnly>> uniqueCategorizedValidationStrategies) {
            this.uniqueCategorizedValidationStrategies = uniqueCategorizedValidationStrategies;
        }

        /*
        private MyDataObjectSuperOnlyBuilderImpl() {
        }

        protected MyDataObjectSuperOnlyBuilderImpl self() {
            return this;
        }
     */

        public MyDataObjectSuperOnly build() {
            return build(null);
        }

        public MyDataObjectSuperOnly build(final Collection<ConstraintViolation<MyDataObjectSuperOnly>> constraintViolations) {
            return Validator.validate(new MyDataObjectSuperOnly(this), this.uniqueCategorizedValidationStrategies, constraintViolations);
        }
    }
    // -----------------------------------------------------------------------------------------------------------------
}
