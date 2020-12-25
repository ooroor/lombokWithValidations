package net.barakiroth.lombokwithvalidations.domain;

import net.barakiroth.lombokwithvalidations.domain.exceptions.MyDataObjectConstraintViolationException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class MyDataObjectUnitTest {

    @Test
    public void when_created_with_no_validation_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObject.builder().build()).doesNotThrowAnyException();
    }

    @Test
    public void when_created_with_one_validation_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {
        assertThatCode(() -> MyDataObject.builder(MyDataObjectValidationStrategy.I_IS_7).withI(7).build()).doesNotThrowAnyException();
    }

    @Test
    public void when_validating_an_empty_object_with_VALIDATION_STRATEGIES_01_then_exception_should_be_thrown() {

        assertThatThrownBy(() -> {
            MyDataObject.builder(MyDataObjectValidationStrategy.VALIDATION_STRATEGIES_01).build();
        })
            .isInstanceOf(MyDataObjectConstraintViolationException.class)
            .hasMessageContaining("There is/are " + MyDataObjectValidationStrategy.VALIDATION_STRATEGIES_01.length + " constraint violation(s)")
        ;
    }

    @Test
    public void when_validating_an_empty_object_with_different_validation_strategies_collections_then_exception_should_be_thrown_and_be_equipped_as_expected() {

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
                    assertThat(thrown).isInstanceOf(MyDataObjectConstraintViolationException.class);

                    final MyDataObjectConstraintViolationException actualException =
                            (MyDataObjectConstraintViolationException)thrown;

                    final Set<MyDataObjectValidationStrategy> uniqueExpectedViolations = new HashSet<>();
                    uniqueExpectedViolations.addAll(Arrays.asList(expectedViolatedStrategies));

                    assertThat(actualException.getConstraintViolations().size())
                        .as("Duplicate constraints gave rise to moe than one corresponding violation. strategies: " + expectedViolatedStrategies)
                        .isEqualTo(uniqueExpectedViolations.size());

                    final Set<MyDataObjectValidationStrategy> actualViolatedStrategies =
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
        final MyDataObject.MyDataObjectBuilder myDoMyDataObjectBuilder;
    }

    void internal_build_should_be_private_NON_COMPILABLE() {
        final MyDataObject.MyDataObjectBuilder myDoBuilder = MyDataObject.builder(); // LEGAL: The class is not private
        //myDoBuilder_01.internalBuild(); // ILLEGAL - 'internalBuild()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject.MyDataObjectBuilder'
    }

    void internal_builder_should_be_private_NON_COMPILABLE() {
        final MyDataObject.MyDataObjectBuilder myDoBuilder
                // = MyDataObject.internalBuilder()
                ;  // ILLEGAL: 'internalBuilder()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject'
    }

    void the_myDataObjectBuilder_constructor_should_be_private_NON_COMPILABLE() {
        final MyDataObject.MyDataObjectBuilder myDataObjectBuilder
                //= new MyDataObject.MyDataObjectBuilder()
                ; // ILLEGAL: 'MyDataObjectBuilder()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject.MyDataObjectBuilder'
    }
}
