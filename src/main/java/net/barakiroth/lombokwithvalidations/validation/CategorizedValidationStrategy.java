package net.barakiroth.lombokwithvalidations.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class CategorizedValidationStrategy<DATA_OBJECT> {
    @Getter private final IValidationStrategy<DATA_OBJECT> validationStrategy;
    @Getter private final IValidationStrategy.Severity severity;

    private CategorizedValidationStrategy(
            final IValidationStrategy<DATA_OBJECT> validationStrategy,
            final IValidationStrategy.Severity severity) {
        this.validationStrategy = validationStrategy;
        this.severity = severity;
    }

    public static <DATA_OBJECT> CategorizedValidationStrategy<DATA_OBJECT> of(
            final IValidationStrategy<DATA_OBJECT> validationStrategy,
            final IValidationStrategy.Severity severity) {
        return new CategorizedValidationStrategy<>(validationStrategy, severity);
    }

    public static <DATA_OBJECT> CategorizedValidationStrategy<DATA_OBJECT> ofWarn(
            final IValidationStrategy<DATA_OBJECT> validationStrategy) {
        return CategorizedValidationStrategy.of(validationStrategy, IValidationStrategy.Severity.WARN);
    }

    public static <DATA_OBJECT> CategorizedValidationStrategy<DATA_OBJECT> ofErr(
            final IValidationStrategy<DATA_OBJECT> validationStrategy) {
        return CategorizedValidationStrategy.of(validationStrategy, IValidationStrategy.Severity.ERR);
    }
}