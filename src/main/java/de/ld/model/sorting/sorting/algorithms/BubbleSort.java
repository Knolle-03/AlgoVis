package de.ld.model.sorting.sorting.algorithms;

import de.ld.model.sorting.generator.ArrayGenerator;
import de.ld.model.sorting.sorting.Sorter;

import java.util.Arrays;

public class BubbleSort extends Sorter {

    private static final BubbleSort instance = new BubbleSort();

    private BubbleSort(){}

    @Override
    public int[] sort(int[] intArr) {

        boolean swapped;

        for (int i = 0; i < intArr.length ; i++) {
            swapped = false;
            for (int j = 0; j < intArr.length - 1; j++) {
                if (intArr[j] > intArr[j + 1]) {
                    swap(intArr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        return intArr;
    }

    public static BubbleSort getInstance() {
        return instance;
    }


    public static void main(String[] args) {

        System.out.println(Arrays.toString(BubbleSort.getInstance().sort(ArrayGenerator.generate(25, 10, 100))));
    }
}
