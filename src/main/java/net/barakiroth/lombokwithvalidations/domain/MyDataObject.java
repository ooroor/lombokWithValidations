package net.barakiroth.lombokwithvalidations.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.barakiroth.lombokwithvalidations.domain.exceptions.MyDataObjectConstraintViolationException;

@Builder(
        setterPrefix = "with",
        builderClassName = "MyDataObjectBuilder",
        access = AccessLevel.PUBLIC,
        buildMethodName = "internalBuild",
        builderMethodName = "internalBuilder")
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
public class MyDataObject {

    private final int i;
    private final String s;

    public static MyDataObjectBuilder builder(final MyDataObjectValidationStrategy... validationStrategies) {
        final MyDataObjectBuilder myDataObjectBuilder = MyDataObject.internalBuilder();
        myDataObjectBuilder.setValidationStrategies(validationStrategies);
        return myDataObjectBuilder;
    }

    private static MyDataObjectBuilder internalBuilder() {
        return new MyDataObjectBuilder();
    }

    public static class MyDataObjectBuilder {

        private MyDataObjectValidationStrategy[] validationStrategies;

        private MyDataObjectBuilder() {
        }

        public MyDataObject build() {

            // First construct an unvalidated instance for the validator
            // to be able to get the builder's values because the builder
            // does not provide gettters of its fields.
            final MyDataObject unvalidatedMyDataObject = internalBuild();

            final MyDataObject validatedMyDataObject =
                    MyDataObjectValidationStrategy.validate(unvalidatedMyDataObject, this.validationStrategies);

            return validatedMyDataObject;
        }

        private MyDataObject internalBuild() {
            return new MyDataObject(this.i, this.s);
        }

        private void setValidationStrategies(MyDataObjectValidationStrategy[] validationStrategies) {
            this.validationStrategies = validationStrategies;
        }
    }

}
