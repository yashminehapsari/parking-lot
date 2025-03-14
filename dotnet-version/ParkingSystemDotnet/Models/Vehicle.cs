using ParkingSystemDotnet.Models;

namespace ParkingSystemDotnet.Models
{
    public class Vehicle
    {
    public int QueueNo { get; private set; }
    public string PlateNo { get; private set; }
    public string Color { get; private set; }
    public VehicleType Type { get; private set; }

        public Vehicle(int queueNo, string plateNo, string color, VehicleType type)
        {
            QueueNo = queueNo;
            PlateNo = plateNo;
            Color = color;
            Type = type;
        }

    }
}