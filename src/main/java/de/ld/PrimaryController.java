package de.ld;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import de.ld.model.sorting.generator.ArrayGenerator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PrimaryController {

    public TextField arraySizeTextField;
    public Slider arraySizeSlider;



    public TextField arrayMaxValueTextField;
    public Slider arrayMaxValueSlider;


    public TextField arrayMinValueTextField;
    public Slider arrayMinValueSlider;
    public ChoiceBox<Algorithms> algorithmPicker;
    public Button sortingBtn;

    @FXML
    private Canvas sortingCanvas;

    private GraphicsContext gc;


    public void initialize() {
        setDefaults();
        getAlgorithms();

        arraySizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            arraySizeTextField.setText(String.valueOf(newValue.intValue()));
            draw(ArrayGenerator.generate(newValue.intValue(), (int) arrayMinValueSlider.getValue(), (int) arrayMaxValueSlider.getValue()));
        });
        arrayMinValueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            arrayMinValueTextField.setText(String.valueOf(newValue.intValue()));
            draw(ArrayGenerator.generate((int) arraySizeSlider.getValue(), newValue.intValue(), (int) arrayMaxValueSlider.getValue()));
        });
        arrayMaxValueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            arrayMaxValueTextField.setText(String.valueOf(newValue.intValue()));
            draw(ArrayGenerator.generate((int) arraySizeSlider.getValue(), (int) arrayMinValueSlider.getValue(), newValue.intValue()));
        });

        int[] testArray = ArrayGenerator.generate((int) arraySizeSlider.getValue(), (int) arrayMinValueSlider.getValue(), (int) arrayMaxValueSlider.getValue());
        gc = sortingCanvas.getGraphicsContext2D();

        //draw(testArray);

    }



    public void draw(int[] intArr) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, sortingCanvas.getWidth(), sortingCanvas.getHeight());

        double recWidth = (sortingCanvas.getWidth() - 1) / intArr.length;
        double maxVal = arrayMaxValueSlider.getValue();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLUE);

        for (int i = 0; i < intArr.length; i++) {
            double x = i * recWidth;
            double y = sortingCanvas.getHeight() - intArr[i] * sortingCanvas.getHeight() / maxVal;
            gc.fillRect(x, y, recWidth, sortingCanvas.getHeight());
            gc.strokeRect(x, y, recWidth, sortingCanvas.getHeight() - y);
        }


    }

    private int doubleToInteger(double doubleVal) {
        return 888;
    }

    private void setDefaults(){
        arraySizeSlider.setValue(20);
        arraySizeTextField.setText(String.valueOf(20));
        arrayMinValueSlider.setValue(10);
        arrayMinValueTextField.setText(String.valueOf(10));
        arrayMaxValueSlider.setValue(100);
        arrayMaxValueTextField.setText(String.valueOf(100));

    }
     
    private void getAlgorithms() {
        for (Algorithms algorithm : Algorithms.values()) {
            algorithmPicker.getItems().add(algorithm);

        }
    }








    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }



    public void handleArrayMinValueSliderChange(MouseEvent mouseEvent) {
    }

    public void handleArraySizeTextFieldChange(ActionEvent event) {
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

    public void handleSortBtnPressed(ActionEvent event) {
        System.out.println(algorithmPicker.getSelectionModel().getSelectedItem());
    }
}
