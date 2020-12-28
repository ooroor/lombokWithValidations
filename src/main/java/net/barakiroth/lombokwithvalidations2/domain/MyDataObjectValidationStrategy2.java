package net.barakiroth.lombokwithvalidations2.domain;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class MyDataObjectValidationStrategy2 extends AbstractValidationStrategy2<MyDataObject2> {

    public MyDataObjectValidationStrategy2(
        final BiFunction<
                MyDataObject2,
                AbstractValidationStrategy2<MyDataObject2>,
                ConstraintViolation2<MyDataObject2>
        > validator) {
        super(validator);
    }

    public static final MyDataObjectValidationStrategy2 S_IS_4_LONG =
        new MyDataObjectValidationStrategy2(
            (final MyDataObject2 unvalidatedDataObject, final AbstractValidationStrategy2<MyDataObject2> thiz) -> {
                final ConstraintViolation2<MyDataObject2> constraintViolation;
                if (unvalidatedDataObject.getS() == null || unvalidatedDataObject.getS().length() != 4) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair("i", unvalidatedDataObject.getI()));
                    }};
                    constraintViolation =
                            new ConstraintViolation2<>(
                                thiz,
                                fieldsInvolvedInTheViolation,
                                "i differs from 7"
                            );
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
        );
}
