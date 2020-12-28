package net.barakiroth.lombokwithvalidations2.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(
        setterPrefix = "with",
        builderClassName = "MyDataObjectBuilder",
        access = AccessLevel.PUBLIC,
        buildMethodName = "internalBuild",
        builderMethodName = "internalBuilder")
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
@ToString
public class MyDataObject2 {

    private final int i;
    private final String s;

    public static MyDataObjectBuilder builder(final MyDataObjectValidationStrategy2... validationStrategies) {
        final MyDataObjectBuilder myDataObjectBuilder = MyDataObject2.internalBuilder();
        myDataObjectBuilder.setValidationStrategies(validationStrategies);
        return myDataObjectBuilder;
    }

    private static MyDataObjectBuilder internalBuilder() {
        return new MyDataObjectBuilder();
    }

    public static class MyDataObjectBuilder {

        private MyDataObjectValidationStrategy2[] validationStrategies;

        private MyDataObjectBuilder() {
        }

        public MyDataObject2 build() {

            // First construct an unvalidated instance for the validator
            // to be able to get the builder's values because the builder
            // does not provide getters of its fields.
            final MyDataObject2 unvalidatedMyDataObject2 = internalBuild();

            final MyDataObject2 validatedMyDataObject2 =
                    AbstractValidationStrategy2.validate(unvalidatedMyDataObject2, this.validationStrategies);

            return validatedMyDataObject2;
        }
        // By letting lombok generate this method, it becomes public
        // Please notify me if I am wrong.
        private MyDataObject2 internalBuild() {
            return new MyDataObject2(this.i, this.s);
        }

        private void setValidationStrategies(MyDataObjectValidationStrategy2[] validationStrategies) {
            this.validationStrategies = validationStrategies;
        }
    }
}
