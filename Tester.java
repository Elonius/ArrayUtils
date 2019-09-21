package arrayUtils;

import java.util.Arrays;

public class Tester {

    public static void main(String[] args) {
        String start = startHTML();
        String tableHeader = tableHeader();

        String applyMaskHTML = tableHeader + testApplyMask() + "</table>";
        String concatenateHTML = tableHeader + testConcatenate() + "</table>";
        String containsHTML = tableHeader + testContains() + "</table>";
        String cumulativeSumsHTML = tableHeader + testCumulativeSums() + "</table>";
        String elementwiseSumsHTML = tableHeader + testElementwiseSums() + "</table>";
        String frequencyHTML = tableHeader + testFrequency() + "</table>";
        String isSortedHTML = tableHeader + testIsSorted() + "</table>";
        String reverseHTML = tableHeader + testReverse() + "</table>";
        String mergeHTML = tableHeader + testMerge() + "</table>";
        String scaleHTML = tableHeader + testScale();

        String end = endHTML();

        String output = start + applyMaskHTML + concatenateHTML + containsHTML + cumulativeSumsHTML
                + elementwiseSumsHTML + frequencyHTML + isSortedHTML + reverseHTML + mergeHTML + scaleHTML + end;
        System.out.println(output);
    }

    public static String startHTML() {
        String start = "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>Array Utils</title>" + "<style>"
                + "table, tr, th, td {" + "border: thin black solid;" + "border-collapse: collapse;" + "padding: 10px;"
                + "margin: 10px;" + "}" + "ul {" + "float: left;" + "}" + "</style>" + "</head>" + "<body>";

        return start;
    }

    public static String endHTML() {
        String end = "</table></body></html>";
        return end;
    }

    public static String tableHeader() {
        return "<table><tr><th>Test ID</th><th>Description</th><th>Inputs</th><th>Expected Result</th><th>Actual Result</th><th>Pass?</th></tr>";
    }

    // if input 2 doesn't exist or isn't an array, send 'null' as input2
    public static String makeTable(String methodName, String testID, String description, String input1, String input2,
            String expectedRes, String actualRes) {
        boolean pass = false;
        // Checks to see if expected result and actual result match
        if (expectedRes.equals(actualRes)) {
            pass = true;
        }
        String listStr = "";

        // Store in list string
        listStr += htmlListBuilder(input1);

        // This check won't necessarily work because sometimes there is a second
        // argument that isn't an array
        if (input2.contains(", ")) {
            // store this in a string
            // this doesn't work if sending an integer
            listStr += htmlListBuilder(input2);
        } else {
            listStr += "<ul> <li>" + input2 + "</ul> </li>";
        }
        String table = "";

        if (!methodName.equals("")) {
            table += "<h1> Method: " + methodName + "</h1>";
        }

        table += "<tr><td>" + testID + "</td><td>" + description + "</td><td>";
        table += listStr;
        table += "</td><td>" + expectedRes + "</td><td>" + actualRes + "</td><td>" + pass + "</td></tr>";
        return table;
    }

    public static String htmlListBuilder(String str) {
        // Removing '[' from front and ']' from back of string
        String subStr = str.substring(1, str.length() - 1);
        // Splitting string into an array
        String[] splitStr = subStr.split(", ");

        String list = "<ul>"; // Starting list

        for (int i = 0; i < splitStr.length; i++) {
            list += "<li>" + splitStr[i] + "</li>"; // Printing each item from array to the list
        }

        list += "</ul>"; // Ending list

        if (str == "") {
            list = "";
        }

        return list;
    }

    public static String testApplyMask() {
        // Variables for testing
        int[] testArr = { 1, 2, 3, 4, 5 };
        boolean[] testMask = { true, false, true, true, false };
        int counter = 1;
        // Returned array from ArrayUtils
        int[] returnedArr = ArrayUtils.applyMask(testArr, testMask);
        String res = Arrays.toString(returnedArr);

        // test 1
        String methodName = "applyMask", description = "Perfect case, both arrays same length.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArr),
                input2 = Arrays.toString(testMask), expectedRes = "[1, 3, 4]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testOne);
        counter++;

        // test 2
        // mismatched lengths should return an empty array
        int[] testArr2 = { 1, 2, 5, 4 };
        boolean[] testMask2 = { true, false, true, true, false };
        int[] returnedArr2 = ArrayUtils.applyMask(testArr2, testMask2);
        String res2 = Arrays.toString(returnedArr2);

        testID = methodName + "-" + counter;
        description = "Sending in a different sized integer array.";
        input1 = Arrays.toString(testArr2);
        input2 = Arrays.toString(testMask2);
        expectedRes = "[]";
        actualRes = res2;
        counter++;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testTwo);

