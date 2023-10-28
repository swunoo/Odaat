package dev.odaat.Utils;

public class Utils {

    public static String buildApiRoute(String requestUrl, String routeName){
        int lastSlash = requestUrl.lastIndexOf("/");
        return requestUrl.substring(0, lastSlash) + "/api/v1/" + routeName;
    }
}
