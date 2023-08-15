package genaricUtil;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author :
 * @Description: This class is used for storing java codes
 */

public class JavaUtil {
    /**
     * @return int
     * @description: This method is used to generate Random number
     */
    public int getRandomNumber() {
        Random ran = new Random();
        return ran.nextInt(1000);
    }


    /**
     * This method is used to convert string to key & value & return in the for of map
     *
     * @param key_value
     * @return
     */
    public Map<String, String> getMapData(String... key_value) {
        Map<String, String> map = new HashMap<>();
        for (String obj : key_value) {
            String[] keyValueData = obj.split(CommonParameter.KEY_VALUE_SEPERATOR);
            map.put(keyValueData[0], keyValueData[1]);
        }
        return map;
    }


    public static int convertToInt(String stringData) {
        try {
            return Integer.parseInt(stringData);
        } catch (Exception e) {
            return 0;
        }

    }

    public static long convertToLong(String stringData) {
        try {
            return Long.parseLong(stringData);
        } catch (Exception e) {
            return 0;
        }

    }


	public static double convertToDouble(String stringData){
		try{
			return Double.parseDouble(stringData);
		}
		catch (Exception e){
			return 0;
		}
	}
	/**
	 *This method is used to return a random value from a collection
	 * @param ar
	 * @return
	 */
	public String getRandomFromCollection(String[] ar){
		int length=ar.length;
		Random random=new Random();
		int ran=random.nextInt(length);
		return ar[ran];
	}

    /**
     * @return String
     * @description: This method is used to generate Random String
     */
    public String getRandomString(int no) {
        Random ran = new Random();
        String string = "";
        for (int i = 0; i < no; i++) {
            char c = (char) (ran.nextInt(122 - 97) + 97);
            string = string + c;
        }
        return string;
    }
    public static String FutureDate() {
        LocalDate today = LocalDate.now();
        LocalDate FutureMonth = today.plusMonths(12).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate FutureMonthwithDate1 = FutureMonth.plusDays(6);
        String FutureDate = FutureMonthwithDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(FutureDate);
        return FutureDate;
    }
}

