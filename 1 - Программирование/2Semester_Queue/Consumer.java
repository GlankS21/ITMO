/**
 *  import java.util.Random;
 *     class Consumer implements Runnable{
 *         private queue queue; //  очередь с приоритетами
 *         private int val;
 *         Consumer(queue queue, int val){
 *             1) this.queue = queue;
 *             2) this.val = val;
 *             3) инициализировать потока consumer
 *             4) consumer.start()
 *         }
 */
public class Consumer implements Runnable {
    private final queue queue;
    private final int max;
    public Consumer(queue queue, int max) {
        this.queue = queue;
        this.max = max;
        Thread consumer = new Thread(this);
        consumer.setName((max <= 5) ? "Consumer 1 (1-5)": "Consumer 2 (6-10)"); // Менять имя, чтобы легче проверить
        consumer.start();
    }
    /**
     *     @Override
     *     public void run() {
     *         1) Пока: true
     *             1.1 try
     *                 мы удалим максимальное значение (max)
     *             1.2. Выбросить исключение (если есть)
     *     }
     * }
     */
    @Override
    public void run() {
        while(true){
            try {
                queue.deleteMax(max); // удалить максимальное значение
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}