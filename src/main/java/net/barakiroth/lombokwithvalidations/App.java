package net.barakiroth.lombokwithvalidations;

import net.barakiroth.lombokwithvalidations.domain.MyDataObject;
import net.barakiroth.lombokwithvalidations.domain.MyDataObjectValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.CategorizedValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class App {

    private static final Logger log =
            LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        final App app = new App();
        app.run1();
        app.run2();
        app.run3();
        app.run4();
    }

    private void run1() {

        final MyDataObject myDataObject =
                MyDataObject
                        .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                        .withS("abcd")
                        .withI(103)
                        .build();
        log.info("myDataObject: " + myDataObject);
    }

    private void run2() {

        try {
            MyDataObject
                    .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                    .build();
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
        }
    }

    private void run3() {

        try {
            MyDataObject
                    .builder(MyDataObjectValidationStrategy.S_IS_4_LONG, MyDataObjectValidationStrategy.I_IS_7)
                    .build();
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
        }
    }

    private void run4() {

        final Set<ConstraintViolation<MyDataObject>> constraintViolations = new HashSet<>();
        MyDataObject.builder(
                CategorizedValidationStrategy
                        .ofWarn(MyDataObjectValidationStrategy.I_IS_7),
                CategorizedValidationStrategy
                        .ofWarn(MyDataObjectValidationStrategy.S_IS_4_LONG),
                CategorizedValidationStrategy
                        .ofWarn(MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG)
        )
                .withS("1bcdefgh9")
                .build(constraintViolations);
        if (constraintViolations.size() > 0) {
            log.warn(constraintViolations.toString());
        }
    }
}