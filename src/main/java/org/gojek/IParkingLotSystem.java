package org.gojek;

import org.gojek.datastore.VehicleSearchQuery;

import java.util.Map;


public interface IParkingLotSystem {
    int park(String registrationNo, String color);

    void unPark(Integer parkingSlot);

    void showStatus();

    Map<Vehicle, Integer> search(VehicleSearchQuery vehicleSearchQuery);
}
