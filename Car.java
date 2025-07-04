import java.util.concurrent.locks.Lock;

public class Car extends Thread {
    private final int carId;
    private final int arrivalTime;
    private final int parkingDuration;
    private final String gateName;
    private final ParkingLot parkingLot;

    public Car(int carId, int arrivalTime, int parkingDuration, String gateName) {
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.gateName = gateName;
        this.parkingLot = ParkingLot.getInstance(4);
        
    }
    @Override
    public void run() {
        try {
            Thread.sleep(arrivalTime * (1000L));
            ParkingLotLogger.getInstance().log(this + " arrived at time " + arrivalTime);
            if (parkingLot.enterParking(this, arrivalTime)) {
                Thread.sleep(parkingDuration * (1000L));
                int exitTime = arrivalTime + parkingDuration;
                parkingLot.exitParking(this, exitTime);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Car " + carId + " from " + gateName;
    }

    public String getGate() {
        return gateName;
    }
}
   

