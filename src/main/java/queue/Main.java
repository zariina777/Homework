package queue;

public class Main {
     /*
    BlockingQueue:
    Producer class - generates messages to the queue
    Consumer class - picks up messages from the queue

    The task is to implement the Consumer class, which will parse messages in the queue and finish when an incoming "exit" message arrives.
    When implementing, you can use the MyBlockingQueue or ReentrantLockBlockingQueue class in the repository
    or one of the standard implementations of BlockingQueue from the library.
     */
     public static void main(String[] args) {
         ReentrantLockBlockingQueue<String> reentrantLockBlockingQueue = new ReentrantLockBlockingQueue<>(5);
         QueueProducer producer = new QueueProducer(reentrantLockBlockingQueue);
         QueueConsumer consumer = new QueueConsumer(reentrantLockBlockingQueue);

         producer.start();
         consumer.start();

         try {
             producer.join();
             reentrantLockBlockingQueue.put(QueueConsumer.EXIT);
             consumer.join();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
}
