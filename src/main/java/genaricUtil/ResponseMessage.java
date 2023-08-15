package genaricUtil;

public class ResponseMessage {
    public static  final  String $200 ="200"+CommonParameter.KEY_VALUE_SEPERATOR+"OK";
    public static  final  String $412 ="412"+CommonParameter.KEY_VALUE_SEPERATOR+"Precondition Failed";
    public static  final  String $400 ="400"+CommonParameter.KEY_VALUE_SEPERATOR+"Bad Request";
    public static  final  String $201 ="201"+CommonParameter.KEY_VALUE_SEPERATOR+"Created";
    public static  final  String $403 ="403"+CommonParameter.KEY_VALUE_SEPERATOR+"Forbidden";
    public static  final  String $500 ="500"+CommonParameter.KEY_VALUE_SEPERATOR+"Internal Server Error";
    public static  final  String $404 ="404"+CommonParameter.KEY_VALUE_SEPERATOR+"Not Found";
    public static  final  String $406 ="406"+CommonParameter.KEY_VALUE_SEPERATOR+"Not Acceptable";
    public static  final  String $413 ="413"+CommonParameter.KEY_VALUE_SEPERATOR+"Request Entity Too Large";
}