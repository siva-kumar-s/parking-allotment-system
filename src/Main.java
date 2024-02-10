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
                        if(isParkingLotExists(parkingLot)) {
                            break;
                        }
                        if (tokens.length != 2) {
                            System.out.println("Parking lot Size is Missing or Multiple Inputs Given.");
                            break;
                        }
                        int capacity = Integer.parseInt(tokens[1]);
                        if(capacity <= 0) {
                            System.out.println("Parking lot Size is Missing or Multiple Inputs Given.");
                            return;
                        }
                        parkingLot = new ParkingLot(capacity);
                        System.out.println("Created a parking lot with " + capacity + " slots");
                        break;
                    case "park":
                        if (parkingLot == null) {
                            System.out.println("Please create parking lot first");
                            break;
                        }
                        if (tokens.length != 3) {
                            System.out.println("Missing Inputs either Registration Number or Color or More Inputs Given.");
                            break;
                        }
                        parkingLot.park(tokens[1], tokens[2].toLowerCase());
                        break;
                    case "leave":
                        if (parkingLot == null) {
                            System.out.println("Please create parking lot first");
                            break;
                        }
                        if (tokens.length != 2) {
                            System.out.println("leave command needs one Input (Slot No) is Missing or Multiple Inputs Given.");
                            break;
                        }
                        int slot = Integer.parseInt(tokens[1]);
                        parkingLot.leave(slot);
                        break;
                    case "status":
                        if (parkingLot == null) {
                            System.out.println("Please create parking lot first");
                            break;
                        }
                        if (tokens.length != 1) {
                            System.out.println("Status Command expects No Inputs, Multiple Inputs Given.");
                            break;
                        }
                        parkingLot.status();
                        break;
                    case "registration_numbers_for_cars_with_colour":
                        if (parkingLot == null) {
                            System.out.println("Please create parking lot first");
                            break;
                        }
                        if (tokens.length != 2) {
                            System.out.println("Input Color is Missing or Multiple Inputs Given.");
                            break;
                        }
                        parkingLot.registrationNumberForVehiclesWithColor(tokens[1]);
                        break;
                    case "slot_number_for_registration_number":
                        if (parkingLot == null) {
                            System.out.println("Please create parking lot first");
                            break;
                        }
                        if (tokens.length != 2) {
                            System.out.println("Input RegisterNumber is Missing or Multiple Inputs Given.");
                            break;
                        }
                        parkingLot.getSlotNumberOfVehicleForRegistrationNumber(tokens[1]);
                        break;
                    case "slot_numbers_for_cars_with_colour":
                        if (parkingLot == null) {
                            System.out.println("Please create parking lot first");
                            break;
                        }
                        if (tokens.length != 2) {
                            System.out.println("Input Color is Missing or Multiple Inputs Given.");
                            break;
                        }
                        parkingLot.slotNumberForVehiclesWithParticularColor(tokens[1]);
                        break;
                    case "exit":
                        if (tokens.length != 1) {
                            System.out.println("exit expect no Inputs, Multiple Inputs Given.");
                            break;
                        }
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid command.");
                        System.out.println("Please Enter Valid Command.");
                        printSupportedCommands();
                }
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("Command needed a Positive Number for Parking Size or Slot No Not a String");
                }
                else {
                    System.out.println("Error Occurred Please Restart the System. \n Error Message: " + e.getMessage());
                }
//            e.printStackTrace();
            }
        }
    }

    private static boolean isInvalidArgs(String[] tokens, int len) {
        boolean isInvalid = false;
        if (tokens.length != 2) {
            System.out.println("Parking lot Size is Missing or Multiple Inputs Given.");
            isInvalid = true;
        }
        return isInvalid;
    }

    public static void printSupportedCommands() {
        System.out.println("""
                Supported Commands with explanation:\s
                1. create_parking_lot:  create_parking_lot 6 -> (6) Capacity of the Parking Lot.
                2. park:  park  KA-01-HH-9999 White\s
                3. leave: leave 4 -> (slot) car parked at (slot) to remove
                4. status: print all vehicle details -> Slot No. -- Register No -- Color
                5. registration_numbers_for_cars_with_colour White: (colour) print all vehicle register number with (colour)
                6. slot_numbers_for_cars_with_colour White: (color) print all vehicle slotNo has a colour of (color)
                7. slot_number_for_registration_number KA-01-HH-7777: (regNum) print the slotNo of the vehicle with register Number
                8. exit: exit from the System."""
        );
    }

    public static boolean isParkingLotExists(ParkingLot parkingLot) {
        if (parkingLot != null) {
            System.out.println("Parking Lot is Already Created");
            return true;
        }
        return false;
    }
}