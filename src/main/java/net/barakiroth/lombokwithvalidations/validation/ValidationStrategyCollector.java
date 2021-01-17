package net.barakiroth.lombokwithvalidations.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationStrategyCollector {

    public static <DATA_OBJECT> Set<CategorizedValidationStrategy<DATA_OBJECT>> collect(final IValidationStrategy<DATA_OBJECT>[] validationStrategies) {

        final List<IValidationStrategy<DATA_OBJECT>> validationStrategyList = Arrays.asList(validationStrategies);
        final Set<CategorizedValidationStrategy<DATA_OBJECT>> uniqueCategorizedValidationStrategies =
                validationStrategyList
                        .stream()
                        .map((final IValidationStrategy<DATA_OBJECT> validationStrategy) -> CategorizedValidationStrategy.of(validationStrategy, IValidationStrategy.Severity.ERR))
                        .collect(Collectors.toSet());

        return uniqueCategorizedValidationStrategies;
    }

    public static <DATA_OBJECT> Set<CategorizedValidationStrategy<DATA_OBJECT>> collect(final CategorizedValidationStrategy<DATA_OBJECT>... validationStrategies) {

        final Set<CategorizedValidationStrategy<DATA_OBJECT>> uniqueCategorizedValidationStrategies = new HashSet<>();
        final List<CategorizedValidationStrategy<DATA_OBJECT>> categorizedValidationStrategyList = Arrays.asList(validationStrategies);
        categorizedValidationStrategyList
                .stream()
                .forEach(
                        (final CategorizedValidationStrategy<DATA_OBJECT> categorizedValidationStrategy) -> {
                            uniqueCategorizedValidationStrategies
                                    .stream()
                                    .filter(
                                            (cvs) ->
                                                    cvs.getValidationStrategy().equals(categorizedValidationStrategy.getValidationStrategy())
                                                            &&
                                                            cvs.getSeverity().equals(IValidationStrategy.Severity.WARN))
                                    .findFirst()
                                    .ifPresent(
                                            (final CategorizedValidationStrategy<DATA_OBJECT> cvs) -> {
                                                uniqueCategorizedValidationStrategies.remove(cvs);
                                            }
                                    );
                            uniqueCategorizedValidationStrategies.add(categorizedValidationStrategy);
                        }
                );
        return uniqueCategorizedValidationStrategies;
    }
}
