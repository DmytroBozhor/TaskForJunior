package org.example.Task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConversionOfDegreesTest {

    @Test
    void convertDegreeToJsonTest() {
        assertEquals(ConversionOfDegrees.convertDegreeToJson("26C"), """
                {
                	F:78,
                	K:298,
                }""");
        assertEquals(ConversionOfDegrees.convertDegreeToJson("26F"), """
                {
                	C:-4,
                	K:269,
                }""");
        assertEquals(ConversionOfDegrees.convertDegreeToJson("26K"), """
                {
                	C:-247,
                	F:-412,
                }""");
    }

    @Test
    void convertDegreeToJsonTestScaleNotSpecifiedException() {
        assertThrows(IllegalArgumentException.class, () -> ConversionOfDegrees.convertDegreeToJson("26"));
        assertThrows(IllegalArgumentException.class, () -> ConversionOfDegrees.convertDegreeToJson("26something"));
    }

    @Test
    void convertDegreeToJsonTestParseDoubleException() {
        assertThrows(NumberFormatException.class, () -> ConversionOfDegrees.convertDegreeToJson("26somethingC"));
        assertThrows(NumberFormatException.class, () -> ConversionOfDegrees.convertDegreeToJson("26somethingF"));
        assertThrows(NumberFormatException.class, () -> ConversionOfDegrees.convertDegreeToJson("26somethingK"));
    }


}