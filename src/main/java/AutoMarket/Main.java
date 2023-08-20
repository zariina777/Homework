package AutoMarket;

public class Main {
    /*
     * - Create an abstract class (contract) for a car dealership. Come up with common properties for cars
     * - Implement several specific classes of cars.
     * - Make a class to manage a car dealership
     * - Add an interface to the system. Put into it a contract for the creation of cars and motorcycles
     */

    public static void main(String[] args) {
        VehicleFactoryImpl vehicleFactory = new VehicleFactoryImpl();
        System.out.println(vehicleFactory.createCar("BMWx5"));
        System.out.println(vehicleFactory.createBike("Motox5"));

        CarStore carStore = new CarStore(vehicleFactory);
        System.out.println(carStore.order("AudiA3"));
    }
}
