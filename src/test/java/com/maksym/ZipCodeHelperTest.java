package com.maksym;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ZipCodeHelperTest {
    private static final String DISPLAY_NAME = "Resolving test case: ";
    private static final List<List<Integer>> INPUT = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(94133,94133));
        add(Arrays.asList(94200,94299));
        add(Arrays.asList(94600,94699));
    }};
    private static final List<List<Integer>> INPUT1 = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(94133,94133));
        add(Arrays.asList(94200,94299));
        add(Arrays.asList(94226,94399));
    }};
    private static final List<List<Integer>> INPUT2 = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(90133,91133));
        add(Arrays.asList(91100,94299));
        add(Arrays.asList(91600,93699));
        add(Arrays.asList(92600,94699));
    }};
    private static final List<List<Integer>> INPUT3 = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(89133,89233));
        add(Arrays.asList(90200,91299));
        add(Arrays.asList(90600,92699));
        add(Arrays.asList(92400, 93101));
        add(Arrays.asList(93102, 94200));
    }};

    private static final List<List<Integer>> OUTPUT = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(94133, 94133));
        add(Arrays.asList(94200, 94299));
        add(Arrays.asList(94600, 94699));
    }};
    private static final List<List<Integer>> OUTPUT1 = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(94133, 94133));
        add(Arrays.asList(94200, 94399));
    }};
    private static final List<List<Integer>> OUTPUT2 = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(90133, 94699));
    }};
    private static final List<List<Integer>> OUTPUT3 = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(89133, 89233));
        add(Arrays.asList(90200, 93101));
        add(Arrays.asList(93102, 94200));
    }};

    private static final List<List<Integer>> ILLEGAL_INPUT = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(94133,93133));
        add(Arrays.asList(94200,94299));
        add(Arrays.asList(94600,94699));
    }};
    private static final List<List<Integer>> ILLEGAL_INPUT2 = new ArrayList<List<Integer>>() {{
        add(Arrays.asList(94133,95133));
        add(Arrays.asList(94200,93299));
        add(Arrays.asList(94600,94699));
    }};

    private static List<List<List<Integer>>> inputList = new ArrayList<>();
    private static List<List<List<Integer>>> outputList = new ArrayList<>();
    private static List<List<List<Integer>>> illegalInputList = new ArrayList<>();

    @BeforeAll
    public static void setParameters() {
        inputList.add(INPUT);
        inputList.add(INPUT1);
        inputList.add(INPUT2);
        inputList.add(INPUT3);

        illegalInputList.add(ILLEGAL_INPUT);
        illegalInputList.add(ILLEGAL_INPUT2);

        outputList.add(OUTPUT);
        outputList.add(OUTPUT1);
        outputList.add(OUTPUT2);
        outputList.add(OUTPUT3);
    }

    @Order(1)
    @TestFactory
    Stream<DynamicTest> testZipCodeCorrectIntervals() {
        return inputList.stream().map(element -> DynamicTest.dynamicTest(DISPLAY_NAME + inputList.indexOf(element),
                () -> {
                    int i = inputList.indexOf(element);
                    Assertions.assertIterableEquals(outputList.get(i), ZipCodeHelper.solveZip(element));
                }));
    }

    @Order(2)
    @TestFactory
    Stream<DynamicTest> testZipCodeIllegatInterval() throws IllegalArgumentException{
        return illegalInputList.stream().map(element -> DynamicTest.dynamicTest(DISPLAY_NAME + illegalInputList.indexOf(element),
                () -> {
                    Assertions.assertThrows(IllegalArgumentException.class, () -> ZipCodeHelper.solveZip(element));
                }));
    }

}
