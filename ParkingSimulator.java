import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ParkingSimulator {
    private final List<Gate> gates = new ArrayList<>();
    public ParkingSimulator() {
        gates.add(new Gate("Gate 1"));
        gates.add(new Gate("Gate 2"));
        gates.add(new Gate("Gate 3"));
    }
   
    public List<Car> loadCarsFromFile(String filename) throws Exception {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String gateName = parts[0];
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split(" ")[1]);

                Gate gate = gates.stream().filter(g -> g.getName().equals(gateName)).findFirst().orElse(null);
                if (gate != null) {
                    Car car = gate.createCar(carId, arrivalTime, parkingDuration);
                    cars.add(car);
                    car.start(); 
                }
            }
        }
        return cars; 
    }
}