package buildings;

public class Main {
    /*
    Construction company:

- there are 4 brigades
    - each team builds 1 house
    - the company is building a village of 10 houses
    - then receives a report from the builders on the costs and calculates the total costs for the construction of the village
    - after which it is taken for the next village of 10 houses

Implement logic with ExecutorService
 How will I need to change the code if the company has:
    (a) Will there be only one permanent team to build?
    TODO You need to change the number of brigades in the call of the Firm constructor to 1 (nBrigades).

b) Will brigades be created according to the number of houses under construction, and then dissolved?
    TODO
        1. Remove the infinite loop in Brigade.
        2. Transfer the creation and launch of commands from the Firm constructor to the Firm run() method, inside while, along with the list of commands.
     */

    public static void main(String[] args) {
        Firm firm = new Firm(4, 10);
        firm.run();
    }


}
