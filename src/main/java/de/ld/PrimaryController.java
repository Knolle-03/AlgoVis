package de.ld;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.ld.Algorithms;
import de.ld.App;
import de.ld.model.generator.ArrayGenerator;
import de.ld.model.sorting.Sorter;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PrimaryController {

    // sorting Delay values
    private static final int MIN_DELAY_MILLI = 0;
    private static final int MAX_DELAY_MILLI = 500;
    private static final int DEFAULT_DELAY_MILLI = 5;

    // Array size values
    private static final int MIN_ARRAY_SIZE = 0;
    private static final int MAX_ARRAY_SIZE = 1000;
    private static final int DEFAULT_ARRAY_SIZE = MAX_ARRAY_SIZE / 10;

    // min and max values for the array
    private static final int MIN_ARRAY_VALUE = 0;
    private static final int MAX_ARRAY_VALUE = 1000 ;
    private static final int DEFAULT_MIN_ARRAY_VALUE = 20;
    private static final int DEFAULT_MAX_ARRAY_VALUE = 100;

    public TextField arraySizeTextField;
    public Slider arraySizeSlider;



    public TextField arrayMaxValueTextField;
    public Slider arrayMaxValueSlider;

    public TextField arrayMinValueTextField;
    public Slider arrayMinValueSlider;
    public ChoiceBox<Algorithms> algorithmPicker;
    public Button sortingBtn;
    public Slider sortingStepDelaySlider;
    public TextField sortingStepDelayTextField;
    public BorderPane borderPane;
    public AnchorPane canvasParent;

    @FXML
    private Canvas sortingCanvas;

    private GraphicsContext gc;

    private ObservableList<Integer> intArray;

    public void initialize() {
        setupCanvas();
        setDefaults();
        getAlgorithms();
        algorithmPicker.getSelectionModel().select(0);

        arraySizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            arraySizeTextField.setText(String.valueOf(newValue.intValue()));
            intArray = ArrayGenerator.generate(newValue.intValue(), (int) arrayMinValueSlider.getValue(), (int) arrayMaxValueSlider.getValue());
            draw(intArray);
        });
        arrayMinValueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            arrayMinValueTextField.setText(String.valueOf(newValue.intValue()));
            intArray = ArrayGenerator.generate((int) arraySizeSlider.getValue(), newValue.intValue(), (int) arrayMaxValueSlider.getValue());
            draw(intArray);
        });
        arrayMaxValueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            arrayMaxValueTextField.setText(String.valueOf(newValue.intValue()));
            intArray = ArrayGenerator.generate((int) arraySizeSlider.getValue(), (int) arrayMinValueSlider.getValue(), newValue.intValue());
            draw(intArray);
        });
        sortingStepDelaySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sortingStepDelayTextField.setText(String.valueOf(newValue.intValue()));
        });

        gc = sortingCanvas.getGraphicsContext2D();
        intArray = ArrayGenerator.generate(DEFAULT_ARRAY_SIZE, DEFAULT_MIN_ARRAY_VALUE, DEFAULT_MAX_ARRAY_VALUE);
        draw(intArray);


    }


    private void setupCanvas() {






        canvasParent.widthProperty().addListener(observable -> {
            System.out.println("width changed");
            draw(intArray);
        });
        canvasParent.heightProperty().addListener(observable -> draw(intArray));


        sortingCanvas.widthProperty().bind(canvasParent.widthProperty());
        sortingCanvas.heightProperty().bind(canvasParent.heightProperty());


    }




    public void draw(ObservableList<Integer> intArr) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, sortingCanvas.getWidth(), sortingCanvas.getHeight());

        double recWidth = (sortingCanvas.getWidth() - 1) / intArr.size();
        double maxVal = arrayMaxValueSlider.getValue();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLUE);

        for (int i = 0; i < intArr.size(); i++) {
            double x = i * recWidth;
            double y = sortingCanvas.getHeight() - intArr.get(i) * sortingCanvas.getHeight() / maxVal;
            gc.fillRect(x, y, recWidth, sortingCanvas.getHeight());
            gc.strokeRect(x, y, recWidth, sortingCanvas.getHeight() - y);
        }
    }


    public void draw(ObservableList<Integer> intArr, Sorter sorter) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, sortingCanvas.getWidth(), sortingCanvas.getHeight());

        double recWidth = (sortingCanvas.getWidth() - 1) / intArr.size();
        double maxVal = arrayMaxValueSlider.getValue();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLUE);

        for (int i = 0; i < intArr.size(); i++) {

            double x = i * recWidth;
            double y = sortingCanvas.getHeight() - intArr.get(i) * sortingCanvas.getHeight() / maxVal;
            if (sorter.getCurrentMaxIndex() == i) {
                gc.setFill(Color.RED);
                System.out.println("drawing current max");
            } else {
                gc.setFill(Color.WHITE);
            }
            gc.fillRect(x, y, recWidth, sortingCanvas.getHeight());
            gc.strokeRect(x, y, recWidth, sortingCanvas.getHeight() - y);
        }

    }

    private int doubleToInteger(double doubleVal) {
        return 888;
    }

    private void setDefaults(){
        sortingStepDelaySlider.setValue(DEFAULT_DELAY_MILLI);
        sortingStepDelayTextField.setText(String.valueOf(DEFAULT_DELAY_MILLI));
        arraySizeSlider.setValue(DEFAULT_ARRAY_SIZE);
        arraySizeTextField.setText(String.valueOf(DEFAULT_ARRAY_SIZE));
        arrayMinValueSlider.setValue(DEFAULT_MIN_ARRAY_VALUE);
        arrayMinValueTextField.setText(String.valueOf(DEFAULT_MIN_ARRAY_VALUE));
        arrayMaxValueSlider.setValue(DEFAULT_MAX_ARRAY_VALUE);
        arrayMaxValueTextField.setText(String.valueOf(DEFAULT_MAX_ARRAY_VALUE));

    }
     
    private void getAlgorithms() {
        for (Algorithms algorithm : Algorithms.values()) {
            algorithmPicker.getItems().add(algorithm);

        }
    }



    public void handleArraySizeTextFieldChange() {
        int newValue = Integer.parseInt(arraySizeTextField.getText());
        arraySizeSlider.setValue(newValue);
    }

    public void handleArrayMinSizeTextFieldChange() {
        int newValue = Integer.parseInt(arrayMinValueTextField.getText());
        arrayMinValueSlider.setValue(newValue);
    }

    public void handleArrayMaxSizeTextFieldChange() {
        int newValue = Integer.parseInt(arrayMaxValueTextField.getText());
        arrayMaxValueSlider.setValue(newValue);
    }

    public void handleStepDelayTextFieldChange() {
        int newValue = Integer.parseInt(sortingStepDelayTextField.getText());
        sortingStepDelaySlider.setValue(newValue);
    }

    public void handleSortBtnPressed() {
        Algorithms pickedAlgorithm = algorithmPicker.getSelectionModel().getSelectedItem();
        Sorter sorter = pickedAlgorithm.getSorter();

        Service<Void> sortingService = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() {
                        sorter.progressProperty().addListener((observable, oldValue, newValue) -> Platform.runLater (() -> draw(newValue)));
                        sorter.sort(intArray, (int) sortingStepDelaySlider.getValue());
                        System.out.println("done sorting");

                        return null;
                    }
                };
            }
        };
        sortingService.start();

    }


    public static class ResizableCanvas extends Canvas {

        public ResizableCanvas() {
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> draw());
            heightProperty().addListener(evt -> draw());
        }

        private void draw() {
            double width = getWidth();
            double height = getHeight();

            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, width, height);

            gc.setStroke(Color.RED);
            gc.strokeLine(0, 0, width, height);
            gc.strokeLine(0, height, width, 0);
        }

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) {
            return getWidth();
        }

        @Override
        public double prefHeight(double width) {
            return getHeight();
        }
    }





}



