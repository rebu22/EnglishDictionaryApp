package main.service;

public class CalculatorService {
    
    public double calculate(String expression) {
        try {

            expression = expression.replaceAll("\\s+", "");

            if (expression.contains("+")) {
                String[] parts = expression.split("\\+");
                return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
            } else if (expression.contains("-")) {
                String[] parts = expression.split("-");
                return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
            } else if (expression.contains("*")) {
                String[] parts = expression.split("\\*");
                return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
            } else if (expression.contains("/")) {
                String[] parts = expression.split("/");
                if (Double.parseDouble(parts[1]) == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
            } else {
                return Double.parseDouble(expression);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }
    
    public String evaluateExpression(String expression) {
        try {
            double result = calculate(expression);
            if (result == (long) result) {
                return String.valueOf((long) result);
            } else {
                return String.valueOf(result);
            }
        } catch (Exception e) {
            return "Error";
        }
    }
}