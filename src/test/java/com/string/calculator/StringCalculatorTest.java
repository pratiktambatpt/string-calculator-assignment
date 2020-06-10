package com.string.calculator;

import com.stirng.calculator.StringCalculator;
import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StringCalculatorTest {
    @Test
    public void givenEmptyStringReturnsZero() throws Exception {
        String emptyString = "";
        assertResultForGivenInputStringIs(emptyString, 0);
    }

    @Test
    public void givenSingleNumberReturnsItsValue() throws Exception {
        String singleNumber = "1";
        int valueOfTheNumber = 1;
        assertResultForGivenInputStringIs(singleNumber, valueOfTheNumber);
    }

    @Test
    public void givenPairOfComaSeparatedNumbersReturnTheirSum() throws Exception {
        String pairOfNumbers = "1,2";
        int sumOfNumbers = 1 + 2;
        assertResultForGivenInputStringIs(pairOfNumbers, sumOfNumbers);
    }

    @Test
    public void allowsUsingNewLinesAsSeparators() throws Exception {
        String numbersSeparatedWithNewLines = "1\n2,3";
        int sumOfNumbers = 1 + 2 + 3;
        assertResultForGivenInputStringIs(numbersSeparatedWithNewLines, sumOfNumbers);
    }

    @Test
    public void usesCustomSeparatorDefinedInFirstLineOfInput() throws Exception {
        String numbersWithCustomSeparator = "//;\n1;2";
        int sumOfNumbers = 1 + 2;
        assertResultForGivenInputStringIs(numbersWithCustomSeparator, sumOfNumbers);
    }

    @Test
    public void exceptionIsThrownWhenNegativeNumberIsFound() throws Exception {
        String numbersWithNegativeNumber = "1,2,-3";
        assertInputStringCausesExceptionToBeThrownWithMessage(numbersWithNegativeNumber, "Negatives not allowed: [-3]");
    }

    @Test
    public void exceptionIsThrownWhenMultipleNegativesAreFound() throws Exception {
        String numbersWithNegativeNumber = "1,-2,-3";
        assertInputStringCausesExceptionToBeThrownWithMessage(numbersWithNegativeNumber, "Negatives not allowed: [-2, -3]");
    }

    @Test
    public void numberGreaterThan1000IsIgnored() throws Exception {
        String singleBigNumber = "1001";
        assertResultForGivenInputStringIs(singleBigNumber, 0);
    }

    @Test
    public void multipleNumbersGreaterThan1000AreIgnored() throws Exception {
        String singleBigNumber = "2,1001";
        assertResultForGivenInputStringIs(singleBigNumber, 2);
    }

    private void assertInputStringCausesExceptionToBeThrownWithMessage(String inputString, String expectedErrorMsg) {
        try {
            calculateSum(inputString);
            fail("exception should have been thrown");
        } catch (Exception e) {
            assertEquals("failure message", expectedErrorMsg, e.getMessage());
        }

    }

    private void assertResultForGivenInputStringIs(String inputString, int expectedResult) {
        int actualResult = calculateSum(inputString);
        assertEquals(format("sum of numbers in input string \"%s\"", inputString), expectedResult, actualResult);
    }

    private int calculateSum(String inputString) {
        return new StringCalculator().add(inputString);
    }
}
