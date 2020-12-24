package net.barakiroth.lombokwithvalidations.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

@Getter
@ToString
@AllArgsConstructor
public class MyDataObjectConstraintViolation {
    private final MyDataObjectValidationStrategy myDataObjectValidationStrategy;
    private final Set<Pair<String, Object>> fieldsInvolvedInTheViolation;
    private final String msg;
}