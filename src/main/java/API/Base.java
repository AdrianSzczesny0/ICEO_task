package API;
import Utility.ENV;
import java.util.HashMap;
import java.util.Map;

public class Base {
    public String BASE_URL="https://api.apilayer.com/exchangerates_data";
    private static String apiKey = "VAg2sKX96pZQ8luOnU6VV14GM0DfJP6z";
    // a good idea would be to store apiKey on local machine in a file that is not pushed to repository.
    public ENV env;
    private static String getApiKey(){
        return apiKey;
    }
    public static Map<String,String> initHeaders() {
        Map<String, String> header = new HashMap<>();
        header.put("apikey", getApiKey());
        return header;
    }
    public void SetUp(ENV environment){
        env = environment;
        baseURl();
    }
    public void baseURl(){
        switch(env){
            case DEV:{
                BASE_URL="https://api.apilayer.com/exchangerates_data";
                break;
            }
            case DEV2:{
                BASE_URL="link to DEV api";
                break;
            }
            case PROD:{
                BASE_URL="link to PROD api";
                break;
            }
            case PRE_PROD:{
                BASE_URL="link to PRE PROD api";
                break;
            }
            default:{
                BASE_URL="https://api.apilayer.com/exchangerates_data";
                break;
            }
        }
    }
}
