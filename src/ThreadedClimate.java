import java.util.Date;

public class ThreadedClimate extends Thread {
    public int starting_year;
    public int ending_year;

    public void run(){
        ClimateReader cr = new ClimateReader();

        try {
            for (int year=starting_year; year<ending_year; year+=10){
                int last_year = year + 10; // We are trying to do the calls to the API in increments of 10 years
                if (last_year > ending_year){
                    last_year = ending_year;
                }
                String result = cr.getMonthlyClimate(year, year+10);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ThreadedClimate threads[] = new ThreadedClimate[4];

        // Thread setup
        int thread_chunk = Math.floorDiv(2017-1880, threads.length);
        int prev_start = 1880;
        int prev_end = 1880;

        for (int t=0; t<threads.length; t++){
            ThreadedClimate thread = new ThreadedClimate();

            thread.starting_year = prev_end;
            thread.ending_year = thread.starting_year + thread_chunk;

            threads[t] = thread;

            prev_start = thread.starting_year;
            prev_end = thread.ending_year;
        }

        Date pre = new Date();

        try {
            for (int t=0; t<threads.length; t++){
                threads[t].start();
            }

            for (int t=0; t<threads.length; t++){
                threads[t].join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date post = new Date();
        System.out.println("Two thread Time:" + new Long(post.getTime()-pre.getTime()));
    }
}
