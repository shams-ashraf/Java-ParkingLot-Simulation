public class Gate {
    private final String gateName;

    public Gate(String gateName) {
        this.gateName = gateName;
    }

    public Car createCar(int carId, int arrivalTime, int parkingDuration) {
        return new Car(carId, arrivalTime, parkingDuration, gateName);
    }

    public String getName(){
        return this.gateName;
    }
}
