package me.wyderekk.utils;

public class RandomUtil {
    public static int getRandom(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}
