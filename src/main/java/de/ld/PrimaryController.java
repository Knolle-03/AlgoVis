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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PrimaryController {

    public TextField arraySizeTextField;
    public Slider arraySizeSlider;



    public TextField arrayMaxValueTextField;
    public Slider arrayMaxValueSlider;


    public TextField arrayMinValueTextField;
    public Slider arrayMinValueSlider;

    @FXML
    private Canvas sortingCanvas;

    private GraphicsContext gc;


    private int[] testArray = ArrayGenerator.generate(100, 10, 100);


    public void initialize() {

//        arraySizeSlider.valueProperty().addListener((observableValue, oldNumber, newNumber) ->

        arraySizeSlider.valueProperty().addListener((observableValue, oldNumber, newNumber) -> {
                arraySizeTextField.setText(String.valueOf(newNumber.intValue()));
                draw(ArrayGenerator.generate(newNumber.intValue(), (int) arrayMinValueSlider.getValue(), (int) arrayMaxValueSlider.getValue()));
        });

        arrayMinValueSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                arrayMinValueTextField.setText((String.valueOf(newValue.intValue()))));

        arrayMaxValueSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                arrayMaxValueTextField.setText(String.valueOf(newValue.intValue())));


        gc = sortingCanvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, sortingCanvas.getWidth(), sortingCanvas.getHeight());
        draw(testArray);

    }



    public void draw(int[] intArr) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, sortingCanvas.getWidth(), sortingCanvas.getHeight());

        double recWidth = (sortingCanvas.getWidth() - 1) / intArr.length;
        double maxVal = 100;   //arrayMaxValueSlider.getValue();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLUE);

        for (int i = 0; i < intArr.length; i++) {
            double x = i * recWidth;
            double y = sortingCanvas.getHeight() - intArr[i] * sortingCanvas.getHeight() / maxVal;
            gc.fillRect(x, y, recWidth, sortingCanvas.getHeight());
            gc.strokeRect(x, y, recWidth, sortingCanvas.getHeight() - y);
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
}
