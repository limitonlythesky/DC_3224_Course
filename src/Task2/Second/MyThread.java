package Task2.Second;

public class MyThread extends Thread{
    private int id;
    private ThreadSafeQueue queue;


    public MyThread(int id, ThreadSafeQueue queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while(true) {
                Processor processor = queue.pop();
                processor.process();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

