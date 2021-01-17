package net.barakiroth.lombokwithvalidations.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ConstraintViolation<DATA_OBJECT> {
    private final CategorizedValidationStrategy<DATA_OBJECT> categorizedValidationStrategy;
    private final String msg;
    private final Set<Pair<String, Object>> fieldsInvolvedInTheViolation;

    @Override
    public String toString() {
        return this.categorizedValidationStrategy.toString() + ", \"" + this.msg + "\"" + ", " + this.fieldsInvolvedInTheViolation.toString();
    }
}