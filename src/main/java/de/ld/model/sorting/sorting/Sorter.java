package de.ld.model.sorting.sorting;

public abstract class Sorter {
    public abstract int[] sort(int[] intArr);

    public void swap(int[] intArr, int x, int y) {
        int tmp = intArr[x];
        intArr[x] = intArr[y];
        intArr[y] = tmp;
    }

}
