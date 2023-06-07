package pt.isec.pa.tinypac.utils;

import java.util.Random;
import java.util.Scanner;

public final class Utils {
    private Utils() {
    }

    private static Random rd;

    static {
        resetRandom();
    }

    public static void resetRandom() {
        rd = new Random();
    }

    public static boolean randBool(){
        return rd.nextBoolean();
    }

    public static int getNumber(int low, int high){
        return rd.nextInt(high-low) + low;
    }
}

