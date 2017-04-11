package org.gojek.datastore;

public class VehicleSearchQueryBuilder {
    private String color;
    private String registrationNo;

    public VehicleSearchQuery build(){
        VehicleSearchQuery vehicleSearchQuery = new VehicleSearchQuery();
        vehicleSearchQuery.setColor(this.color);
        vehicleSearchQuery.setRegistrationNo(this.registrationNo);
        return vehicleSearchQuery;
    }

    public VehicleSearchQueryBuilder withColor(String color){
        this.color = color;
        return this;
    }

    public VehicleSearchQueryBuilder withRegistrationNo(String registrationNo){
        this.registrationNo = registrationNo;
        return this;
    }
}
