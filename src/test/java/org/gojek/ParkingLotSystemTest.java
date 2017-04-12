package org.gojek;

import org.gojek.datastore.VehicleSearchQueryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ParkingLotSystemTest {

    private      IParkingLotSystem parkingLotSystem = null;

    @Test
    public void testPark() {
        parkingLotSystem = new ParkingLotSystem(3);
        int first = parkingLotSystem.park("r1", "black");
        int second = parkingLotSystem.park("r2", "white");
        int third = parkingLotSystem.park("r3", "black");

        Assert.assertEquals("first parking slot", 1, first);
        Assert.assertEquals("second parking slot", 2, second);
        Assert.assertEquals("third parking slot", 3, third);

        Map<Vehicle, Integer> searchResult = parkingLotSystem.search(new VehicleSearchQueryBuilder().withColor("black").build());
        Assert.assertEquals(2, searchResult.entrySet().size());

        parkingLotSystem.unPark(3);
        searchResult = parkingLotSystem.search(new VehicleSearchQueryBuilder().withColor("black").build());
        Assert.assertEquals(1, searchResult.entrySet().size());
    }

    @Test
    public void testDefaultTestCase(){
//        create_parking_lot 6
//        park KA-01-HH-1234 White
//        park KA-01-HH-9999 White
//        park KA-01-BB-0001 Black
//        park KA-01-HH-7777 Red
//        park KA-01-HH-2701 Blue
//        park KA-01-HH-3141 Black
//        leave 4
//        status
//        park KA-01-P-333 White
//        park DL-12-AA-9999 White
//                status
//        registration_numbers_for_cars_with_colour White
//        slot_numbers_for_cars_with_colour White
//        slot_number_for_registration_number KA-01-HH-3141
//        slot_number_for_registration_number MH-04-AY-1111
//        exit

        parkingLotSystem = new ParkingLotSystem(6);
        int first = parkingLotSystem.park("KA-01-HH-1234", "White");
        int second = parkingLotSystem.park("KA-01-HH-9999", "White");
        int third = parkingLotSystem.park("KA-01-BB-0001", "Black");
        int fourth = parkingLotSystem.park("KA-01-HH-7777", "Red");
        int fifth = parkingLotSystem.park("KA-01-HH-2701", "Blue");
        int sixth = parkingLotSystem.park("KA-01-HH-3141", "Black");

        Assert.assertEquals("first parking slot", 1, first);
        Assert.assertEquals("second parking slot", 2, second);
        Assert.assertEquals("third parking slot", 3, third);
        Assert.assertEquals("fourth parking slot", 4, fourth);
        Assert.assertEquals("fifth parking slot", 5, fifth);
        Assert.assertEquals("sixth parking slot", 6, sixth);

        parkingLotSystem.unPark(4);

        int seventh = parkingLotSystem.park("KA-01-P-333", "White");

        Assert.assertEquals("seventh parking slot", 4, seventh);

        try {
            Integer eight = parkingLotSystem.park("DL-12-AA-9999", "White");
            Assert.fail( "Parking Lot should have been full" );
        } catch (ParkingFullException expectedException) {
            Assert.assertTrue(true);
        }

    }
}