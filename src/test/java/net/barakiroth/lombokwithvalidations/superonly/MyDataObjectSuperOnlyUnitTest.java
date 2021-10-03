package net.barakiroth.lombokwithvalidations.superonly;

import net.barakiroth.lombokwithvalidations.validation.CategorizedValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolationException;
import net.barakiroth.lombokwithvalidations.validation.IValidationStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class MyDataObjectSuperOnlyUnitTest {

    @Test
    void when_setting_i_then_the_same_value_should_be_retrieved() {

        final int expectedI = 198;
        final MyDataObjectSuperOnly myDataObjectSuperOnly = MyDataObjectSuperOnly.builder().withI(expectedI).build();
        final int actualI = myDataObjectSuperOnly.getI();
        assertThat(actualI).isEqualTo(expectedI).as("Setting and getting i give different values");
    }

    @Test
    void when_setting_s_then_the_same_value_should_be_retrieved() {

        final String expectedS = "kdsfjvnbkdsjfnb cvkjnbkjc nkj bkujbekub kujebkuvbeku bvkubewkuekub";
        final MyDataObjectSuperOnly myDataObjectSuperOnly = MyDataObjectSuperOnly.builder().withS(expectedS).build();
        final String actualS = myDataObjectSuperOnly.getS();
        assertThat(actualS).isEqualTo(expectedS).as("Setting and getting s give different values");
    }

    @Test
    public void when_created_with_no_validation_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObjectSuperOnly.builder().build()).doesNotThrowAnyException();
    }

    @Test
    public void when_created_with_I_IS_7_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObjectSuperOnly.builder(MyDataObjectSuperOnlyValidationStrategy.I_IS_7).withI(7).build()).doesNotThrowAnyException();
    }

    @Test
    public void when_created_with_S_IS_4_LONG_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObjectSuperOnly.builder(MyDataObjectSuperOnlyValidationStrategy.S_IS_4_LONG).withS("pqrs").build()).doesNotThrowAnyException();
    }

    @Test
    public void when_created_with_S_IS_BETWEEN_7_AND_11_LONG_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObjectSuperOnly.builder(MyDataObjectSuperOnlyValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG).withS("pqrs1234").build()).doesNotThrowAnyException();
    }

    @Test
    public void when_validating_an_empty_object_with_VALIDATION_STRATEGIES_01_then_an_exception_should_be_thrown() {

        assertThatThrownBy(() -> {
            MyDataObjectSuperOnly.builder(MyDataObjectSuperOnlyValidationStrategy.VALIDATION_STRATEGIES_01).build();
        })
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("There are 2 constraint violations, and at least one of them is of type ERR: {1: ")
        ;
    }

    @Test
    public void when_validating_an_empty_object_with_different_validation_strategies_collections_then_exceptions_should_be_thrown_and_be_equipped_as_expected() {

        final MyDataObjectSuperOnlyValidationStrategy[][] strategiesCollections =
                new MyDataObjectSuperOnlyValidationStrategy[][]{
                        MyDataObjectSuperOnlyValidationStrategy.VALIDATION_STRATEGIES_01,
                        MyDataObjectSuperOnlyValidationStrategy.VALIDATION_STRATEGIES_02,
                        new MyDataObjectSuperOnlyValidationStrategy[]{MyDataObjectSuperOnlyValidationStrategy.I_IS_7, MyDataObjectSuperOnlyValidationStrategy.I_IS_7}
                };

        Arrays.stream(strategiesCollections).
                forEach(
                        (expectedViolatedStrategies) ->
                        {
                            final Throwable thrown = catchThrowable(() -> {
                                MyDataObjectSuperOnly.builder(expectedViolatedStrategies).build(); // No fields set, which surely will generate violation(s)
                            });
                            assertThat(thrown).isInstanceOf(ConstraintViolationException.class);

                            final ConstraintViolationException actualException =
                                    (ConstraintViolationException) thrown;

                            final Set<MyDataObjectSuperOnlyValidationStrategy> uniqueExpectedViolations =
                                    new HashSet<>(Arrays.asList(expectedViolatedStrategies));

                            assertThat(actualException.getConstraintViolations().size())
                                    .as(
                                            "Duplicate constraints gave rise to more than one corresponding violation. strategies: "
                                                    + Arrays.toString(expectedViolatedStrategies))
                                    .isEqualTo(uniqueExpectedViolations.size());

                            final Set<IValidationStrategy<?>> actualViolatedStrategies =
                                    actualException
                                            .getConstraintViolations()
                                            .stream()
                                            .map(
                                                    (actualConstraintViolation) ->
                                                            actualConstraintViolation.getCategorizedValidationStrategy().getValidationStrategy()
                                            )
                                            .collect(HashSet::new, HashSet::add, HashSet::addAll);

                            assertThat(actualViolatedStrategies)
                                    .as("There is at least one violation that is not represented in the exception")
                                    .containsAll(uniqueExpectedViolations);
                        }
                );
    }

    @Test
    void when_created_with_error_strategy_and_it_is_violated_then_an_exception_should_be_thrown() {

        assertThatThrownBy(
                () ->
                        MyDataObjectSuperOnly
                                .builder(CategorizedValidationStrategy.ofErr(MyDataObjectSuperOnlyValidationStrategy.I_IS_7))
                                .build()
        )
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void when_created_with_warning_and_error_strategies_and_they_are_violated_then_an_exception_should_be_thrown() {

        assertThatThrownBy(
                () ->
                        MyDataObjectSuperOnly
                                .builder(
                                        CategorizedValidationStrategy.ofWarn(MyDataObjectSuperOnlyValidationStrategy.I_IS_7),
                                        CategorizedValidationStrategy.ofErr(MyDataObjectSuperOnlyValidationStrategy.S_IS_4_LONG)
                                )
                                .build()
        ).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void when_created_with_warning_and_error_strategies_and_only_the_warning_is_violated_then_no_exception_should_be_thrown() {

        assertThatCode(
                () -> MyDataObjectSuperOnly.builder(
                                CategorizedValidationStrategy.ofWarn(MyDataObjectSuperOnlyValidationStrategy.I_IS_7),
                                CategorizedValidationStrategy.ofErr(MyDataObjectSuperOnlyValidationStrategy.S_IS_4_LONG)

                        )
                        .withS("abcd")
                        .build())
                .doesNotThrowAnyException();
    }

    @Test
    void when_created_with_warning_and_error_strategies_and_only_the_error_is_violated_then_the_exception_should_contain_only_one() {

        final ConstraintViolationException constraintViolationException =
                (ConstraintViolationException)
                        catchThrowable(
                                () -> MyDataObjectSuperOnly.builder(
                                                CategorizedValidationStrategy.ofWarn(MyDataObjectSuperOnlyValidationStrategy.I_IS_7),
                                                CategorizedValidationStrategy.ofErr(MyDataObjectSuperOnlyValidationStrategy.S_IS_4_LONG)
                                        )
                                        .withI(7)
                                        .build()
                        );

        final Set<ConstraintViolation<?>> constraintViolations = // TODO: Fix the generics
                constraintViolationException.getConstraintViolations();
        assertThat(constraintViolations.size()).isEqualTo(1);

        assertThat(
                constraintViolations
                        .stream()
                        .anyMatch(
                                (constraintViolation) ->
                                        constraintViolation.getCategorizedValidationStrategy().getSeverity().equals(IValidationStrategy.Severity.ERR)
                                                &&
                                                constraintViolation.getCategorizedValidationStrategy().getValidationStrategy().equals(MyDataObjectSuperOnlyValidationStrategy.S_IS_4_LONG)
                        )
        )
                .as("Expected constraint violation was not found")
                .isTrue();
    }

    @Test
    void when_referencing_the_builder_then_no_compilation_error_should_occur_since_the_builder_class_is_public_NON_COMPILABLE() {
        final MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilder myDoMyDataObjectBuilder; // LEGAL: The class is not private
    }

    @Test
    void the_myDataObjectBuilder_constructor_should_be_private_NON_COMPILABLE() {
        final MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilder myDataObjectBuilder
                // Comment out to confirm compiler error:
                // = new MyDataObjectSuperOnly.MyDataObjectSuperOnlyBuilder()
                ; // ILLEGAL: 'DataObjectBuilder()' has private access in 'net.barakiroth.lombokexperiments.plain.MyDataObject.DataObjectBuilder'
    }
}