package service;

import entity.ParkingLot;
import entity.Vehicle;
import entity.VehicleType;

public class ParkingService {
    private final ParkingLot parkingLot;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void countFreeSlots() {
        System.out.println("Slots free: "+(parkingLot.getSpace()-parkingLot.getVehicles().size()));
    }

    public void status() {
        System.out.println("Parking Status");
        if (!parkingLot.getVehicles().isEmpty()) {
            System.out.println("Slot\t\tNo.\t\tType\t\tColor");
            for (Vehicle vehicle : parkingLot.getVehicles()) {
                System.out.println(vehicle.getQueueNo() + ".\t\t" + vehicle.getPlateNo() + "\t\t" + vehicle.getType() + "\t\t" + vehicle.getColor());
            }
        }else System.out.println("Parking Lot is empty");
    }

    public void park(String details) {
        String[] info = details.split(" ");
        int queue = 0;
        for (int i = 1; i <= parkingLot.getSpace(); i++) {
            boolean isEmpty = true;
            for (Vehicle vehicle:parkingLot.getVehicles()) {
                if (vehicle.getQueueNo()==i) {
                    isEmpty=false;
                    break;
                }
            }if (isEmpty) {
                queue=i;
                break;
            }
        }
        if (queue!=0) {
            Vehicle vehicle = new Vehicle(queue,info[0],info[1],info[2].equalsIgnoreCase("mobil")? VehicleType.MOBIL:VehicleType.MOTOR);
            parkingLot.getVehicles().add(vehicle);
            System.out.println("Allocated slot number: " + vehicle.getQueueNo());
        }else System.out.println("Parking lot is full");
    }

    public void leave(int queueNo) {
        for (Vehicle vehicle:parkingLot.getVehicles()) {
            if (vehicle.getQueueNo()==queueNo) {
                parkingLot.getVehicles().remove(vehicle);
            }
        }
        System.out.println("Slot number "+queueNo+" is free");
    }

    public void search(int input, String param) {
        switch (input) {
            case 1:
                getVehiclesPlateNoByColor(param);
                break;
            case 2:
                getVehiclesQueueNoByColor(param);
                break;
            case 3:
                getVehiclesByOddPlate();
                break;
            case 4:
                getVehiclesByEvenPlate();
                break;
            case 5:
                countVehiclesByType(VehicleType.MOBIL);
                break;
            case 6:
                countVehiclesByType(VehicleType.MOTOR);
                break;
            case 7:
                getSlotNumberByPlateNumber(param);
                break;
        }
    }
    public void getVehiclesQueueNoByColor(String color) {
        for (Vehicle vehicle:parkingLot.getVehicles()) {
            if (vehicle.getColor().equalsIgnoreCase(color))
                System.out.print(vehicle.getQueueNo()+", ");
        }
    }

    public void getVehiclesPlateNoByColor(String color) {
        for (Vehicle vehicle:parkingLot.getVehicles()) {
            if (vehicle.getColor().equalsIgnoreCase(color))
                System.out.print(vehicle.getPlateNo()+", ");
        }
    }

    public void countVehiclesByType(VehicleType type) {
        int count = 0;
        for (Vehicle vehicle:parkingLot.getVehicles()) {
            if (vehicle.getType().equals(type))
                count+=1;
        }
        System.out.println(count);
    }

    public void getSlotNumberByPlateNumber(String plateNumber) {
        boolean isExist = false;
        for (Vehicle vehicle:parkingLot.getVehicles()) {
            if (vehicle.getPlateNo().equalsIgnoreCase(plateNumber)) {
                System.out.println(vehicle.getQueueNo());
                isExist = true;
            }
        }
        if (!isExist)
            System.out.println("Plate number not found");
    }

    public void getVehiclesByEvenPlate() {
        for (Vehicle vehicle:parkingLot.getVehicles()) {
            String[] plate = vehicle.getPlateNo().split("-");
            int numbers = Integer.parseInt(plate[1]);
            if (numbers%2==0)
                System.out.println(vehicle.getQueueNo()+".\t"+vehicle.getPlateNo());
        }
    }

    public void getVehiclesByOddPlate() {
        for (Vehicle vehicle:parkingLot.getVehicles()) {
            String[] plate = vehicle.getPlateNo().split("-");
            int numbers = Integer.parseInt(plate[1]);
            if (numbers%2!=0)
                System.out.println(vehicle.getQueueNo()+".\t"+vehicle.getPlateNo());
        }
    }
}
