package net.barakiroth.lombokwithvalidations2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

@Getter
@ToString
@AllArgsConstructor
public class ConstraintViolation2<DATA_OBJECT> {
    private final AbstractValidationStrategy2<DATA_OBJECT> validationStrategy;
    private final Set<Pair<String, Object>> fieldsInvolvedInTheViolation;
    private final String msg;
}