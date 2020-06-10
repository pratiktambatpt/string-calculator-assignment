package com.stirng.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
public class NumbersParser {

    public static final Pattern separatorDefinitionPattern = Pattern.compile("^(//(.)\n).*$");
    public static final String noNumbersRegex = "^(//.\n)?$";
    public static final String defaultSeparatorRegex = "[,\n]";

    private final String numbersString;
    private List<String> terms;

    public NumbersParser(String input) {
        this.numbersString = input;
        terms = new ArrayList<String>();
    }

    public List<Integer> parseIntegers() {
        if (inputContainsNumbers()) {
            parseNumbers();
        }
        return termsAsIntegers();
    }

    private void parseNumbers() {
        terms.clear();
        String separator = defaultSeparatorRegex;
        String numbers = numbersString;
        Matcher separatorDefinitionMatcher = separatorDefinitionPattern.matcher(numbersString);
        if (separatorDefinitionMatcher.matches()) {
            separator = Pattern.quote(separatorDefinitionMatcher.group(2));
            numbers = numbersString.substring(separatorDefinitionMatcher.group(1).length());
        }
        terms.addAll(asList(numbers.split(separator)));
    }

    private boolean inputContainsNumbers() {
        return !numbersString.matches(noNumbersRegex);
    }

    private List<Integer> termsAsIntegers() {
        List<Integer> numbers = new ArrayList<Integer>();
        for (String stringTerm : terms) {
            numbers.add(toInteger(stringTerm));
        }
        return numbers;
    }

    private Integer toInteger(String input) {
        return Integer.valueOf(input);
    }

}
