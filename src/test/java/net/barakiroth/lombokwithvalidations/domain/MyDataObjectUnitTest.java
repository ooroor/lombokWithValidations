package net.barakiroth.lombokwithvalidations.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class MyDataObjectUnitTest {

    @Test
    public void when_created_with_no_validation_then_no_exceptions_should_be_thrown() {

        //final MyDataObject myDataObject = MyDataObject.builder(MyDataObjectValidationStrategy.I_IS_7).build();

        assertThatCode(() -> MyDataObject.builder().build()).doesNotThrowAnyException();

        /*

        ITestConst.enteringTestHeaderLogger.debug(null);

        final Main main = Main.getSingleton();

        final IFarBackendConfig farBackendConfig = FarBackendConfig.getSingleton();

        final JettyManager  jettyManager1  = main.getJettyManager(farBackendConfig);
        final JettyManager  jettyManager2  = main.getJettyManager(farBackendConfig);

        assertThat(jettyManager1).isEqualTo(jettyManager2);
        assertThat(jettyManager1).isSameAs(jettyManager2);
        */
    }

    @Test
    public void when_created_with_one_validation_and_that_is_satisfied_then_no_exceptions_should_be_thrown() {

        //final MyDataObject myDataObject = ;

        MyDataObject.MyDataObjectBuilder myDataObjectBuilder = MyDataObject.builder();
        //myDataObjectBuilder.

        assertThatCode(() -> MyDataObject.builder(MyDataObjectValidationStrategy.I_IS_7).withI(7).build()).doesNotThrowAnyException();

        final MyDataObject myDataObject = MyDataObject.builder(MyDataObjectValidationStrategy.I_IS_7).withI(7).build();

        /*

        ITestConst.enteringTestHeaderLogger.debug(null);

        final Main main = Main.getSingleton();

        final IFarBackendConfig farBackendConfig = FarBackendConfig.getSingleton();

        final JettyManager  jettyManager1  = main.getJettyManager(farBackendConfig);
        final JettyManager  jettyManager2  = main.getJettyManager(farBackendConfig);

        assertThat(jettyManager1).isEqualTo(jettyManager2);
        assertThat(jettyManager1).isSameAs(jettyManager2);
        */
    }

    void when_referencing_the_builder_then_no_compilation_error_should_occur_since_the_builder_class_is_public() {
        final MyDataObject.MyDataObjectBuilder myDoMyDataObjectBuilder;
    }
}
