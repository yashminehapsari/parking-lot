using System;
using System.Collections.Generic;
using ParkingSystemDotnet.Services;
using ParkingSystemDotnet.Models;

namespace ParkingSystemDotnet
{
    public class MainService
    {
        public static void Run()
        {
            var scanner = Console.In;
            bool repeat = true;

            Console.WriteLine("Parking lot system\n");
            Console.WriteLine("Creating parking lot,");
            Console.WriteLine("Please input lot's capacity");

            int capacity = int.Parse(Console.ReadLine() ?? "0");
            var lot = new ParkingLot(capacity, new List<Vehicle>());
            Console.WriteLine($"Created a parking lot with {lot.Space} slots");

            var parkingService = new ParkingService(lot);

            while (repeat)
            {
                try
                {
                    Console.WriteLine("\nManagements");
                    Console.WriteLine("1. Check-in vehicle");
                    Console.WriteLine("2. Check-out vehicle");
                    Console.WriteLine("3. Parking lot status");
                    Console.WriteLine("4. Search vehicles");
                    Console.WriteLine("5. Exit system");

                    int selectMenu = int.Parse(Console.ReadLine() ?? "0");

                    switch (selectMenu)
                    {
                        case 1:
                            Console.WriteLine("Input vehicle details with format ([plateNumber] [color] [mobil/motor])");
                            Console.WriteLine("(ex: A-1234-YZ red mobil)");
                            string detail = Console.ReadLine();
                            parkingService.Park(detail);
                            break;

                        case 2:
                            Console.WriteLine("Input slot number");
                            int slot = int.Parse(Console.ReadLine() ?? "0");
                            parkingService.Leave(slot);
                            break;

                        case 3:
                            parkingService.Status();
                            break;

                        case 4:
                            Console.WriteLine("Search:");
                            Console.WriteLine("1. Plate number by color");
                            Console.WriteLine("2. Slot number by color");
                            Console.WriteLine("3. Plate with odd number");
                            Console.WriteLine("4. Plate with even number");
                            Console.WriteLine("5. Count cars");
                            Console.WriteLine("6. Count motorcycles");
                            Console.WriteLine("7. Slot number by Plate number");
                            Console.WriteLine("8. Back");

                            int searchMenu = int.Parse(Console.ReadLine() ?? "0");
                            string searchParam = string.Empty;

                            switch (searchMenu)
                            {
                                case 1:
                                case 2:
                                    Console.WriteLine("Input color: ");
                                    searchParam = Console.ReadLine();
                                    break;
                                case 7:
                                    Console.WriteLine("Input plate number: ");
                                    searchParam = Console.ReadLine();
                                    break;
                                default:
                                    searchParam = null;
                                    break;
                            }

                            parkingService.Search(searchMenu, searchParam);
                            break;

                        case 5:
                            Console.WriteLine("Quitting system");
                            repeat = false;
                            break;

                        default:
                            Console.WriteLine("\nIncorrect input");
                            break;
                    }
                }
                catch (Exception)
                {
                    Console.WriteLine("\nIncorrect input");
                }
            }
        }
    }
}