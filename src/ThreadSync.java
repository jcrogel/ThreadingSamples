import java.util.Date;
import java.util.Random;

public class ThreadSync extends Thread {
    public String _name;
    public static int max_limit = 10;
    public static int count;

    public ThreadSync(String myname){
        _name = myname;
    }

    public static synchronized void increment(String name){

        int current = count;
        count++;
        System.out.println(name + " Was: " + current + " is:" + count);
    }

    public void run(){
        // This part will be run in a threaded mode
        while (count<max_limit){
            this.increment(this._name);
        }

    }

    public static void main(String args[]){
        // Heavy processing starts here
        ThreadSync my_thread_1 = new ThreadSync("Thread_1");
        ThreadSync my_thread_2 = new ThreadSync("Thread_2");
        ThreadSync my_thread_3 = new ThreadSync("Thread_3");
        try {
            my_thread_1.start();
            my_thread_2.start();
            my_thread_3.start();

            my_thread_1.join();
            my_thread_2.join();
            my_thread_3.join();
        } catch (Exception se){
            System.out.println("Interrupted!");
        }
        // Heavy processing ends here

        System.out.println("Done");

    }
}
