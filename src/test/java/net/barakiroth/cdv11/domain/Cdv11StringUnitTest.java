package net.barakiroth.cdv11.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Cdv11UnitTest {
/*
    @Test
    void when_setting_i_then_the_same_value_should_be_retrieved() {

        final int expectedI = 198;
        final MyDataObject myDataObject = MyDataObject.builder().withI(expectedI).build();
        final int actualI = myDataObject.getI();
        assertThat(actualI).isEqualTo(expectedI).as("Setting and getting i give different values");
    }

    @Test
    void when_setting_s_then_the_same_value_should_be_retrieved() {

        final String expectedS = "kdsfjvnbkdsjfnb cvkjnbkjc nkj bkujbekub kujebkuvbeku bvkubewkuekub";
        final MyDataObject myDataObject = MyDataObject.builder().withS(expectedS).build();
        final String actualS = myDataObject.getS();
        assertThat(actualS).isEqualTo(expectedS).as("Setting and getting s give different values");
    }

    @Test
    public void when_created_with_no_validation_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObject.builder().build()).doesNotThrowAnyException();
    }

    @Test
    public void when_created_with_I_IS_7_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObject.builder(MyDataObjectValidationStrategy.I_IS_7).withI(7).build()).doesNotThrowAnyException();
    }

    @Test
    public void when_created_with_S_IS_4_LONG_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObject.builder(MyDataObjectValidationStrategy.S_IS_4_LONG).withS("pqrs").build()).doesNotThrowAnyException();
    }

    @Test
    public void when_created_with_S_IS_BETWEEN_7_AND_11_LONG_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObject.builder(MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG).withS("pqrs1234").build()).doesNotThrowAnyException();
    }

    @Test
    public void when_validating_an_empty_object_with_VALIDATION_STRATEGIES_01_then_an_exception_should_be_thrown() {

        assertThatThrownBy(() -> {
            MyDataObject.builder(MyDataObjectValidationStrategy.VALIDATION_STRATEGIES_01).build();
        })
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining("There are 2 constraint violations: {1: ")
        ;
    }

    @Test
    public void when_validating_an_empty_object_with_different_validation_strategies_collections_then_exceptions_should_be_thrown_and_be_equipped_as_expected() {

        final MyDataObjectValidationStrategy[][] strategiesCollections =
                new MyDataObjectValidationStrategy[][] {
                    MyDataObjectValidationStrategy.VALIDATION_STRATEGIES_01,
                    MyDataObjectValidationStrategy.VALIDATION_STRATEGIES_02,
                    new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.I_IS_7, MyDataObjectValidationStrategy.I_IS_7}
        };

        Arrays.stream(strategiesCollections).
            forEach(
                (expectedViolatedStrategies) ->
                {
                    final Throwable thrown = catchThrowable(() -> {
                        MyDataObject.builder(expectedViolatedStrategies).build(); // No fields set, which surely will generate violation(s)
                    });
                    assertThat(thrown).isInstanceOf(ConstraintViolationException.class);

                    final ConstraintViolationException actualException =
                            (ConstraintViolationException)thrown;

                    final Set<MyDataObjectValidationStrategy> uniqueExpectedViolations = new HashSet<>();
                    uniqueExpectedViolations.addAll(Arrays.asList(expectedViolatedStrategies));

                    assertThat(actualException.getConstraintViolations().size())
                        .as("Duplicate constraints gave rise to more than one corresponding violation. strategies: " + expectedViolatedStrategies)
                        .isEqualTo(uniqueExpectedViolations.size());

                    final Set<IValidationStrategy<?>> actualViolatedStrategies =
                        actualException
                            .getConstraintViolations()
                            .stream()
                            .map(
                                (actualViolatedStrategy) ->
                                    actualViolatedStrategy.getValidationStrategy()
                            )
                            .collect(HashSet::new, HashSet::add, HashSet::addAll)
                            ;

                    assertThat(actualViolatedStrategies)
                        .as("There is at least one violation that is not represented in the exception")
                        .containsAll(uniqueExpectedViolations);
                }
            );
    }

    void when_referencing_the_builder_then_no_compilation_error_should_occur_since_the_builder_class_is_public_NON_COMPILABLE() {
        final MyDataObject.MyDataObjectBuilder myDoMyDataObjectBuilder; // LEGAL: The class is not private
    }

    void internal_build_should_be_private_NON_COMPILABLE() {
        final MyDataObject.MyDataObjectBuilder myDoBuilder = MyDataObject.builder();
        // Comment out to confirm compiler error:
        //myDoBuilder.internalBuild(); // ILLEGAL - 'internalBuild()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject.MyDataObjectBuilder'
    }

    void internal_builder_should_be_private_NON_COMPILABLE() {
        final MyDataObject.MyDataObjectBuilder myDoBuilder
                // Comment out to confirm compiler error:
                // = MyDataObject.internalBuilder()  // ILLEGAL: 'internalBuilder()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject'
        ;
    }

    void the_myDataObjectBuilder_constructor_should_be_private_NON_COMPILABLE() {
        final MyDataObject.MyDataObjectBuilder myDataObjectBuilder
                // Comment out to confirm compiler error:
                // = new MyDataObject.MyDataObjectBuilder()
                ; // ILLEGAL: 'MyDataObjectBuilder()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject.MyDataObjectBuilder'
    }

 */
}
