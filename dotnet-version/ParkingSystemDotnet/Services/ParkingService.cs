using System;
using System.Collections.Generic;
using System.Linq;
using ParkingSystemDotnet.Models;

namespace ParkingSystemDotnet.Services
{
    public class ParkingService
    {
        private readonly ParkingLot _parkingLot;

        public ParkingService(ParkingLot parkingLot)
        {
            _parkingLot = parkingLot;
        }

        public void CountFreeSlots()
        {
            Console.WriteLine($"Slots free: {_parkingLot.Space - _parkingLot.Vehicles.Count}");
        }

        public void Status()
        {
            Console.WriteLine("Parking Status");
            if (_parkingLot.Vehicles.Any())
            {
                Console.WriteLine("Slot\t\tNo.\t\tType\t\tColor");
                foreach (var vehicle in _parkingLot.Vehicles)
                {
                    Console.WriteLine($"{vehicle.QueueNo}.\t\t{vehicle.PlateNo}\t\t{vehicle.Type}\t\t{vehicle.Color}");
                }
            }
            else Console.WriteLine("Parking Lot is empty");
        }

        public void Park(string details)
        {
            var info = details.Split(' ');
            int queue = Enumerable.Range(1, _parkingLot.Space)
                .FirstOrDefault(i => !_parkingLot.Vehicles.Any(v => v.QueueNo == i));

            if (queue != 0)
            {
                var vehicleType = info[2].Equals("mobil", StringComparison.OrdinalIgnoreCase) ? VehicleType.Mobil : VehicleType.Motor;
                var vehicle = new Vehicle(queue, info[0], info[1], vehicleType);
                _parkingLot.Vehicles.Add(vehicle);
                Console.WriteLine($"Allocated slot number: {vehicle.QueueNo}");
            }
            else Console.WriteLine("Parking lot is full");
        }

        public void Leave(int queueNo)
        {
            var vehicle = _parkingLot.Vehicles.FirstOrDefault(v => v.QueueNo == queueNo);
            if (vehicle != null)
            {
                _parkingLot.Vehicles.Remove(vehicle);
                Console.WriteLine($"Slot number {queueNo} is free");
            }
        }

        public void Search(int input, string param)
        {
            switch (input)
            {
                case 1:
                    GetVehiclesPlateNoByColor(param);
                    break;
                case 2:
                    GetVehiclesQueueNoByColor(param);
                    break;
                case 3:
                    GetVehiclesByOddPlate();
                    break;
                case 4:
                    GetVehiclesByEvenPlate();
                    break;
                case 5:
                    CountVehiclesByType(VehicleType.Mobil);
                    break;
                case 6:
                    CountVehiclesByType(VehicleType.Motor);
                    break;
                case 7:
                    GetSlotNumberByPlateNumber(param);
                    break;
            }
        }

        private void GetVehiclesQueueNoByColor(string color)
        {
            Console.WriteLine(string.Join(", ", _parkingLot.Vehicles.Where(v => v.Color.Equals(color, StringComparison.OrdinalIgnoreCase)).Select(v => v.QueueNo)));
        }

        private void GetVehiclesPlateNoByColor(string color)
        {
            Console.WriteLine(string.Join(", ", _parkingLot.Vehicles.Where(v => v.Color.Equals(color, StringComparison.OrdinalIgnoreCase)).Select(v => v.PlateNo)));
        }

        private void CountVehiclesByType(VehicleType type)
        {
            Console.WriteLine(_parkingLot.Vehicles.Count(v => v.Type == type));
        }

        private void GetSlotNumberByPlateNumber(string plateNumber)
        {
            var vehicle = _parkingLot.Vehicles.FirstOrDefault(v => v.PlateNo.Equals(plateNumber, StringComparison.OrdinalIgnoreCase));
            Console.WriteLine(vehicle != null ? vehicle.QueueNo.ToString() : "Plate number not found");
        }

        private void GetVehiclesByEvenPlate()
        {
            foreach (var vehicle in _parkingLot.Vehicles)
            {
                if (int.TryParse(vehicle.PlateNo.Split('-')[1], out int numbers) && numbers % 2 == 0)
                {
                    Console.WriteLine($"{vehicle.QueueNo}.\t{vehicle.PlateNo}");
                }
            }
        }

        private void GetVehiclesByOddPlate()
        {
            foreach (var vehicle in _parkingLot.Vehicles)
            {
                if (int.TryParse(vehicle.PlateNo.Split('-')[1], out int numbers) && numbers % 2 != 0)
                {
                    Console.WriteLine($"{vehicle.QueueNo}.\t{vehicle.PlateNo}");
                }
            }
        }
    }
}