        // test 3
        // mismatched lengths should return an empty array
        int[] testArr3 = { 1, 2, 5, 4 };
        boolean[] testMask3 = { true, false, true };
        int[] returnedArr3 = ArrayUtils.applyMask(testArr3, testMask3);
        String res3 = Arrays.toString(returnedArr3);

        testID = methodName + "-" + counter;
        description = "Sending in a different sized Boolean array.";
        input1 = Arrays.toString(testArr3);
        input2 = Arrays.toString(testMask3);
        expectedRes = "[]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testThree);
        counter++;

        // test 4
        // mismatched lengths should return an empty array
        int[] testArr4 = { 1, 2, 5, 4 };
        boolean[] testMask4 = { true, true, true, true };
        int[] returnedArr4 = ArrayUtils.applyMask(testArr4, testMask4);
        String res4 = Arrays.toString(returnedArr4);

        testID = methodName + "-" + counter;
        description = "All elements of Boolean array are true.";
        input1 = Arrays.toString(testArr4);
        input2 = Arrays.toString(testMask4);
        expectedRes = "[1, 2, 5, 4]";
        actualRes = res4;

        String testFour = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testFour);
        counter++;

        // test 5
        // mismatched lengths should return an empty array
        int[] testArr5 = { 1, 2, 5, 4 };
        boolean[] testMask5 = { false, false, false, false };
        int[] returnedArr5 = ArrayUtils.applyMask(testArr5, testMask5);
        String res5 = Arrays.toString(returnedArr5);

        testID = methodName + "-" + counter;
        description = "All elements of Boolean array are false.";
        input1 = Arrays.toString(testArr5);
        input2 = Arrays.toString(testMask5);
        expectedRes = "[]";
        actualRes = res5;

        String testFive = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testFive);

        // Add on to table with testTwo, testThree, etc
        String table = testOne + testTwo + testThree + testFour + testFive;

        return table;
    }

    public static String testConcatenate() {
        // Variables for testing
        int[] testArrA1 = { 1, 2, 3 }, testArrB1 = { 4, 5, 6 };
        int counter = 1;

        // Returned array from ArrayUtils
        int[] returnedArr = ArrayUtils.concatenate(testArrA1, testArrB1);
        String res = Arrays.toString(returnedArr);

        // Test 1
        String methodName = "concatenate", description = "Perfect case, sending in two matching length arrays.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArrA1),
                input2 = Arrays.toString(testArrB1), expectedRes = "[1, 2, 3, 4, 5, 6]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 2
        int[] testArrA2 = {}, testArrB2 = { 4, 7, 4, 0, 1 };
        int[] returnedArr2 = ArrayUtils.concatenate(testArrA2, testArrB2);
        String res2 = Arrays.toString(returnedArr2);

        description = "Sending in an empty array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArrA2);
        input2 = Arrays.toString(testArrB2);
        expectedRes = "[4, 7, 4, 0, 1]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 3
        int[] testArrA3 = {}, testArrB3 = {};
        int[] returnedArr3 = ArrayUtils.concatenate(testArrA3, testArrB3);
        String res3 = Arrays.toString(returnedArr3);

        description = "Sending in two empty arrays.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArrA3);
        input2 = Arrays.toString(testArrB3);
        expectedRes = "[]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes);

        String table = testOne + testTwo + testThree;
        return table;
    }

    public static String testContains() {
        // Variables for testing
        int[] testArr1 = { 1, 2, 3, 4, 5, 4, 2, 0 };
        int testKey1 = 5;
        int counter = 1;

        // Returned array from ArrayUtils
        boolean[] returnedBool = { ArrayUtils.contains(testArr1, testKey1) };
        String res = Arrays.toString(returnedBool);

        // Test 1
        String methodName = "contains", description = "Sending in a number that is in array.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArr1),
                input2 = Integer.toString(testKey1), expectedRes = "[true]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 2
        int[] testArr2 = { 1, 2, 3, 4, 5, 4, 2, 0 };
        int testKey2 = 6;

        // Returned array from ArrayUtils
        boolean[] returnedBool2 = { ArrayUtils.contains(testArr2, testKey2) };
        String res2 = Arrays.toString(returnedBool2);

        description = "Sending in a number that isn't in the array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr2);
        input2 = Integer.toString(testKey2);
        expectedRes = "[false]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 3
        int[] testArr3 = { 1, 2, 3, 4, 5, 4, 2, 0 };
        int testKey3 = -5;

        // Returned array from ArrayUtils
        boolean[] returnedBool3 = { ArrayUtils.contains(testArr3, testKey3) };
        String res3 = Arrays.toString(returnedBool3);

        description = "Sending in a negative key.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr3);
        input2 = Integer.toString(testKey3);
        expectedRes = "[false]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes);

        String table = testOne + testTwo + testThree;
        return table;
    }

    public static String testCumulativeSums() {
        // Variables for testing
        int[] testArr1 = { 7, 2, 8, 3, 6 };
        int counter = 1;

        // Returned array from ArrayUtils
        int[] returnedArr1 = ArrayUtils.cumulativeSums(testArr1);
        String res = Arrays.toString(returnedArr1);

        // Test 1
        String methodName = "cumulativeSums", description = "Sending in an all positive array.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArr1),
                expectedRes = "[7, 9, 17, 20, 26]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, "", expectedRes, actualRes);
        counter++;

        // Test 2
        int[] testArr2 = { 1, 33, -9, 0, 42 };

        // Returned array from ArrayUtils
        int[] returnedArr2 = ArrayUtils.cumulativeSums(testArr2);
        String res2 = Arrays.toString(returnedArr2);

        description = "Sending in a negative in the array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr2);
        expectedRes = "[1, 34, 25, 25, 67]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, "", expectedRes, actualRes);
        counter++;

        // Test 3
        int[] testArr3 = new int[0];

        // Returned array from ArrayUtils
        int[] returnedArr3 = ArrayUtils.cumulativeSums(testArr3);
        String res3 = Arrays.toString(returnedArr3);

        description = "Sending in an empty array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr3);
        expectedRes = "[]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, "", expectedRes, actualRes);
        counter++;

        // Test 4
        int[] testArr4 = { -4, -33, -6, -3, -100 };

        // Returned array from ArrayUtils
        int[] returnedArr4 = ArrayUtils.cumulativeSums(testArr4);
        String res4 = Arrays.toString(returnedArr4);

        description = "Sending in all negatives.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr4);
        expectedRes = "[-4, -37, -43, -46, -146]";
        actualRes = res4;
        String testFour = makeTable("", testID, description, input1, "", expectedRes, actualRes);

        String table = testOne + testTwo + testThree + testFour;
        return table;
    }

    public static String testElementwiseSums() {
        // Variables for testing
        int[] testArrA1 = { 7, 2, 8, 3, 6 }, testArrB1 = { 8, 3, 2, 6, 9 };
        int counter = 1;

        // Returned array from ArrayUtils
        int[] returnedArr1 = ArrayUtils.elementwiseSums(testArrA1, testArrB1);
        String res1 = Arrays.toString(returnedArr1);

        // Test 1
        String methodName = "elementwiseSums", description = "Sending in two positive arrays.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArrA1),
                input2 = Arrays.toString(testArrB1), expectedRes = "[15, 5, 10, 9, 15]", actualRes = res1;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 2
        int[] testArrA2 = { -7, -2, -8, -3, -6 }, testArrB2 = { 8, 3, 2, 6, 9 };

        // Returned array from ArrayUtils
        int[] returnedArr2 = ArrayUtils.elementwiseSums(testArrA2, testArrB2);
        String res2 = Arrays.toString(returnedArr2);

        description = "Sending in one positive array and one negative array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArrA2);
        input2 = Arrays.toString(testArrB2);
        expectedRes = "[1, 1, -6, 3, 3]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 3
        int[] testArrA3 = { -7, -2, -8, -3, -6 }, testArrB3 = { -8, -3, -2, -6, -9 };

        // Returned array from ArrayUtils
        int[] returnedArr3 = ArrayUtils.elementwiseSums(testArrA3, testArrB3);
        String res3 = Arrays.toString(returnedArr3);

        description = "Sending in two negative arrays.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArrA3);
        input2 = Arrays.toString(testArrB3);
        expectedRes = "[-15, -5, -10, -9, -15]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes);

        String table = testOne + testTwo + testThree;
        return table;
    }

    public static String testFrequency() {
        // Variables for testing
        int[] testArr1 = { 1, 2, 3, 4, 5, 4, 3, 2, 1 };
        int testKey1 = 4;
        int counter = 1;

        // Returned array from ArrayUtils
        int returnedInt1 = ArrayUtils.frequency(testArr1, testKey1);
        int[] intArr1 = { returnedInt1 };
        String res = Arrays.toString(intArr1);

        // Test 1
        String methodName = "frequency", description = "Sending in an array with a key - perfect case.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArr1),
                input2 = Integer.toString(testKey1), expectedRes = "[2]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 2
        int[] testArr2 = { 1, 2, 3, 4, 5, 4, 3, 2, 1 };
        int testKey2 = 10;

        // Returned array from ArrayUtils
        int returnedInt2 = ArrayUtils.frequency(testArr2, testKey2);
        int[] intArr2 = { returnedInt2 };
        String res2 = Arrays.toString(intArr2);

        description = "Sending in a key that isn't in array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr2);
        input2 = Integer.toString(testKey2);
        expectedRes = "[0]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 3
        int[] testArr3 = {};
        int testKey3 = 10;

        // Returned array from ArrayUtils
        int returnedInt3 = ArrayUtils.frequency(testArr3, testKey3);
        int[] intArr3 = { returnedInt3 };
        String res3 = Arrays.toString(intArr3);

        description = "Sending in a key and an empty array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr3);
        input2 = Integer.toString(testKey3);
        expectedRes = "[0]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        // Test 4
        int[] testArr4 = { -5, -5, -5, -5, -5 };
        int testKey4 = -5;

        // Returned array from ArrayUtils
        int returnedInt4 = ArrayUtils.frequency(testArr4, testKey4);
        int[] intArr4 = { returnedInt4 };
        String res4 = Arrays.toString(intArr4);

        description = "Sending in a negative key and a negative array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr4);
        input2 = Integer.toString(testKey4);
        expectedRes = "[5]";
        actualRes = res4;

        String testFour = makeTable("", testID, description, input1, input2, expectedRes, actualRes);

        String table = testOne + testTwo + testThree + testFour;
        return table;
    }

    public static String testIsSorted() {
        // Variables for testing
        int[] testArr1 = { 1, 2, 3, 4, 5 };
        int counter = 1;

        // Returned result from ArrayUtils
        boolean returnedBool1 = ArrayUtils.isSorted(testArr1);
        String res1 = Boolean.toString(returnedBool1);
        // String[] strArr1 = {res1};

        // Test 1
        String methodName = "isSorted", description = "Sending in a sorted array.", testID = methodName + "-" + counter,
                input1 = Arrays.toString(testArr1), expectedRes = "true", actualRes = res1;

        String testOne = makeTable(methodName, testID, description, input1, "", expectedRes, actualRes);
        counter++;

        // Test 2
        int[] testArr2 = { 1, 2, 3, 5, 4 };

        // Returned result from ArrayUtils
        boolean returnedBool2 = ArrayUtils.isSorted(testArr2);
        String res2 = Boolean.toString(returnedBool2);

        description = "Sending in an unsorted array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr2);
        expectedRes = "false";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, "", expectedRes, actualRes);
        counter++;

        // Test 3
        int[] testArr3 = { -3, -2, -1, 0, 1, 2, 3 };

        // Returned result from ArrayUtils
        boolean returnedBool3 = ArrayUtils.isSorted(testArr3);
        String res3 = Boolean.toString(returnedBool3);

        description = "Sending in a sorted array with negative numbers.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr3);
        expectedRes = "true";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, "", expectedRes, actualRes);
        counter++;

        // Test 4
        int[] testArr4 = new int[0];

        // Returned result from ArrayUtils
        boolean returnedBool4 = ArrayUtils.isSorted(testArr4);
        String res4 = Boolean.toString(returnedBool4);

        description = "Sending in an empty array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr4);
        expectedRes = "false";
        actualRes = res4;

        String testFour = makeTable("", testID, description, input1, "", expectedRes, actualRes);
        counter++;

        String table = testOne + testTwo + testThree + testFour;
        return table;
    }

    public static String testReverse() {
        int[] inputOneTestOne = { 1, 4, 7 };
        int counter = 1;
        int[] returnedArr = ArrayUtils.reverse(inputOneTestOne);
        String res = Arrays.toString(returnedArr);

        // test 1
        String methodName = "reverse", description = "Reverse order of elements in an array.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(inputOneTestOne),
                expectedRes = "[7, 4, 1]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, "", expectedRes, actualRes);
        counter++;

        // test 2
        // empty array
        int[] inputOneTestTwo = {};
        int[] returnedArrTwo = ArrayUtils.reverse(inputOneTestTwo);
        String res2 = Arrays.toString(returnedArrTwo);

        testID = methodName + "-" + counter;
        description = "Sent empty array";
        input1 = Arrays.toString(inputOneTestTwo);
        expectedRes = "[]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, "", expectedRes, actualRes);
        counter++;

        return testOne + testTwo;
    }

    public static String testMerge() {
        int[] inputOneTestOne = { 1, 4, 7 };
        int[] inputTwoTestOne = { 6, 5, 3 };
        int counter = 1;
        int[] returnedArr = ArrayUtils.merge(inputOneTestOne, inputTwoTestOne);
        String res = Arrays.toString(returnedArr);

        // test 1
        String methodName = "merge", description = "Merge two equal length arrays", testID = methodName + "-" + counter,
                input1 = Arrays.toString(inputOneTestOne), input2 = Arrays.toString(inputTwoTestOne),
                expectedRes = "[1, 3, 4, 5, 6, 7]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testOne);
        counter++;

        // test 2
        // mismatched lengths and negative numbers
        int[] inputOneTestTwo = { 1, 4, 7, -5, -7, 8 };
        int[] inputTwoTestTwo = { 6, 5, 3 };
        int[] returnedArrTwo = ArrayUtils.merge(inputOneTestTwo, inputTwoTestTwo);
        String res2 = Arrays.toString(returnedArrTwo);

        testID = methodName + "-" + counter;
        description = "Mismatched arrays and negative numbers";
        input1 = Arrays.toString(inputOneTestTwo);
        input2 = Arrays.toString(inputTwoTestTwo);
        expectedRes = "[-7, -5, 1, 3, 4, 5, 6, 7, 8]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testTwo);
        counter++;

        // test 3
        // both arrays empty
        int[] inputOneTestThree = {};
        int[] inputTwoTestThree = {};
        int[] returnedArrThree = ArrayUtils.merge(inputOneTestThree, inputTwoTestThree);
        String res3 = Arrays.toString(returnedArrThree);

        testID = methodName + "-" + counter;
        description = "Sending in two empty arrays.";
        input1 = Arrays.toString(inputOneTestThree);
        input2 = Arrays.toString(inputTwoTestThree);
        expectedRes = "[]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testThree);
        counter++;

        // System.out.println(Arrays.toString(returnedArr));
        return testOne + testTwo + testThree;
    }

    public static String testScale() {
        // Variables for testing
        int[] testArr = { 1, 2, 3, 4, 5 };
        int scalar1 = 5;
        int counter = 1;
        // Returned array from ArrayUtils
        int[] returnedArr = ArrayUtils.scale(testArr, scalar1);
        String res = Arrays.toString(returnedArr);

        // test 1
        String methodName = "scale", description = "Scale array up by 5x.", testID = methodName + "-" + counter,
                input1 = Arrays.toString(testArr), input2 = Integer.toString(scalar1),
                expectedRes = "[5, 10, 15, 20, 25]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testOne);
        counter++;

        // test 2
        // mismatched lengths should return an empty array
        int[] testArr2 = { 1, 2, 5, 4, 5 };
        int scalar2 = -5;
        int[] returnedArr2 = ArrayUtils.scale(testArr2, scalar2);
        String res2 = Arrays.toString(returnedArr2);

        testID = methodName + "-" + counter;
        description = "Scaling the array by -5x";
        input1 = Arrays.toString(testArr2);
        input2 = Integer.toString(scalar2);
        expectedRes = "[-5, -10, -25, -20, -25]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        // System.out.println(testTwo);
        counter++;

        // test 3
        // mismatched lengths should return an empty array
        int[] testArr3 = { 1, 2, 5, 4, 5 };
        int scalar3 = 0;
        int[] returnedArr3 = ArrayUtils.scale(testArr3, scalar3);
        String res3 = Arrays.toString(returnedArr3);

        testID = methodName + "-" + counter;
        description = "Scaling the array by 0";
        input1 = Arrays.toString(testArr3);
        input2 = Integer.toString(scalar3);
        expectedRes = "[0, 0, 0, 0, 0]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes);
        counter++;

        return testOne + testTwo + testThree;
    }

}
