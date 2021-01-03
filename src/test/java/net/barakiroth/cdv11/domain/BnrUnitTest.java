package net.barakiroth.cdv11.domain;

import net.barakiroth.cdv11.Bnr;
import net.barakiroth.cdv11.exceptions.Cdv11StringFormatException;
import net.barakiroth.cdv11.exceptions.DateBasedCdv11StringFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BnrUnitTest {

    private static Stream<Arguments> providePotentialBnrStrings() {
        return Stream.of(
                Arguments.of("40010112345", Cdv11StringFormatException.class, null, "The provided string does not validate as to control digits"),
                Arguments.of("01200112345", Cdv11StringFormatException.class, null, "The provided string does not validate as to control digits"),
                Arguments.of("19325612986", null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("providePotentialBnrStrings")
    void when_instantiating_with_an_invalid_string_then_an_exception_should_be_thrown(
            final String potentialCdv11String,
            final Class<Throwable> expectedThrowableClass,
            final Class<Throwable> expectedThrowableCauseClass,
            final String expectedMsg) throws Cdv11StringFormatException, DateBasedCdv11StringFormatException {

        if (expectedThrowableClass == null) {

            assertThatCode(() -> new Bnr(potentialCdv11String))
                    .doesNotThrowAnyException();

            final Bnr bnr = new Bnr(potentialCdv11String);
            assertThat(bnr.getValue()).isEqualTo(potentialCdv11String);
        } else {
            if (expectedThrowableCauseClass == null) {
                assertThatThrownBy(
                        () -> {
                            new Bnr(potentialCdv11String);
                        })
                        .isInstanceOf(expectedThrowableClass)
                        .hasMessageContaining(expectedMsg)
                        .hasNoCause();
            } else {
                assertThatThrownBy(
                        () -> {
                            new Bnr(potentialCdv11String);
                        })
                        .isInstanceOf(expectedThrowableClass)
                        .hasMessageContaining(expectedMsg)
                        .hasCauseInstanceOf(expectedThrowableCauseClass);
            }
        }
    }

    @Test
    void when_x_then_y() {
        final Bnr bnr = new Bnr(LocalDate.of(1, 2, 3), 0);
    }
}
