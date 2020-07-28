package de.ld;

public enum Algorithms {
    BUBBLESORT("Bubble sort"),
    INSERSIONSORT("Insertion sort"),
    QUICKSORT("Quick sort");

    private final String label;
    Algorithms(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
