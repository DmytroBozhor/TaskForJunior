package org.example.jsonconverter;

import org.example.jsonconverter.degree.Celsius;
import org.example.jsonconverter.degree.Degree;
import org.example.jsonconverter.degree.Fahrenheit;
import org.example.jsonconverter.degree.Kelvin;

public class ConversionOfDegrees {

    public static void main(String[] args) {
        System.out.println(convertDegreeToJson("26C"));
    }

    public static String convertDegreeToJson(String value) throws IllegalArgumentException {
        Degree degree = createDegreeByName(value);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convert(degree);
    }

    private static double convertFromCelsiusToFahrenheit(String celsiusDegree) {
        double celsiusDegreeValue = Double.parseDouble(celsiusDegree.replace("C", ""));
        return (celsiusDegreeValue * 9 / 5) + 32;
    }

    private static double convertFromFahrenheitToKelvin(String fahrenheitDegree) {
        double fahrenheitDegreeValue = Double.parseDouble(fahrenheitDegree.replace("F", ""));
        return (fahrenheitDegreeValue - 32) * 5 / 9 + 273.15;
    }

    private static double convertFromKelvinToCelsius(String kelvinDegree) {
        double kelvinDegreeValue = Double.parseDouble(kelvinDegree.replace("K", ""));
        return kelvinDegreeValue - 273.15;
    }

    private static Degree createDegreeByName(String value) {
        if (value.contains("C")) {
            Celsius celsius = new Celsius();
            celsius.setF((int) convertFromCelsiusToFahrenheit(value));
            celsius.setK((int) convertFromFahrenheitToKelvin(String.valueOf(celsius.getF())));
            return celsius;
        } else if (value.contains("F")) {
            Fahrenheit fahrenheit = new Fahrenheit();
            fahrenheit.setK((int) convertFromFahrenheitToKelvin(value));
            fahrenheit.setC((int) convertFromKelvinToCelsius(String.valueOf(fahrenheit.getK())));
            return fahrenheit;
        } else if (value.contains("K")) {
            Kelvin kelvin = new Kelvin();
            kelvin.setC((int) convertFromKelvinToCelsius(value));
            kelvin.setF((int) convertFromCelsiusToFahrenheit(String.valueOf(kelvin.getC())));
            return kelvin;
        } else throw new IllegalArgumentException("Scale not specified");
    }

}
