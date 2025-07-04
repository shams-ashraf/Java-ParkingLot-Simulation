import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ParkingLot {
    private static ParkingLot instance;
    private final Semaphore parkingSpots;
    private int occupiedSpots = 0;
    private long waitingTime = 0;
    private final Map<Car, Integer> entryTimes = new HashMap<>();

    private ParkingLot(int totalSpots) {
        parkingSpots = new Semaphore(totalSpots);
    }

    public static synchronized ParkingLot getInstance(int totalSpots) {
        if (instance == null) {
            instance = new ParkingLot(totalSpots);
        }
        return instance;
    }

    public boolean enterParking(Car car, int entryTime) {
        long waitStart = System.currentTimeMillis();
        boolean carWaited = false;

        if (!parkingSpots.tryAcquire()) {
            ParkingLotLogger.getInstance().log(car + " waiting for a spot.");
            carWaited = true;
            try {
                parkingSpots.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        waitingTime = ((System.currentTimeMillis() - waitStart) / 1000 ) + 1;

        synchronized (this) {
            occupiedSpots++;
            entryTimes.put(car, entryTime);

            if (carWaited) {
                ParkingLotLogger.getInstance().log(car + " parked after waiting " + waitingTime + " units of time. (Parking Status: " + occupiedSpots + " spots occupied)");
            } else {
                ParkingLotLogger.getInstance().log(car + " parked. (Parking Status: " + occupiedSpots + " spots occupied)");
            }
        }
        return true;
    }
    public void exitParking(Car car, int exitTime) {
        Integer entryTime = entryTimes.remove(car);
        if (entryTime != null) {
            int duration = exitTime - entryTime;
            parkingSpots.release();
            synchronized (this) {
                occupiedSpots--;
                ParkingLotLogger.getInstance().log(car + " left after " + duration + " units of time. (Parking Status: " + occupiedSpots + " spots occupied)");
            }
            ParkingLotLogger.getInstance().carServed(car.getGate());

        }
    }
}
