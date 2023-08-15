package genaricUtil;

public class CommonParameter {
    public static  final  String KEY_VALUE_SEPERATOR ="###";

    public static String getKeyValueForDataBase(String key, String value){
        String[] ar = key.split("\\.");
        return  ar[ar.length-1] + CommonParameter.KEY_VALUE_SEPERATOR + value;
    }
    public static String getKeyValue(String key, String value){
        return  key + CommonParameter.KEY_VALUE_SEPERATOR + value;
    }
}
