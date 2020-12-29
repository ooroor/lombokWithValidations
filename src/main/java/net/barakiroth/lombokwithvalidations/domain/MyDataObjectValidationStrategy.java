package net.barakiroth.lombokwithvalidations.domain;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class MyDataObjectValidationStrategy extends AbstractValidationStrategy<MyDataObject> {

    public static final MyDataObjectValidationStrategy I_IS_7 =
            new MyDataObjectValidationStrategy(
                    "I_IS_7",
                    (final MyDataObject unvalidatedDataObject, final AbstractValidationStrategy<MyDataObject> thiz) -> {
                        final ConstraintViolation<MyDataObject> constraintViolation;
                        if (unvalidatedDataObject.getI() != 7) {
                            final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                                add(new ImmutablePair("i", unvalidatedDataObject.getI()));
                            }};
                            constraintViolation =
                                    new ConstraintViolation<>(thiz, "i differs from 7", fieldsInvolvedInTheViolation);
                        } else {
                            constraintViolation = null;
                        }
                        return constraintViolation;
                    }
            );
    public static final MyDataObjectValidationStrategy S_IS_4_LONG =
            new MyDataObjectValidationStrategy(
                    "S_IS_4_LONG",
                    (final MyDataObject unvalidatedDataObject, final AbstractValidationStrategy<MyDataObject> thiz) -> {
                        final ConstraintViolation<MyDataObject> constraintViolation;
                        if (unvalidatedDataObject.getS() == null || unvalidatedDataObject.getS().length() != 4) {
                            final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                                add(new ImmutablePair("s", unvalidatedDataObject.getS()));
                            }};
                            constraintViolation =
                                    new ConstraintViolation<>(thiz, "length of s differs from 4", fieldsInvolvedInTheViolation);
                        } else {
                            constraintViolation = null;
                        }
                        return constraintViolation;
                    }
            );
    public static final MyDataObjectValidationStrategy S_IS_BETWEEN_7_AND_11_LONG =
            new MyDataObjectValidationStrategy(
                    "S_IS_BETWEEN_7_AND_11_LONG",
                    (final MyDataObject unvalidatedDataObject, final AbstractValidationStrategy<MyDataObject> thiz) -> {
                        final ConstraintViolation<MyDataObject> constraintViolation;
                        if (unvalidatedDataObject.getS() == null || unvalidatedDataObject.getS().length() < 7 || unvalidatedDataObject.getS().length() > 11) {
                            final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                                add(new ImmutablePair("s", unvalidatedDataObject.getS()));
                            }};
                            constraintViolation =
                                    new ConstraintViolation<>(thiz, "length of s not between 7 and 11", fieldsInvolvedInTheViolation);
                        } else {
                            constraintViolation = null;
                        }
                        return constraintViolation;
                    }
            );
    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_01 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.I_IS_7, MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG};
    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_02 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.S_IS_4_LONG};

    public MyDataObjectValidationStrategy(
            final String name,
            final BiFunction<
                    MyDataObject,
                    AbstractValidationStrategy<MyDataObject>,
                    ConstraintViolation<MyDataObject>
                    > validator) {
        super(name, validator);
    }
}
