import java.util.concurrent.locks.*;
public class queue implements PriorityQueue{
    private final int[] queue;
    private int size;
    private final ReentrantLock lock;
    private final Condition notFull;  // Проверка пуст ли массив
    private final Condition notEmpty; // Проверка массив заполнен
    private final Condition lessThan5; // Проверка максимальное значение <= 5 или нет
    private final Condition greaterThan5; // Проверка максимальное значение > 5 или нет
    /**
     * 1) Выделение памяти под массив
     * 2) Инициализация переменных size = 0
     * 3) Инициализация Блокировки lock = new ReentrantLock()
     * 4) Инициализация Condition notFull = lock.newCondition()
     * 5) Инициализация Condition notEmpty = lock.newCondition()
     * 6) Инициализация Condition lessThan5 = lock.newCondition()
     * 7) Инициализация Condition greaterThan5 = lock.newCondition()
     */
    public queue() {
        queue = new int[10];
        size = 0;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        lessThan5 = lock.newCondition();
        greaterThan5 = lock.newCondition();
    }
    /**
     * 1) lock.lock()
     * 2) Try
     *      2.1. пока size == 10 (full()) => notFull.await()
     *      2.2. добавить val в конец массива - это позиция current
     *      2.3. пока queue[current] > queue[parent] (parent = (current - 1)/2) => мы обмениваем их местами
     *      2.4. выводить на экран состояние очереди
     *      2.5. разблокировать все потоки notEmpty, lessThan5, greaterThan5
     *           notEmpty.signalAll()
     *           lessThan5.signalAll()
     *           greaterThan5.signalAll()
     * 3) lock.unlock()
     */
    @Override
    public void insert(int val) throws InterruptedException{
        lock.lock();
        try {
            while (full()) notFull.await();
            System.out.println(Thread.currentThread().getName() + " Produced " + val);
            int current = size;
            queue[size++] = val;
            while (current > 0 && queue[current] > queue[(current - 1)/2]) { // Если queue[child] < queue[parent]
                int parent = (current - 1) / 2;
                swap(current, parent); // Мы меняем их местами
                current = parent;
            }
            print();
//            if(val < 5)
//                lessThan5.signalAll();
//            else
//                greaterThan5.signalAll();
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }
    /**
     *public int deleteMax(int val) throws InterruptedException { => deleteMax(1) - поток 1 работает, deleteMax(2) - поток 2 работает
     *         1) lock.lock()
     *         2) Try
     *             2.1. пока size == 0 (empty()) =>  notEmpty.await()
     *             2.2. Инициализация res = queue[0]
     *             2.3. Проверка:
     *                 2.3.1. Если поток, который работает это поток consumer 1
     *                        пока: максимальное значение > 5 => {
     *                             greaterThan5.await();
     *                             lessThan5.signalAll();
     *                        }
     *                 2.3.2. Если поток, который работает это поток consumer 2
     *                        пока: максимальное значение <= 5 => {
     *                             lessThan5.await()
     *                             greaterThan5.signalAll()
     *                        }
     *             2.4. удалить первый элемент (queue[0] = queue[size--])
     *             2.5. Пока child (parent*2 + 1) < size (начала parent = 0)
     *                  Если queue[child] > queue[parent] => мы обмениваем их местами
     *                  Наоборот break
     *             2.6. выводить на экран состояние очереди
     *             2.7. Возвращаться res
     *             2.8. разблокировать все потоки notEmpty.signalAll();
     *         3) lock.unlock()
     *     }
     */
    @Override
    public int deleteMax(int val) throws InterruptedException{
        lock.lock();
        try {
            while (empty()) notEmpty.await();
            if (val <= 5){  // Если Consumer 1(1-5) работает
                while (queue[0] > 5) { // поток будет спать если мах > 5
                    lessThan5.signalAll();
                    greaterThan5.await();
                }
            } else{  // Если Consumer 2(6-10) работает
                while (queue[0] <= 5){
                    greaterThan5.signalAll();
                    lessThan5.await();
                }
            }
            int result = queue[0];
            System.out.println(Thread.currentThread().getName() + " : " + queue[0]);
            size--;
            queue[0] = queue[size];
            int current = 0;
            while (current < (size - 1) / 2) { // Проверка всех родителей
                int left = current * 2 + 1;
                int right = current * 2 + 2;
                int max = left;
                if (right < size && queue[right] > queue[left]) max = right;
                if (queue[current] < queue[max]) {  // если queue[parent] < queue[child] => меняем их местами
                    swap(current, max);
                    current = max;
                } else break;
            }
            print();
            notFull.signalAll();
            return result;
        } finally {
            lock.unlock();
        }
    }

    /**
     * если массив пустой => notEmpty.await()
     * return queue[0]
     */
    @Override
    public boolean full() {
        return size == 10;
    }
    @Override
    public boolean empty() {
        return size == 0;
    }
    /**
     * Отображение на экране всех элементов массива
     */
    private void print(){
        System.out.print("PRIORITY QUEUE: ");
        for(int i = 0; i < size; ++i)
            System.out.print(queue[i] + " ");
        System.out.println();
        System.out.println();
    }
    private void swap(int i, int j){
        int temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }
}