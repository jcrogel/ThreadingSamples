import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class AsyncSample {
    public int last_step(String msg){
        System.out.println("Received message:" + msg);
        return 0;
    }

    public String step_2(String msg){
        System.out.println("Step 2:" + msg);
        return "Step 2, done";
    }

    public String step_3(String msg){
        System.out.println("Step 3:" + msg);
        return "Step 3, done";
    }

    public String my_function(){
        for(long x=0; x<1000000L;x++) {
            System.out.print("");
        }
        System.out.println("Finished counting");
        return "API Done";
    }

    public String step_1(){
        for(int x=1000; x<2000;x++) {
            System.out.print("");
        }
        System.out.println("step_1 done");
        return "Step one return value";
    }

    public static void main(String args[]){
        AsyncSample as = new AsyncSample();

        CompletableFuture<String> cf = new CompletableFuture<String>();
        cf.supplyAsync(as::my_function).thenApply(as::last_step);
        cf.supplyAsync(as::step_1).thenApply(as::step_2).thenApply(as::step_3).thenApply(as::last_step);
        System.out.println("Spawned async process");

        try {
            Thread.sleep(5000);
        } catch (Exception e){}
        System.out.println("Waited 5 secs");
    }
}
