package org.gojek;



import org.gojek.datastore.VehicleSearchQuery;

/**
 * Encapsulation of a vehicle. Vehicle properties such as registration number, color are present.
 */
public class Vehicle {
    private String color;
    private String registrationNo;

    Vehicle(String color, String registrationNo) {
        this.color = color;
        this.registrationNo = registrationNo;
    }

    String getColor() {
        return color;
    }

    String getRegistrationNo() {
        return registrationNo;
    }

    @Override
    public int hashCode() {
        return registrationNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj ||
                (obj != null
                && obj instanceof Vehicle
                && ((Vehicle) obj).getRegistrationNo().equals(registrationNo));
    }

    @Override
    public String toString() {
        return "Vehicle : [ " +
                color + " " +
                "reg no : " + registrationNo +
                " ]";
    }

    public boolean matches(VehicleSearchQuery vehicleSearchQuery) {
        return (vehicleSearchQuery.getColor() == null || this.color.equals(vehicleSearchQuery.getColor().trim()))
                && (vehicleSearchQuery.getRegistrationNo() == null || this.registrationNo.equals(vehicleSearchQuery.getRegistrationNo().trim()));
    }
}
