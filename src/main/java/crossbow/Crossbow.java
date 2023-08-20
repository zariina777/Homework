package crossbow;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Crossbow {
    /*

    Crossbow

    The robinHood shooter shoots a crossbow in one stream,
    His assistant Servant in another stream brings him a quiver when the arrows run out.
    Logic stub in the Crossbow class. Refine the logic to:
    - The shooting continued indefinitely;
    - The number of arrows brought each time would be determined by the user by entering the number of arrows through the console

     */


        private volatile int arrows = 10;

        private final ReentrantLock lock = new ReentrantLock(); // mutex для синхронизации
        private final Condition hasArrows = lock.newCondition(); // условие "стрелы появились"
        private final Condition hasNoArrows = lock.newCondition(); // условие "стрелы закончились"

        private final Scanner scanner = new Scanner(System.in);

        // стрельба
        public void fire() {
            try {
                while (true) {
                    try {
                        // ставим блокировку
                        lock.lock();

                        // пока нет стрел, ждем события "стрелы появились"
                        while (arrows == 0) {
                            hasArrows.await(); // await - метод Condition, в отличии от wait (метод Object)
                        }

                        System.out.println("Available arrows: " + arrows);

                        // перезаряжаем
                        Thread.sleep(1000);

                        // стреляем
                        while (arrows > 0) {
                            System.out.println("The arrow is " + arrows + " right on the target!");
                            arrows--;
                        }

                        System.out.println("No arrows left");

                        // сигнализируем, что стрелы закончились
                        hasNoArrows.signal();
                    } finally {
                        // снимаем блокировку
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        public void reload() {
            try {
                while (true) {
                    try {
                        // ставим блокировку
                        lock.lock();

                        // пока есть стрелы, ждем события "стрелы кончились"
                        while (arrows > 0) {
                            hasNoArrows.await();
                        }

                        // запрашиваем у пользователя, сколько стрел принести
                        while (true) {
                            System.out.print("Enter number of arrows: ");

                            if (scanner.hasNextInt()) {
                                break;
                            } else {
                                scanner.next();
                            }
                        }
                        int nextArrows = scanner.nextInt();

                        // приносим стрелку стрелы
                        System.out.println("New arrows on the way!");
                        arrows = nextArrows;

                        // сигнализируем, что стрелы появились
                        hasArrows.signal();
                    } finally {
                        // снимаем блокировку
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        public static void main(String[] args) {
            Crossbow crossbow = new Crossbow();

            Thread robinHood = new Thread(crossbow::fire);
            Thread servant = new Thread(crossbow::reload);

            robinHood.start();
            servant.start();

            try {
                robinHood.join();
                servant.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
