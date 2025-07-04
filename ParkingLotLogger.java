import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLotLogger {
    private static ParkingLotLogger instance;
    private final AtomicInteger totalCarsServed = new AtomicInteger(0);
    private final Map<String, AtomicInteger> carsServedByGate = new HashMap<>();

    private ParkingLotLogger() {}

    public static synchronized ParkingLotLogger getInstance() {
        if (instance == null) {
            instance = new ParkingLotLogger();
        }
        return instance;
    }
    public void log(String message) {
        System.out.println(message);
    }
    public void carServed(String gateName) {
        totalCarsServed.incrementAndGet();
        carsServedByGate.computeIfAbsent(gateName, k -> new AtomicInteger(0)).incrementAndGet();
    }
    public int getTotalCarsServed() {
        return totalCarsServed.get();
    }
    public int getCarsServedByGate(String gateName) {
        return carsServedByGate.getOrDefault(gateName, new AtomicInteger(0)).get();
    }

    public void printSummary() {
        System.out.println("Total Cars Served: " + totalCarsServed.get());
        System.out.println("Current Cars in Parking: 0");
        System.out.println("Details:");
        for (String gate : carsServedByGate.keySet()) {
            System.out.println("- " + gate + " served " + carsServedByGate.get(gate).get() + " cars.");
        }
    }
}
