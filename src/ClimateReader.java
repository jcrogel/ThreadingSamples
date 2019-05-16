import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class ClimateReader {


    public String getMonthlyClimate(int startYear, int endYear) throws Exception{
        String noaa_url = "https://www.ncdc.noaa.gov/cag/global/time-series/globe/land_ocean/all/4/%d-%d.json";
        URL noaa = new URL(String.format(noaa_url, startYear, endYear));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(noaa.openStream()));

        String inputLine;
        String fileContents = "";
        while ((inputLine = in.readLine()) != null){
            fileContents += inputLine;
        }

        in.close();
        return fileContents;
    }

    public static void main(String args[]){
        Date pre = new Date();
        ClimateReader cr = new ClimateReader();

        try {
            int startYear = 1880;
            int endYear = 2017;
            for (int year=startYear; year<endYear; year+=10){
                String result = cr.getMonthlyClimate(year, year+10);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date post = new Date();
        System.out.println("Single Thread Time:" + new Long(post.getTime()-pre.getTime()));

    }
}
