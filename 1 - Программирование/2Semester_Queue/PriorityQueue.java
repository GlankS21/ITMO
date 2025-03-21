public interface PriorityQueue {
    void insert(int val) throws InterruptedException; //помещает значение val в очередь
    int deleteMax(int max) throws InterruptedException; //удаляеет максимальное значение-приоритет из
    //очереди и возвращает его как результат
    boolean full(); //возвращает true, если очередь полная, иначе false
    boolean empty(); //возвращает true, если очередь пустая, иначе false
}