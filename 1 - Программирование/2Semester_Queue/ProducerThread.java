//import java.util.Arrays;
//import java.util.Random;
//public class ProducerThread implements Runnable{
//    private PriorityQueue priorityQueue;
//    public ProducerThread(PriorityQueue priorityQueue){
//        this.priorityQueue = priorityQueue;
//    }
//    @Override
//    public void run(){
//        Random random = new Random();
//        while(true){
//            int count = random.nextInt(3) + 1;
//            for(int i = 0; i < count; ++i){
//                int val = random.nextInt(10) + 1;
//                priorityQueue.insert(val);
//                System.out.println("Producer: inserted value " + val);
//                System.out.println("Queue: ");
//            }
//        }try{
//            Thread.sleep(random.nextInt(1000));
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }
//}
