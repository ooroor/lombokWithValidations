package net.barakiroth.lombokwithvalidations;

import net.barakiroth.lombokwithvalidations.domain.MyDataObject;
import net.barakiroth.lombokwithvalidations.domain.MyDataObjectValidationStrategy;
import net.barakiroth.lombokwithvalidations.domain.exceptions.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log =
            LoggerFactory.getLogger(App.class);

    public static void main( String[] args) {
        final App app = new App();
        app.run();
    }

    private void run() {

        final MyDataObject myDataObject =
                MyDataObject
                        .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                        .withS("abcd")
                        .withI(103)
                        .build();
        log.info("myDataObject: " + myDataObject);

        try {
            MyDataObject
                .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                .build();
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
        }

        try {
            MyDataObject
                    .builder(MyDataObjectValidationStrategy.S_IS_4_LONG, MyDataObjectValidationStrategy.I_IS_7)
                    .build();
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
        }
    }
}