package com.gft.demos.warehouse.domain.entities.enums;


public enum VehicleTypeEnum {
    CAR(Names.CAR), MOTORCYCLE(Names.MOTORCYCLE);

    public class Names{
        public static final String CAR = "CAR";
        public static final String MOTORCYCLE = "MOTORCYCLE";
    }
    private final String name;

    VehicleTypeEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
