package API;
public class Timeseries extends Base {
    public String getTimeseriesURL(String startDate, String endDate){
        return "/timeseries?start_date="+startDate+"&end_date="+endDate;
    }
    public String getTimeseriesURL(String route,String startDate, String endDate){
        return route+"?start_date="+startDate+"&end_date="+endDate;
    }

}
