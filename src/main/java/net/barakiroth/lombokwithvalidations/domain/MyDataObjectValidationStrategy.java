package net.barakiroth.lombokwithvalidations.domain;

import java.util.Arrays;
import java.util.function.Consumer;

public enum MyDataObjectValidationStrategy {

    I_IS_7((unvalidatedMyDataObject) -> {
        if (unvalidatedMyDataObject.getI() != 7)
            throw new IllegalStateException("i differs from 7: " + unvalidatedMyDataObject.getI());
    }),
    S_IS_4_LONG((unvalidatedMyDataObject) -> {
        if (unvalidatedMyDataObject.getS().length() != 4)
            throw new IllegalStateException("length of s differs from 4: " + unvalidatedMyDataObject.getS());
    }),
    S_IS_BETWEEN_7_AND_11_LONG((unvalidatedMyDataObject) -> {
        if (unvalidatedMyDataObject.getS().length() < 7 || unvalidatedMyDataObject.getS().length() > 11)
            throw new IllegalStateException("length of s not between 7 and 11: " + unvalidatedMyDataObject.getS());
    })
    ;
    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_01 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.I_IS_7, MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG};
    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_02 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.S_IS_4_LONG};


    final Consumer<MyDataObject> validator;

    MyDataObjectValidationStrategy(final Consumer<MyDataObject> validator) {
        this.validator = validator;
    }

    public static void validate(final MyDataObject unvalidatedMyDataObject, final MyDataObjectValidationStrategy[] validationStrategies) {

        if (validationStrategies != null) {
            Arrays.stream(validationStrategies)
            .forEach((myDataObjectValidationStrategy) -> myDataObjectValidationStrategy.internalValidate(unvalidatedMyDataObject));
            ;
        }
    }

    private void internalValidate(final MyDataObject unvalidatedMyDataObject) {
        this.validator.accept(unvalidatedMyDataObject);
    }
}