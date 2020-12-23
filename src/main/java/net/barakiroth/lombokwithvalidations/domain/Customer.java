package net.barakiroth.lombokwithvalidations.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder(
        setterPrefix = "with",
        builderClassName = "CustomerBuilder",
        buildMethodName = "build",
        builderMethodName = "builder"
)
@AllArgsConstructor
public class Customer {
    private final long id;
    private final String name;

    public static CustomerBuilder builder(final int xxxx) {
        final CustomerBuilder customerBuilder = Customer.internalBuilder();
        customerBuilder.setXxxx(78);

        return customerBuilder;
    }

    private static CustomerBuilder internalBuilder() {
        final CustomerBuilder customerBuilder = new CustomerBuilder();
        return customerBuilder;
    }

    public static class CustomerBuilder {

        @Setter
        int xxxx;

        public Customer build() {
            return new Customer(this.id, this.name);
        }

    }
}
