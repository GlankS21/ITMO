import java.util.Random;
/**
 *     private final queue queue; //  очередь с приоритетами
 *     public Producer(queue queue) {
 *         1) this.queue = queue;
 *         2) инициализировать 2 потока producer1 и producer2
 *         3) producer1.start()
 *            producer2.start()
 *     }
 */
class Producer implements Runnable {
    private final queue queue;
    private String name;
    public Producer(queue queue, String name) {
        this.queue = queue;
        this.name = name;
        Thread producer1 = new Thread(this, name);
        producer1.start();
    }
    /**
     *   public void run() {
     *         1) Инициализация random = new Random()
     *         2) Пока true:
     *             2.1. Инициализация count = random(1,3)
     *             2.2. Если i < count:
     *                 2.2.1. Инициализация val = random(10)
     *                 2.2.2. добавить val в queue
     *             2.3. Выбросить исключение (если есть)
     *     }
     */
    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                int count = random.nextInt(1,3);
                for (int i = 0; i < count; i++) {
                    queue.insert(random.nextInt(1,10)); //добавить случайно значение
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
