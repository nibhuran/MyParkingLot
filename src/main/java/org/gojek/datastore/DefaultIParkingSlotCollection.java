package org.gojek.datastore;

import org.gojek.ParkingFullException;
import org.gojek.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.gojek.ParkingLotSystem.PARKING_FULL_MESSAGE;

public class DefaultIParkingSlotCollection implements IParkingSlotCollection {
    private final int             capacity;
    private int                   currentCount;
    private List<ParkingSlot>     parkingSlots;
    private Map<Integer, Vehicle> slotToVehicle;

    public DefaultIParkingSlotCollection(int capacity) {
        this.capacity = capacity;
        this.currentCount = 0;
        this.parkingSlots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            parkingSlots.add(new ParkingSlot(i + 1));
        }
        this.slotToVehicle = new HashMap<>(capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Map<Integer, Vehicle> getAll() {
        Map<Integer, Vehicle> allSlotsWithVehicles = new TreeMap<>();
        parkingSlots.stream().filter(parkingSlot -> parkingSlot.getSlotStatus() == SlotStatus.OCCUPIED).forEach(
                parkingSlot -> allSlotsWithVehicles.put(parkingSlot.getId(), parkingSlot.getVehicle()));
        return allSlotsWithVehicles;
    }

    @Override
    public boolean isSlotAvailable() {
        return currentCount < capacity;
    }

    // TODO: 12/04/17 check for duplicates
    @Override
    public synchronized int parkVehicle(Vehicle vehicleToPark) {
        if (!isSlotAvailable()) { //checked again to ensure thread safe nature
            throw new ParkingFullException(PARKING_FULL_MESSAGE);
        }
        ParkingSlot availableSlot = getNextAvailableSlot();
        availableSlot.setVehicle(vehicleToPark);
        slotToVehicle.put(availableSlot.getId(), vehicleToPark);
        currentCount++;
        System.out.println("Allocated slot number: " + availableSlot.getId());
        return availableSlot.getId();
    }

    @Override
    public synchronized void unParkVehicle(Integer parkingSlot) {
        Vehicle vehicle = slotToVehicle.get(parkingSlot);
        if (vehicle == null) {
            throw new RuntimeException("Slot is vacant");
        }
        slotToVehicle.remove(parkingSlot);
        parkingSlots.get(parkingSlot - 1).removeVehicle();
        currentCount--;
        System.out.println("Slot number " + parkingSlot + " is free");
    }

    private ParkingSlot getNextAvailableSlot() {
        for (ParkingSlot eachSlot : parkingSlots) {
            if (eachSlot.isAvailableToPark()) {
                return eachSlot;
            }
        }
        return null;
    }

    @Override
    public Map<Vehicle, Integer> search(VehicleSearchQuery vehicleSearchQuery) {
        Map<Vehicle, Integer> result = new HashMap<>();
        slotToVehicle.entrySet().stream().filter(eachVehicleAndSlot -> eachVehicleAndSlot.getValue().matches(vehicleSearchQuery)).forEach(
                eachVehicleAndSlot -> result.put(eachVehicleAndSlot.getValue(), eachVehicleAndSlot.getKey()));
        return result;
    }
}
