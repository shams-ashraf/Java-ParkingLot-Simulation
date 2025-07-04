import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ParkingSimulator simulator = new ParkingSimulator();
        List<Car> cars = new ArrayList<>();

        try {
            cars = simulator.loadCarsFromFile("input.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Car car : cars) {
            try {
                car.join(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ParkingLotLogger.getInstance().printSummary();
    }
}
