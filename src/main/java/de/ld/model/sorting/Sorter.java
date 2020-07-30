package de.ld.model.sorting;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.ObservableList;

import java.util.List;

public abstract class Sorter {
    public abstract ObservableList<Integer> sort(ObservableList<Integer> intArr);
    public abstract ObservableList<Integer> sort(ObservableList<Integer> intArr, int delay);
    protected Integer currentMaxIndex;
    protected Integer compareValue;


    protected final ReadOnlyListWrapper<Integer> progress = new ReadOnlyListWrapper<>();

    public ReadOnlyListProperty<Integer> progressProperty() {
        return progress ;
    }

    public Integer getCurrentMaxIndex() {
        return currentMaxIndex;
    }

    public List<Integer> getProgress() {
        return progressProperty().get();
    }


    public void swap(List<Integer> intArr, int x, int y) {
        Integer tmp = intArr.get(x);
        intArr.set(x, intArr.get(y));
        intArr.set(y, tmp);
    }

}
