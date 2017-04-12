package org.gojek;

import org.gojek.datastore.DefaultIParkingSlotCollection;
import org.gojek.datastore.IParkingSlotCollection;
import org.gojek.datastore.VehicleSearchQuery;

import java.util.Map;

// TODO: 11/04/17 make it Singleton
public class ParkingLotSystem implements IParkingLotSystem {
    public static final String           PARKING_FULL_MESSAGE = "Sorry, parking lot is full";
    private final IParkingSlotCollection parkingSlotCollection;

    ParkingLotSystem(int capacity) {
        this.parkingSlotCollection = new DefaultIParkingSlotCollection(capacity);
    }

    @Override
    public int park(String registrationNo, String color) {
        if (!parkingSlotCollection.isSlotAvailable()) {
            throw new ParkingFullException(PARKING_FULL_MESSAGE);
        }
        //to factory
        Vehicle vehicleToPark = new Vehicle(color, registrationNo);
        return parkingSlotCollection.parkVehicle(vehicleToPark);
    }

    @Override
    public void unPark(Integer parkingSlot) {
        if (parkingSlot == null || parkingSlot < 1 || parkingSlot > ((DefaultIParkingSlotCollection) parkingSlotCollection).getCapacity()) {
            throw new IllegalArgumentException("Invalid slot number");
        }
        parkingSlotCollection.unParkVehicle(parkingSlot);
    }

    @Override
    public void showStatus() {
        Map<Integer, Vehicle> allParkingSlots = parkingSlotCollection.getAll();
        prettyPrintParkingSlots(allParkingSlots);
    }

    @Override
    public Map<Vehicle, Integer> search(VehicleSearchQuery vehicleSearchQuery) {
        Map<Vehicle, Integer> result;
        result = parkingSlotCollection.search(vehicleSearchQuery);
        return result;
    }

    private void prettyPrintParkingSlots(Map<Integer, Vehicle> allParkingSlots) {
        System.out.println("Slot No.\tRegistration No\t\tColour");
        for (Map.Entry<Integer, Vehicle> eachParkingSlot : allParkingSlots.entrySet()) {
            Vehicle vehicle = eachParkingSlot.getValue();
            if (vehicle != null) {
                // TODO: 12/04/17 output formatting
                System.out.println(eachParkingSlot.getKey() + ".\t\t\t" + vehicle.getRegistrationNo() + "\t\t" + vehicle.getColor());
            }
        }
    }
}
