package org.gojek.datastore;

import org.gojek.Vehicle;

import java.util.Map;


public interface IParkingSlotCollection {
    Map<Integer, Vehicle> getAll();

    boolean isSlotAvailable();

    int parkVehicle(Vehicle vehicleToPark);

    void unParkVehicle(Integer parkingSlot);

    Map<Vehicle, Integer> search(VehicleSearchQuery vehicleSearchQuery);
}
