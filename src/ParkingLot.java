import java.util.*;

public class ParkingLot {
    private static int parkingCapacity;
    private static PriorityQueue<Integer> availableSlots;
    public static Map<String, List<Vehicle>> particularColourWithVehicle;
    public static Map<Integer, Vehicle> slotNoWithCar;
    public static Map<String, Integer> regNumWithSlotNum;

    private static final String REGISTER_NUMBER_REGEX = "^[A-Z]{2}-[0-9]{2}-[A-Z]{2}-[0-9]{4}$";


    public ParkingLot(int size) {
        parkingCapacity = size;
        particularColourWithVehicle = new HashMap<>();
        regNumWithSlotNum = new HashMap<>();
        slotNoWithCar = new HashMap<>();
        availableSlots = new PriorityQueue<>(parkingCapacity);
        for(int ind = 1; ind <= size; ind++) {
            availableSlots.offer(ind);
        }
    }

    public static void park(String regNum, String carColour) {
        if(!regNum.matches(REGISTER_NUMBER_REGEX)) {
            System.out.println("Register Number Not Valid, The Valid Format is KA-01-HH-9999");
            return;
        }
        if (availableSlots.isEmpty()) {
            System.out.println("Sorry, parking lot is full");
            return;
        }
        if(regNumWithSlotNum.containsKey(regNum)) {
            System.out.println("Registration Number with " + regNum + " is already Parked.");
            return;
        }
        int slot = availableSlots.poll();
        Vehicle parkingVehicle = new Vehicle(regNum, carColour, slot);

        if(particularColourWithVehicle.containsKey(carColour)) {
            particularColourWithVehicle.get(carColour).add(parkingVehicle);
        } else {
            List<Vehicle> parkingVehicles = new ArrayList<>();
            parkingVehicles.add(parkingVehicle);
            particularColourWithVehicle.put(carColour, parkingVehicles);
        }
        regNumWithSlotNum.put(regNum, slot);
        slotNoWithCar.put(slot, parkingVehicle);
        System.out.println("Allocated slot number: " + slot + " For the Registration number: " + regNum);
    }
    
    public void leave(int slot) {
        boolean isParkingSlotEmpty = (availableSlots.size() == parkingCapacity);
        if(isParkingSlotEmpty) {
            System.out.println("Sorry, parking lot is empty");
            return;
        }
        if (availableSlots.contains(slot)) {
            System.out.println("Slot No: " + slot + "is already free.");
            return;
        }
        if(!slotNoWithCar.containsKey(slot)) {
            System.out.println("Slot No: " + slot + " is not exists in the Parking Lot.");
            return;
        }
        Vehicle parkedVehicle =  slotNoWithCar.get(slot);
        particularColourWithVehicle.get(parkedVehicle.getColour()).remove(parkedVehicle);
        regNumWithSlotNum.remove(parkedVehicle.getRegNumber());
        slotNoWithCar.remove(slot);
        availableSlots.add(slot);
        System.out.println("Slot number " + slot + " is free.");
    }

    public void status() {
        if(availableSlots.size() == parkingCapacity) {
            System.out.println("No Vehicles are Parked in the Parking Lot, Parking Lot is Empty.");
            return;
        }
        System.out.println("Slot No. --  Registration No -- Colour");
        for (Map.Entry<Integer, Vehicle> entry : slotNoWithCar.entrySet()) {
            int slot = entry.getKey();
            Vehicle parkedVehicle = entry.getValue();
            String regNum = parkedVehicle.getRegNumber();
            String color = parkedVehicle.getColour();
            System.out.println(slot + " -- " + regNum + " --  " + Character.toUpperCase(color.charAt(0)) + color.substring(1));
        }
    }

    public void registrationNumberForVehiclesWithColor(String colour) {
        if(availableSlots.size() == parkingCapacity) {
            System.out.println("No Vehicles are Parked in the Parking Lot. Parking Lot is Empty");
            return;
        }
        List<Vehicle> parkedVehicles = particularColourWithVehicle.get(colour.toLowerCase());
        if(parkedVehicles.isEmpty()) {
            System.out.println("No " + colour +" colour vehicles are Parked in the Parking Lot.");
            return;
        }
        System.out.println("Registration Number of Vehicles having Color: " + colour);
        for(Vehicle vehicle : parkedVehicles) {
            System.out.println(vehicle.getRegNumber());
        }
    }
    
    public void slotNumberForVehiclesWithParticularColor(String colour) {
        if(availableSlots.size() == parkingCapacity) {
            System.out.println("No Vehicles are Parked in the Parking Lot, Parking Lot is Empty.");
            return;
        }
        List<Vehicle> parkedVehicles = particularColourWithVehicle.get(colour.toLowerCase());
        if(parkedVehicles.isEmpty()) {
            System.out.println("No " + colour +" colour vehicles are Parked in the Parking Lot.");
            return;
        }
        System.out.println("Slot No of Vehicles having Color: " + colour);
        for(Vehicle vehicle : parkedVehicles) {
            System.out.println("Slot No: " + vehicle.getSlotNo());
        }
    }

    public void getSlotNumberOfVehicleForRegistrationNumber(String regNum) {
        if(availableSlots.size() == parkingCapacity) {
            System.out.println("No Vehicles are Parked in the Parking Lot, Parking Lot is Empty.");
            return;
        }
        if(!regNumWithSlotNum.containsKey(regNum)) {
            System.out.println("No Vehicle Parked With Register Number: " + regNum);
        }
        System.out.println("Vehicle with Register Number: " + regNum + " parked in Slot No: " + regNumWithSlotNum.get(regNum));
    }
}
