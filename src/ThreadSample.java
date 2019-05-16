public class ThreadSample extends Thread{
    private String name;

    public ThreadSample(String _name){
        name = _name;
    }

    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(this.name + ": " + i);
        }
    }

    public static void main(String args[]){
        ThreadSample firstThread = new ThreadSample("Uno");
        ThreadSample secondThread = new ThreadSample("Dos");
        firstThread.start();
        secondThread.start();
        System.out.println("Done");
    }
}