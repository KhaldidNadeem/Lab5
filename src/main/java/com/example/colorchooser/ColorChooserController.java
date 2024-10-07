package com.example.colorchooser;

import javafx.beans.value.ChangeListener;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ColorChooserController {
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider alphaSlider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private TextField alphaTextField;
    @FXML private Rectangle colorRectangle;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    public void initialize() {
        // Bidirectional bindings between sliders and text fields
        StringConverter<Number> intStringConverter = new NumberStringConverter("#");
        StringConverter<Number> doubleStringConverter = new NumberStringConverter("#.##");

        // Red
        Bindings.bindBidirectional(redTextField.textProperty(), redSlider.valueProperty(), intStringConverter);
        // Green
        Bindings.bindBidirectional(greenTextField.textProperty(), greenSlider.valueProperty(), intStringConverter);
        // Blue
        Bindings.bindBidirectional(blueTextField.textProperty(), blueSlider.valueProperty(), intStringConverter);
        // Alpha (this is a double, so we use a double converter)
        Bindings.bindBidirectional(alphaTextField.textProperty(), alphaSlider.valueProperty(), doubleStringConverter);

        // Listeners that set the Rectangle's fill color based on slider changes
        redSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            red = newValue.intValue();
            updateColor();
        });
        greenSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            green = newValue.intValue();
            updateColor();
        });
        blueSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            blue = newValue.intValue();
            updateColor();
        });
        alphaSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            alpha = newValue.doubleValue();
            updateColor();
        });
    }

    // Update the color rectangle
    private void updateColor() {
        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
    }
}
