package de.ld.model.sorting.algorithms;

import de.ld.model.sorting.Sorter;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;

public class InsertionSort extends Sorter {

    private static final InsertionSort instance = new InsertionSort();

    private InsertionSort() {

    }

    public static InsertionSort getInstance() {
        return instance;
    }

    @Override
    public ObservableList<Integer> sort(ObservableList<Integer> intArr) {
        return null;
    }

    @Override
    public ObservableList<Integer> sort(ObservableList<Integer> intArr, int delay) {
        return null;
    }
}



