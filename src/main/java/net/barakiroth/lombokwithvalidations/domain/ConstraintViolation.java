package net.barakiroth.lombokwithvalidations.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ConstraintViolation<DATA_OBJECT> {
    private final AbstractValidationStrategy<DATA_OBJECT> validationStrategy;
    private final String msg;
    private final Set<Pair<String, Object>> fieldsInvolvedInTheViolation;

    @Override
    public String toString() {
        return validationStrategy.toString() + ", \"" + msg + "\"" + ", " + fieldsInvolvedInTheViolation.toString();
    }
}