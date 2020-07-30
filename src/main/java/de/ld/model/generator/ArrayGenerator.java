package de.ld.model.generator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ArrayGenerator {



    public static ObservableList<Integer> generate(int size, int min, int max) {
        Random rng = new Random();
        ObservableList<Integer> intArr = FXCollections.observableArrayList(size);

        for (int i = 0; i < size; i++) {
            intArr.add(rng.nextInt(max - min) + min);
        }
        Collections.shuffle(intArr);
        return intArr;
    }


    public static void main(String[] args) {
        ObservableList<Integer> rndIntArr =  ArrayGenerator.generate(20, 100, 102);
        System.out.println(rndIntArr);
    }
}
