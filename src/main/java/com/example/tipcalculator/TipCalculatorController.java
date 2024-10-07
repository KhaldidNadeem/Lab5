package com.example.tipcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.beans.binding.Bindings;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class TipCalculatorController {
    // formatters for currency and percentages
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    // called by FXMLLoader to initialize the controller
    public void initialize() {
        // 0-4 rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);

        // Bind tipPercentageLabel to slider's value
        tipPercentageLabel.textProperty().bind(
                Bindings.format("%.0f%%", tipPercentageSlider.valueProperty())
        );

        // Listen to changes in the amountTextField or the tipPercentageSlider
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTipAndTotal();
        });

        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            calculateTipAndTotal();
        });
    }

    // Method to calculate the tip and total
    private void calculateTipAndTotal() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(BigDecimal.valueOf(tipPercentageSlider.getValue() / 100));
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            amountTextField.setText("Enter valid amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }
}
