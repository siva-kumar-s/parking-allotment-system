import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Parking Allotment System");
        printSupportedCommands();
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = null;

        while (true) {
            try {
                System.out.print("Input: ");
                String input = scanner.nextLine();
                String[] tokens = input.split("\\s+");
                String command = tokens[0];
                switch (command) {
                    case "create_parking_lot":
                        parkingLot = createParkingLot(tokens, parkingLot);
                        break;
                    case "park":
                        parkVehicle(tokens, parkingLot);
                        break;
                    case "leave":
                        leaveParking(tokens, parkingLot);
                        break;
                    case "status":
                        getStatus(parkingLot);
                        break;
                    case "registration_numbers_for_cars_with_colour":
                        getRegistrationNumbersByColor(tokens, parkingLot);
                        break;
                    case "slot_number_for_registration_number":
                        getSlotNumberByRegistration(tokens, parkingLot);
                        break;
                    case "slot_numbers_for_cars_with_colour":
                        getSlotNumbersByColor(tokens, parkingLot);
                        break;
                    case "exit":
                        exitSystem(scanner);
                        break;
                    default:
                        System.out.println("Invalid command.");
                        printSupportedCommands();
                }
            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    private static ParkingLot createParkingLot(String[] tokens, ParkingLot parkingLot) {
        if (isParkingLotExists(parkingLot) || isNotValidToken(tokens, 2, "Parking lot Size is Missing or Multiple Inputs Given.")) {
            return null;
        }
        int capacity = Integer.parseInt(tokens[1]);
        if (isNegative(capacity)) return null;
        System.out.println("Created a parking lot with " + capacity + " slots");
        return new ParkingLot(capacity);
    }

    private static void parkVehicle(String[] tokens, ParkingLot parkingLot) {
        if (isParkingLotNotExists(parkingLot) || isNotValidToken(tokens, 3, "Missing Inputs either Registration Number or Color or More Inputs Given.")) {
            return;
        }
        parkingLot.park(tokens[1], tokens[2].toLowerCase());
    }

    private static void leaveParking(String[] tokens, ParkingLot parkingLot) {
        if (isParkingLotNotExists(parkingLot) || isNotValidToken(tokens, 2, "leave command needs one Input (Slot No) is Missing or Multiple Inputs Given.")) {
            return;
        }
        int slot = Integer.parseInt(tokens[1]);
        if (isNegative(slot)) return;
        parkingLot.leave(slot);
    }

    private static void getStatus(ParkingLot parkingLot) {
        if (isParkingLotNotExists(parkingLot)) {
            return;
        }
        parkingLot.status();
    }

    private static void getRegistrationNumbersByColor(String[] tokens, ParkingLot parkingLot) {
        if (isParkingLotNotExists(parkingLot) || isNotValidToken(tokens, 2, "Input Color is Missing or Multiple Inputs Given.")) {
            return;
        }
        parkingLot.registrationNumberForVehiclesWithColor(tokens[1]);
    }

    private static void getSlotNumberByRegistration(String[] tokens, ParkingLot parkingLot) {
        if (isParkingLotNotExists(parkingLot) || isNotValidToken(tokens, 2, "Input RegisterNumber is Missing or Multiple Inputs Given.")) {
            return;
        }
        parkingLot.getSlotNumberOfVehicleForRegistrationNumber(tokens[1]);
    }

    private static void getSlotNumbersByColor(String[] tokens, ParkingLot parkingLot) {
        if (isParkingLotNotExists(parkingLot) || isNotValidToken(tokens, 2, "Input Color is Missing or Multiple Inputs Given.")) {
            return;
        }
        parkingLot.slotNumberForVehiclesWithParticularColor(tokens[1]);
    }

    private static void exitSystem(Scanner scanner) {
        System.out.println("Exiting...");
        scanner.close();
        System.out.println("Thank You!!");
        System.exit(0);
    }

    private static boolean isNegative(int capacity) {
        if (capacity <= 0) {
            System.out.println("Parking lot Size Need to be Positive and Non Zero.");
            return true;
        }
        return false;
    }

    private static void printSupportedCommands() {
        System.out.println("""
                Supported Commands with explanation:\s
                1. create_parking_lot:  create_parking_lot 6 -> (6) Capacity of the Parking Lot.
                2. park:  park  KA-01-HH-9999 White\s
                3. leave: leave 4 -> (slot) car parked at (slot) to remove
                4. status: print all vehicle details -> Slot No. -- Register No -- Color
                5. registration_numbers_for_cars_with_colour White: (colour) print all vehicle register number with (colour)
                6. slot_numbers_for_cars_with_colour White: (color) print all vehicle slotNo has a colour of (color)
                7. slot_number_for_registration_number KA-01-HH-7777: (regNum) print the slotNo of the vehicle with register Number
                8. exit: exit""");
    }

    private static boolean isParkingLotNotExists(ParkingLot parkingLot) {
        if (parkingLot == null) {
            System.out.println("Parking Lot is Not Created.");
            return true;
        }
        return false;
    }

    private static boolean isParkingLotExists(ParkingLot parkingLot) {
        if (parkingLot != null) {
            System.out.println("Parking Lot is Already Created.");
            return true;
        }
        return false;
    }

    private static boolean isNotValidToken(String[] tokens, int len, String message) {
        if (tokens.length != len) {
            System.out.println(message);
            return true;
        }
        return false;
    }

    private static void handleException(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.println("Command needed a Positive Number for Parking Size and Slot No Not a Character");
        } else {
            System.out.println("Error Occurred Please Restart the System. \n Error Message: " + e.getMessage());
        }
    }
}
