package org.gojek;

import org.gojek.datastore.VehicleSearchQueryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ParkingLotSystemTest {

    private IParkingLotSystem parkingLotSystem = new ParkingLotSystem(3);

    @Test
    public void testPark() {
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


}