package net.barakiroth.lombokwithvalidations.supersub;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
@Getter(AccessLevel.PUBLIC)
@SuperBuilder(
        setterPrefix = "with",
        buildMethodName = "internalBuild",
        builderMethodName = "internalBuilder")
public class MyDataObjectSuper {
    private final int i;
}
