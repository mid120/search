package com.liw.util;

import java.util.Random;
import java.util.UUID;

public class RandomCodeUtil {
    public static Integer RANDOM_CODE_LENGTH = Integer.valueOf(6);
    private static String[] chars = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static String[] numChars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public RandomCodeUtil() {
    }

    public static String generateRandomCode(Integer length) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        for(int i = 0; i < length.intValue(); ++i) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % chars.length]);
        }

        return shortBuffer.toString();
    }

    public static String generateRandomNum(Integer length) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        for(int i = 0; i < length.intValue(); ++i) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(numChars[x % numChars.length]);
        }

        return shortBuffer.toString();
    }

    public static String generateCode(int length) {
        String val = "";
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int temp = 97;
                val = val + (char)(random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val = val + String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }
}
