package de.ld.model.sorting.algorithms;

import de.ld.model.sorting.Sorter;
import javafx.collections.ObservableList;

public class BubbleSort extends Sorter {

    private static final BubbleSort instance = new BubbleSort();

    private BubbleSort(){}


    @Override
    public ObservableList<Integer> sort(ObservableList<Integer> intArr) {
        return null;
    }

    @Override
    public ObservableList<Integer> sort(ObservableList<Integer> intArr, int delay) {

        boolean swapped;
        compareValue = intArr.get(1);

        for (int i = 0; i < intArr.size() ; i++) {
            swapped = false;
            currentMaxIndex = 0;
            for (int j = 0; j < intArr.size() - 1 - i; j++) {
                compareValue = intArr.get(j);
                if (intArr.get(j) > intArr.get(j + 1)) {
                    swap(intArr, j, j + 1);
                    swapped = true;
                }
                currentMaxIndex = j + 2;

                try {
                    Thread.sleep(delay);
                    progress.set(intArr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (!swapped) break;
        }

        return intArr;
    }

    public static BubbleSort getInstance() {
        return instance;
    }

}
