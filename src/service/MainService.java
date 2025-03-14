package service;

import entity.ParkingLot;

import java.util.ArrayList;
import java.util.Scanner;

public class MainService {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        System.out.println("Parking lot system\n");
        System.out.println("Creating parking lot,");
        System.out.println("Please input lot's capacity");

        ParkingLot lot = new ParkingLot(scanner.nextInt(),new ArrayList<>());
        System.out.println("Created a parking lot with "+lot.getSpace()+" slots");

        ParkingService parkingService = new ParkingService(lot);

        while (repeat) {
            try {
                System.out.println("\nManagements");
                System.out.println("1. Check-in vehicle");
                System.out.println("2. Check-out vehicle");
                System.out.println("3. Parking lot status");
                System.out.println("4. Search vehicles");
                System.out.println("5. Exit system");
                int selectMenu = scanner.nextInt();
                scanner.nextLine();


                switch (selectMenu) {
                    case 1:
                        System.out.println("input vehicle details with format ([plateNumber] [color] [mobil/motor])");
                        System.out.println("(ex: A-1234-YZ red mobil)");
                        String detail = scanner.nextLine();
                        parkingService.park(detail);
                        break;
                    case 2:
                        System.out.println("input slot number");
                        int slot = scanner.nextInt();
                        scanner.nextLine();
                        parkingService.leave(slot);
                        break;
                    case 3:
                        parkingService.status();
                        break;
                    case 4:
                        System.out.println("Search:");
                        System.out.println("1. Plate number by color");
                        System.out.println("2. Slot number by color");
                        System.out.println("3. Plate with odd number");
                        System.out.println("4. Plate with even number");
                        System.out.println("5. Count cars");
                        System.out.println("6. Count motorcycles");
                        System.out.println("7. Slot number by Plate number");
                        System.out.println("8. Back");
                        int searchMenu = scanner.nextInt();
                        scanner.nextLine();
                        String searchParam="";
                        switch (searchMenu) {
                            case 1,2:
                                System.out.println("input color: ");
                                searchParam = scanner.nextLine();
                                break;
                            case 7:
                                System.out.println("input plate number: ");
                                searchParam = scanner.nextLine();
                                break;
                            default:
                                searchParam = null;
                                break;
                        }
                        parkingService.search(searchMenu,searchParam);
                        break;
                    case 5:
                        System.out.println("Quitting system");
                        repeat = false;
                        break;
                    default:
                        System.out.println();
                        System.out.println("Incorrect input");
                        break;
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("Incorrect input");
                scanner.nextLine();
            }
        }
    }
}
