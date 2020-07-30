package de.ld;

import de.ld.model.sorting.Sorter;
import de.ld.model.sorting.algorithms.BubbleSort;
import de.ld.model.sorting.algorithms.InsertionSort;
import de.ld.model.sorting.algorithms.QuickSort;

public enum Algorithms {
    BUBBLESORT("Bubble sort", BubbleSort.getInstance()),
    INSERTIONSORT("Insertion sort", InsertionSort.getInstance()),
    QUICKSORT("Quick sort", QuickSort.getInstance());

    private final String label;
    private final Sorter sorter;
    Algorithms(String label, Sorter sorter){
        this.label = label;
        this.sorter = sorter;
    }

    public String getLabel() {
        return label;
    }

    public Sorter getSorter() {
        return sorter;
    }
}
