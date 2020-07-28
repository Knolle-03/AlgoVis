package de.ld.model.sorting.generator;

import java.util.Arrays;
import java.util.Random;

public class ArrayGenerator {



    public static int[] generate(int size, int min, int max) {
        Random rng = new Random();
        int[] intArr = new int[size];

        for (int i = 0; i < size; i++) {
            intArr[i] = rng.nextInt(max - min) + min;
        }

        return intArr;
    }


    public static void main(String[] args) {
        int[] rndIntArr =  ArrayGenerator.generate(20, 100, 102);
        System.out.println(Arrays.toString(rndIntArr));
    }
}
