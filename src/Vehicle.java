public class Vehicle {

    private String regNumber;

    private String colour;

    private int slotNo;

    public Vehicle(String regNumber, String colour, int slotNo) {
        this.regNumber = regNumber;
        this.colour = colour;
        this.slotNo = slotNo;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }
}
