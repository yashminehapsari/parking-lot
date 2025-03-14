package entity;

import java.time.LocalDateTime;

public class Vehicle {
    private int queueNo;
    private String plateNo;
    private String color;
    private VehicleType type;

    public int getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(int queueNo) {
        this.queueNo = queueNo;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }


    public Vehicle() {
    }

    public Vehicle(int queueNo, String plateNo, String color, VehicleType type) {
        this.queueNo = queueNo;
        this.plateNo = plateNo;
        this.color = color;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "queueNo=" + queueNo +
                ", plateNo='" + plateNo + '\'' +
                ", color='" + color + '\'' +
                ", type=" + type +
                '}';
    }
}
