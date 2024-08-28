package co.istad.springbankingapi.util;

import java.util.Random;

public class RandomUtil {
    public static String random6Digits(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d",number);
    }
}
