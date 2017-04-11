package org.gojek.datastore;

import org.gojek.Vehicle;


class ParkingSlot {
    private final int id;
    private SlotStatus slotStatus;
    private Vehicle vehicle;

    ParkingSlot(int id) {
        this.id = id;
        this.slotStatus = SlotStatus.AVAILABLE;
    }

    int getId() {
        return id;
    }

    SlotStatus getSlotStatus() {
        return slotStatus;
    }

    Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets vehicle in a parking slot. A call to this method makes the slot unavailable for other vehicles.
     * This method is kept package private so that mapping of vehicle to slot could not be modified
     * by any external class outside the data store package
     * @param vehicle vehicle to be stored at this slot
     */
    void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.slotStatus = SlotStatus.OCCUPIED;
    }

    /**
     * Removes vehicle from a parking slot. A call to this method makes the slot available for other vehicles.
     * This method is kept package private so that mapping of vehicle to slot could not be modified
     * by any external class outside the data store package
     * @return the vehicle removed
     */
    Vehicle removeVehicle(){
        Vehicle vehicle = this.vehicle;
        this.vehicle = null;
        this.slotStatus = SlotStatus.AVAILABLE;
        return vehicle;
    }

    @Override
    public String toString() {
        return "Parking Slot id : " + id + " " + vehicle.toString();
    }

    boolean isAvailableToPark() {
        return this.slotStatus == SlotStatus.AVAILABLE;
    }
}
