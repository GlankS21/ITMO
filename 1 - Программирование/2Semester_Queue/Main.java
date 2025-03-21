public class Main {
    public static void main(String[] args){
        queue q = new queue();
        Producer producer1 = new Producer(q,"Producer 1");
        Producer producer2 = new Producer(q, "Producer 2");
        Consumer consumer1 = new Consumer(q,5);
        Consumer consumer2 = new Consumer(q,10);
    }
}