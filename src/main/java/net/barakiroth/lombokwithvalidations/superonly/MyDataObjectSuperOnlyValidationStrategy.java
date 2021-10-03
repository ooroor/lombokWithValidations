package net.barakiroth.lombokwithvalidations.superonly;


import net.barakiroth.lombokwithvalidations.validation.CategorizedValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.validation.IValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.Validator;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

public enum MyDataObjectSuperOnlyValidationStrategy
        implements IValidationStrategy<MyDataObjectSuperOnly> {
    I_IS_7(
            // Alternative 1 of doing it (personal taste):
            (uvdo, thiz, sev) -> {
                return Validator.validate(
                        uvdo,
                        CategorizedValidationStrategy.of(thiz, sev),
                        "i differs from 7",
                        (final MyDataObjectSuperOnly uvdop) -> uvdop.getI() != 7,
                        new ImmutablePair<>("i", uvdo.getI())
                );
            }
    ),
    S_IS_4_LONG(
            // Alternative 2 of doing it (personal taste):
            (uvdo, thiz, sev) -> {
                final ConstraintViolation<MyDataObjectSuperOnly> constraintViolation;
                if (uvdo.getS() == null || uvdo.getS().length() != 4) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair<>("s", uvdo.getS()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(CategorizedValidationStrategy.of(thiz, sev), "length of s differs from 4", fieldsInvolvedInTheViolation);
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
    ),
    S_IS_BETWEEN_7_AND_11_LONG(
            // Alternative 3 of doing it (personal taste):
            (final MyDataObjectSuperOnly uvdo, final MyDataObjectSuperOnlyValidationStrategy thiz, final Severity sev) -> {
                final ConstraintViolation<MyDataObjectSuperOnly> constraintViolation;
                if (uvdo.getS() == null || uvdo.getS().length() < 7 || uvdo.getS().length() > 11) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair<>("s", uvdo.getS()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(CategorizedValidationStrategy.of(thiz, sev), "length of s not between 7 and 11", fieldsInvolvedInTheViolation);
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
    );

    public static final MyDataObjectSuperOnlyValidationStrategy[] VALIDATION_STRATEGIES_01 =
            new MyDataObjectSuperOnlyValidationStrategy[]{MyDataObjectSuperOnlyValidationStrategy.I_IS_7, MyDataObjectSuperOnlyValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG};
    public static final MyDataObjectSuperOnlyValidationStrategy[] VALIDATION_STRATEGIES_02 =
            new MyDataObjectSuperOnlyValidationStrategy[]{MyDataObjectSuperOnlyValidationStrategy.S_IS_4_LONG};

    private final TriFunction<MyDataObjectSuperOnly, MyDataObjectSuperOnlyValidationStrategy, Severity, ConstraintViolation<MyDataObjectSuperOnly>> validator;

    MyDataObjectSuperOnlyValidationStrategy(
            final TriFunction<MyDataObjectSuperOnly, MyDataObjectSuperOnlyValidationStrategy, Severity, ConstraintViolation<MyDataObjectSuperOnly>> validator) {
        this.validator = validator;
    }

    @Override
    public ConstraintViolation<MyDataObjectSuperOnly> validate(final MyDataObjectSuperOnly unvalidatedMyDataObject, final Severity severity) {
        return this.validator.apply(unvalidatedMyDataObject, this, severity);
    }

    @FunctionalInterface
    private interface TriFunction<T, U, V, R> {
        R apply(final T t, final U u, final V v);
    }
}