package arrayUtils;

import java.util.Arrays;

/**
 *
 * @author shawn
 */
public class Tester {

    public static void main(String[] args) {
        String start = startHTML();

        String applyMaskHTML = testApplyMask() + "</table>";
        String mergeHTML = "<table>" + testMerge() + "</table>";
        String concatenateHTML = "<table>" + testConcatenate() + "</table>";
        String containsHTML = "<table>" + testContains() + "</table>";

        /*
         * testConcatenate(); testContains(); testCumulativeSums();
         * testElementwiseSums(); testFrequency(); testIsSorted(); testReverse();
         * 
         */
        String reverseHTML = "<table>" + testReverse() + "</table>";
        // last test method
        String scaleHTML = "<table>" + testScale();

        String end = endHTML();
        String output = start + applyMaskHTML + concatenateHTML + containsHTML + reverseHTML + mergeHTML + scaleHTML
                + end;
        System.out.println(output);
    }

    public static String startHTML() {
        String start = "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>Array Utils</title>" + "<style>"
                + "table, tr, th, td {" + "border: thin black solid;" + "border-collapse: collapse;" + "padding: 10px;"
                + "margin: 10px;" + "}" + "ul {" + "float: left;" + "}" + "</style>" + "</head>" + "<body>"
                + "<table><tr><th>Test ID</th><th>Description</th><th>Inputs</th><th>Expected Result</th><th>Actual Result</th><th>Pass?</th></tr>";

        return start;
    }

    public static String endHTML() {
        String end = "</table></body></html>";
        return end;
    }

    // if input 2 doesn't exist or isn't an array, send 'null' as input2
    public static String makeTable(String methodName, String testID, String description, String input1, String input2,
            String expectedRes, String actualRes, String startHTML, String endHTML) {
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
        if (!startHTML.equals("")) {
            table = startHTML;
        }

        if (!methodName.equals("")) {
            table += "<h1> Method: " + methodName + "</h1>";
        }

        // table += "<table><tr><th>Test
        // ID</th><th>Description</th><th>Inputs</th><th>Expected Result</th><th>Actual
        // Result</th><th>Pass?</th></tr>";
        table += "<tr><td>" + testID + "</td><td>" + description + "</td><td>";
        table += listStr;
        table += "</td><td>" + expectedRes + "</td><td>" + actualRes + "</td><td>" + pass
                + "</td></tr>"/* "</table>" */;
        table += endHTML;
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

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes, "", "");
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

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
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

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
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

        String testFour = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
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

        String testFive = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
        // System.out.println(testFive);

        // Add on to table with testTwo, testThree, etc
        String table = testOne + testTwo + testThree + testFour + testFive;

