package car;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Main {
    /*
    Write a Car class that will have the following fields:
     -Color
     - boolean - sporty or not
     - Enum brand
     - int maximum speed

Write a Developer class that will:
     -name
     -surname
     -age
     -salary
     - char gender
     -machine
     Write a Generator class that generates Developers and Cars.
    The generator will have two methods that will generate machines and developers depending on the quantity,
     that you'll pass to the parameter.

(To help the Faker library to generate any names, surnames, etc.)
Write a Handler class that will:
    - go through the Developers sheet and bring out all the men who have a sports car
    - group all cars by gender (some for men, others for women)
    - Translate the key-Developer into a Map, and the value is the maximum speed of the car
    - write to a text file of all developers in the format: Jonn-[auto: BMW ::: salary: 12345]
     in a column. That is, only this information is needed in the file
     */
    public static void main(String[] args) {
        Generator generator = new Generator(new Faker(), new Random());
        Handler handler = new Handler();

        List<Developer> developers = generator.createNewRandomDeveloper(10);
        System.out.println(developers);

        handler.printAllMenWithSportCar(developers);
        handler.printCarsByGender(developers);

        System.out.println(handler.getDeveloperWithMaxSpeedOfCar(developers));

        try {
            handler.writeDevelopersToFile(developers);
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }
}
