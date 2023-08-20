package cafe;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Restaurant {
    /*
    Implement the logic of a small restaurant that can only accommodate 5 people at a time.
    People arrive and line up at the entrance, waiting for the tables to become vacant.  Everyone orders a set lunch of 3 dishes (soup, salad and second).
    The restaurant has one waiter and three chefs, each of whom prepares only one dish per person.
    One cook prepares only soups, the second only salads, the third only the second.
    The waiter collects lunch on a tray and takes it to the visitors.
    Diners eat and leave the restaurant, freeing up tables for new diners.
     */

    private Semaphore semaphore = new Semaphore(5);
    private LinkedBlockingQueue<List<Dish>> readyDishes = new LinkedBlockingQueue<>();

    private Chef saladChef = new Chef("Salad chef", 1000L);
    private Chef soupChef = new Chef("Soup chef", 2000L);
    private Chef stakeChef = new Chef("Stake chef", 3000L);

    private Waiter waiter = new Waiter("Waiter-1", List.of(saladChef, soupChef, stakeChef), readyDishes);

    public Restaurant() {
        saladChef.start();
        soupChef.start();
        stakeChef.start();
        waiter.start();
    }

    public void clientArrived() throws InterruptedException {
        semaphore.acquire();
    }

    public List<Dish> serve() throws InterruptedException {
        saladChef.newOrder(Dish.SALAD);
        soupChef.newOrder(Dish.SOUP);
        stakeChef.newOrder(Dish.STAKE);

        return readyDishes.take();
    }

    public void clientLeft() {
        semaphore.release();
    }

    public void close() {
        saladChef.interrupt();
        soupChef.interrupt();
        stakeChef.interrupt();

        waiter.interrupt();
    }


}
