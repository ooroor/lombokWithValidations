package net.barakiroth.lombokwithvalidations;

import net.barakiroth.lombokwithvalidations.domain.MyDataObject;
import net.barakiroth.lombokwithvalidations2.domain.MyDataObject2;
import net.barakiroth.lombokwithvalidations.domain.MyDataObjectValidationStrategy;
import net.barakiroth.lombokwithvalidations.domain.exceptions.MyDataObjectConstraintViolationException;
import net.barakiroth.lombokwithvalidations2.domain.MyDataObjectValidationStrategy2;
import net.barakiroth.lombokwithvalidations2.domain.exceptions.ConstraintViolationException2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log =
            LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        final App app = new App();
        app.run();
    }

    private void run() {

        final MyDataObject myDataObject =
                MyDataObject
                        .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                        .withS("abcd")
                        .build();
        log.debug("myDataObject:" + myDataObject);

        try {
            MyDataObject.builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                            .build();
        } catch (MyDataObjectConstraintViolationException e) {
            e.printStackTrace();
        }

        final MyDataObject2 myDataObject2 =
                MyDataObject2
                        .builder(MyDataObjectValidationStrategy2.S_IS_4_LONG)
                        .withS("abcd")
                        .build();
        log.debug("MyDataObject2:" + myDataObject2);

        try {
            MyDataObject2
                .builder(MyDataObjectValidationStrategy2.S_IS_4_LONG)
                .build();
        } catch (ConstraintViolationException2 e) {
            e.printStackTrace();
        }
    }
}