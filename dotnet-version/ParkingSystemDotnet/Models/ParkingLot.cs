using System.Collections.Generic;
using ParkingSystemDotnet.Models;

namespace ParkingSystemDotnet.Models
{
    public class ParkingLot
    {
        public int Space { get; private set; }
        public List<Vehicle> Vehicles { get; private set; }

        public ParkingLot(int space, List<Vehicle> vehicles)
        {
            Space = space;
            Vehicles = vehicles;
        }
    }
}