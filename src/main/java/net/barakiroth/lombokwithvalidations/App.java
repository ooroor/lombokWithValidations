package net.barakiroth.lombokwithvalidations;

import net.barakiroth.lombokwithvalidations.domain.Customer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final App app = new App();
        app.run();
    }

    private void run() {
        /*
        try {
            final MyDataObject myDo = MyDataObject.builder(MyDataObjectValidationStrategy.VALIDATION_STRATEGIES_01).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            final MyDataObject myDo = MyDataObject.builder(MyDataObjectValidationStrategy.VALIDATION_STRATEGIES_02).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final MyDataObject.MyDataObjectBuilder myDoBuilder; // LEGAL: The class is not private
        //final MyDataObject.MyDataObjectBuilder myDoBuilder_ilegal_01 = new MyDataObject.MyDataObjectBuilder(); // ILLEGAL: 'MyDataObjectBuilder()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject.MyDataObjectBuilder'

        final MyDataObject.MyDataObjectBuilder myDoBuilder_ilegal_02 = MyDataObject.builder();  // ILLEGAL: Cannot resolve method 'builder' in 'MyDataObject'
        //final MyDataObject.MyDataObjectBuilder myDoBuilder_ilegal_03 = MyDataObject.internalBuilder();  // ILLEGAL: 'internalBuilder()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject'
        final MyDataObject.MyDataObjectBuilder myDoBuilder_01 = MyDataObject.builder(); // LEGAL: The class is not private
        //myDoBuilder_01.internalBuild(); // ILLEGAL - 'internalBuild()' has private access in 'net.barakiroth.lombokexperiments.domain.MyDataObject.MyDataObjectBuilder'
        */
        Customer.CustomerBuilder customerBuilder = Customer.builder(13);
        customerBuilder.withId(100);
        customerBuilder.withName("bob");
        customerBuilder.build();
    }
}