        return table;
    }

    public static String testConcatenate() {
        // Variables for testing
        int[] testArr1 = { 1, 2, 3 }, testArr2 = { 4, 5, 6 };
        int counter = 1;

        // Returned array from ArrayUtils
        int[] returnedArr = ArrayUtils.concatenate(testArr1, testArr2);
        String res = Arrays.toString(returnedArr);

        // Test 1
        String methodName = "concatenate", description = "Perfect case, sending in two arrays.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArr1),
                input2 = Arrays.toString(testArr2), expectedRes = "[1, 2, 3, 4, 5, 6]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes, "", "");
        counter++;

        // Test 2
        int[] testArr3 = {}, testArr4 = { 4, 7, 4, 0, 1 };
        int[] returnedArr2 = ArrayUtils.concatenate(testArr3, testArr4);
        String res2 = Arrays.toString(returnedArr2);

        description = "Sending in an empty array.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr3);
        input2 = Arrays.toString(testArr4);
        expectedRes = "[4, 7, 4, 0, 1]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
        counter++;

        // empty first array, empty second array
        String table = testOne + testTwo;
        // System.out.println("Concatenate: " + table);
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
        String methodName = "contains", description = "Perfect case, He loves it lol.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(testArr1),
                input2 = Integer.toString(testKey1), expectedRes = "[true]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes, "", "");
        counter++;

        // Test 2
        int[] testArr2 = { 1, 2, 3, 4, 5, 4, 2, 0 };
        int testKey2 = 6;

        // Returned array from ArrayUtils
        boolean[] returnedBool2 = { ArrayUtils.contains(testArr2, testKey2) };
        String res2 = Arrays.toString(returnedBool2);

        description = "Perfect case, He loves it lol.";
        testID = methodName + "-" + counter;
        input1 = Arrays.toString(testArr2);
        input2 = Integer.toString(testKey2);
        expectedRes = "[false]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
        counter++;
        String table = testOne + testTwo;

        return table;
    }

    public static String testCumulativeSums() {
        int[] testArr = { 7, 2, 8, 3, 6 };
        String result = "";

        int[] returnedArr = ArrayUtils.cumulativeSums(testArr);

        result = Arrays.toString(returnedArr);
        System.out.println("Cumulative Sum: " + result);
        return result;
    }

    public static String testElementwiseSums() {
        int[] testArrA = { 7, 2, 8, 3, 6 }, testArrB = { 8, 3, 2, 6, 9 };

        String result = "";

        int[] returnedArr = ArrayUtils.elementwiseSums(testArrA, testArrB);

        result = Arrays.toString(returnedArr);
        System.out.println("Elementwise Sums: " + result);
        return result;
    }

    public static int testFrequency() {
        int[] testArr = { 1, 2, 3, 4, 5, 4, 3, 2, 1 };
        int testKey = 4;

        int matches = ArrayUtils.frequency(testArr, testKey);

        System.out.println("Frequency: " + matches);
        return matches;
    }

    public static boolean testIsSorted() {
        int[] testArr = { 1, 2, 3, 4, 5 };

        boolean result = ArrayUtils.isSorted(testArr);

        System.out.println("Is Sorted: " + result);
        return result;
    }

    public static int[] testReverse() {
        int[] inputOneTestOne = { 1, 4, 7 };
        int counter = 1;
        int[] returnedArr = ArrayUtils.reverse(inputOneTestOne);
        String res = Arrays.toString(returnedArr);

        // test 1
        String methodName = "reverse", description = "Reverse order of elements in an array.",
                testID = methodName + "-" + counter, input1 = Arrays.toString(inputOneTestOne),
                expectedRes = "[7, 4, 1]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, "", expectedRes, actualRes, "", "");
        System.out.println(testOne);
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

        String testTwo = makeTable("", testID, description, input1, "", expectedRes, actualRes, "", "");
        System.out.println(testTwo);
        counter++;

        System.out.println(testOne + testTwo);
        return testOne + testTwo;
    }

    public static String testMerge() {
        int[] inputOneTestOne = { 1, 4, 7 };
        int[] inputTwoTestOne = { 6, 5, 3 };
        int counter = 1;
        int[] returnedArr = ArrayUtils.merge(inputOneTestOne, inputTwoTestOne);
        String res = Arrays.toString(returnedArr);

        // test 1
        String methodName = "merge", description = "Scale array up by 5x.", testID = methodName + "-" + counter,
                input1 = Arrays.toString(inputOneTestOne), input2 = Arrays.toString(inputTwoTestOne),
                expectedRes = "[1, 3, 4, 5, 6, 7]", actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes, "", "");
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

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
        // System.out.println(testTwo);
        counter++;

        // test 3
        // both arrays empty
        int[] inputOneTestThree = {};
        int[] inputTwoTestThree = {};
        int[] returnedArrThree = ArrayUtils.merge(inputOneTestThree, inputTwoTestThree);
        String res3 = Arrays.toString(returnedArrThree);

        testID = methodName + "-" + counter;
        description = "Mismatched arrays and negative numbers";
        input1 = Arrays.toString(inputOneTestThree);
        input2 = Arrays.toString(inputTwoTestThree);
        expectedRes = "[]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
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

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes, "", "");
        // System.out.println(testOne);
        counter++;

        // test 2
        // mismatched lengths should return an empty array
        int[] testArr2 = { 1, 2, 5, 4, 5 };
        int scalar2 = -5;
        int[] returnedArr2 = ArrayUtils.scale(testArr2, scalar2);
        String res2 = Arrays.toString(returnedArr2);

        testID = methodName + "-" + counter;
        description = "Scaling the array by -5";
        input1 = Arrays.toString(testArr2);
        input2 = Integer.toString(scalar2);
        expectedRes = "[-5, -10, -25, -20, -25]";
        actualRes = res2;

        String testTwo = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
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

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
        // System.out.println(testThree);
        counter++;

        return testOne + testTwo + testThree;
    }

}
