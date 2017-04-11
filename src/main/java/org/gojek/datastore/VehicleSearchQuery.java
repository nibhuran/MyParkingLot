package org.gojek.datastore;

public class VehicleSearchQuery {
    private String color;
    private String registrationNo;

    VehicleSearchQuery() {
    }

    public String getColor() {
        return color;
    }

    void setColor(String color) {
        this.color = color;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
}
