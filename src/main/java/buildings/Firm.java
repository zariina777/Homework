package buildings;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Firm {


        private final static long BRIGADE_DURATION = 2000L;

        private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        private Phaser phaser;
        private List<Brigade> brigades;
        private ExecutorService executor;

        public Firm(int nBrigades, int nBuildings) {
            this.phaser = new Phaser(nBuildings); // Для отсечек по nBuildings - домов
            this.executor = Executors.newFixedThreadPool(nBrigades); // Для запуска потоков бригад
            this.brigades = new ArrayList<>(); // Для того, чтобы иметь возможность по ним итерировать

            // Создаем и запускаем бригады
            for (int i = 0; i < nBrigades; ++i) {
                Brigade brigade = new Brigade(phaser, queue, BRIGADE_DURATION);
                brigades.add(brigade);
                executor.submit(brigade);
            }
        }

        @Override
        public void run() {
            try {
                // Бесконечным циклом наполняем очередь на постройку домов
                while (!Thread.currentThread().isInterrupted()) {
                    int phase = phaser.getPhase();

                    for (int i = 0; i < phaser.getRegisteredParties(); i++) {
                        queue.put(phase + "-" + i);
                    }
                    // Ожидаем смены фазы
                    phaser.awaitAdvance(phase);

                    // Собираем с бригад суммарное число затраченных ими ресурсов
                    int sumResources = brigades.stream().mapToInt(Brigade::getResources).sum();

                    System.out.println("Finished building village " + phase + ". Used resources: " + sumResources);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
}
