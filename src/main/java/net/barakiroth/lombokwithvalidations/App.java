package net.barakiroth.lombokwithvalidations;

import net.barakiroth.lombokwithvalidations.domain.MyDataObject;
import net.barakiroth.lombokwithvalidations.domain.MyDataObjectValidationStrategy;
import net.barakiroth.lombokwithvalidations.domain.exceptions.MyDataObjectConstraintViolationException;
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
            final MyDataObject myDataObject2 =
                    MyDataObject
                            .builder(MyDataObjectValidationStrategy.S_IS_4_LONG)
                            .build();
        } catch (MyDataObjectConstraintViolationException e) {
            e.printStackTrace();
        }
    }
}
