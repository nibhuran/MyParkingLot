package org.gojek;

import org.gojek.datastore.VehicleSearchQueryBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    IParkingLotSystem parkingLotSystem = null;
    /**
     * Usage :
     * park regNo colour
     * leave slotNo
     * status
     * search registration_numbers_for_cars_with_colour, slot_numbers_for_cars_with_colour, slot_number_for_registration_number
     * @param args the capacity of the lot
     */
    public static void main(String[] args) {
        Main main = new Main();
        if(args.length == 1) {
            try {
                String fileName = args[0];
                System.out.println(fileName);
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                for (String input : lines){
                    try {
                        if("exit".equals(input.trim())) break;
                        main.processInput(main.parkingLotSystem, input.split(" "));
                    } catch (Throwable e){
                        System.out.println(e.getMessage());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(args.length == 0){
            Scanner scanner = new Scanner(System.in);
            String input = "";
            while(!"exit".equals(input.trim())){
                try {
                    input = scanner.nextLine();
                    main.processInput(main.parkingLotSystem, input.split(" "));
                } catch (Throwable e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            throw new RuntimeException("Invalid Arguments");
        }
    }

    private void processInput(IParkingLotSystem parkingLotSystem, String[] inputs) {
        String command = inputs[0].trim();
        if(command.equals("create_parking_lot")){
            //alternate for making ParkingLotSystem singleton
            synchronized(this) {
                if (this.parkingLotSystem == null) {
                    this.parkingLotSystem = new ParkingLotSystem(Integer.parseInt(inputs[1].trim()));
                } else {
                    throw new RuntimeException("Parking Lot already exists");
                }
            }
        } else if(parkingLotSystem == null){
            throw new RuntimeException("Create Parking Lot first");
        } else {
            switch (command) {
                case "park":
                    parkingLotSystem.park(inputs[1].trim(), inputs[2].trim());
                    break;

                case "leave":
                    parkingLotSystem.unPark(Integer.parseInt(inputs[1].trim()));
                    break;

                case "status":
                    parkingLotSystem.showStatus();
                    break;

                case "registration_numbers_for_cars_with_colour":
                    Map<Vehicle, Integer> results = parkingLotSystem.search(new VehicleSearchQueryBuilder().withColor(inputs[1].trim()).build());
                    if (results.size() == 0) {
                        System.out.println("Not found");
                    } else {
                        System.out.println(results.keySet().stream().map(Vehicle::getRegistrationNo).collect(Collectors.joining(", ")));
                    }
                    break;

                case "slot_numbers_for_cars_with_colour":
                    results = parkingLotSystem.search(new VehicleSearchQueryBuilder().withColor(inputs[1].trim()).build());
                    if (results.size() == 0) {
                        System.out.println("Not found");
                    } else {
                        System.out.println(results.values().stream().map(Object::toString).collect(Collectors.joining(", ")));
                    }
                    break;

                case "slot_number_for_registration_number":
                    results = parkingLotSystem.search(new VehicleSearchQueryBuilder().withRegistrationNo(inputs[1].trim()).build());
                    if (results.size() == 0) {
                        System.out.println("Not found");
                    } else {
                        System.out.println(results.values().stream().map(Object::toString).collect(Collectors.joining(", ")));
                    }
                    break;
            }
        }
    }
}
