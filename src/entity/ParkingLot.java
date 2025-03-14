package entity;

import java.util.List;

public class ParkingLot {
    private int space;
    private List<Vehicle> vehicles;

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ParkingLot() {
    }

    public ParkingLot(int space, List<Vehicle> vehicles) {
        this.space = space;
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "space=" + space +
                ", vehicles=" + vehicles +
                '}';
    }

}
