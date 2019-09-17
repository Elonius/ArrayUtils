package arrayUtils;

import java.util.Arrays;

/**
 *
 * @author shawn
 */
public class Tester {

    public static void main(String[] args) {
        String start = startHTML();
        String end = endHTML();

        testApplyMask(start, end);
        /*testConcatenate();
        testContains();
        testCumulativeSums();
        testElementwiseSums();
        testFrequency();
        testIsSorted();
        testReverse();
        testMerge();
        testScale();*/
    }

    public static String startHTML() {
        String start = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Array Utils</title>"
                + "<style>"
                + "table, tr, th, td {"
                + "border: thin black solid;"
                + "border-collapse: collapse;"
                + "padding: 10px;"
                + "margin: 10px;"
                + "}"
                + "ul {"
                + "float: left;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>";

        return start;
    }

    public static String endHTML() {
        String end = "</body></html>";
        return end;
    }

    //if input 2 doesn't exist or isn't an array, send 'null' as input2
    public static String makeTable(String methodName, String testID, String description, String input1, String input2, String expectedRes, String actualRes, String startHTML, String endHTML) {
        boolean pass = false;
        // Checks to see if expected result and actual result match
        if (expectedRes.equals(actualRes)) {
            pass = true;
        }
        String listStr = "";

        // Store in list string
        listStr += htmlListBuilder(input1);

        // This check won't necessarily work because sometimes there is a second argument that isn't an array
        if (input2 != null) {
            //store this in a string
            listStr += htmlListBuilder(input2);
        }
        String table ="";
        if (!startHTML.equals("")){
            table = startHTML;
        }
        
        if (!methodName.equals("")) {
            table += "<h1> Method: " + methodName + "</h1>";
        }

        table += "<table><tr><th>Test ID</th><th>Description</th><th>Inputs</th><th>Expected Result</th><th>Actual Result</th><th>Pass?</th></tr>";
        table += "<tr><td>" + testID + "</td><td>" + description + "</td><td>";
        table += listStr;
        table += "</td><td>" + expectedRes + "</td><td>" + actualRes + "</td><td>" + pass + "</td></tr>";
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

    public static String testApplyMask(String header, String end) {
        // Variables for testing
        int[] testArr = {1, 2, 3, 4, 5};
        boolean[] testMask = {true, false, true, true, false};
        int counter = 1;
        // Returned array from ArrayUtils
        int[] returnedArr = ArrayUtils.applyMask(testArr, testMask);
        String res = Arrays.toString(returnedArr);

        //test 1
        String methodName = "applyMask",
                description = "Perfect case, both arrays same length.",
                testID = methodName + "-" + counter,
                input1 = Arrays.toString(testArr),
                input2 = Arrays.toString(testMask),
                expectedRes = "[1, 3, 4]",
                actualRes = res;

        String testOne = makeTable(methodName, testID, description, input1, input2, expectedRes, actualRes, header, "");
        System.out.println(testOne);
        counter++;

        //test 2
        //mismatched lengths should return an empty array
        int[] testArr2 = {1, 2, 5, 4};
        boolean[] testMask2 = {true, false, true, true, false};
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
        System.out.println(testTwo);
        
        //test 3
        //mismatched lengths should return an empty array
        int[] testArr3 = {1, 2, 5, 4};
        boolean[] testMask3 = {true, false, true};
        int[] returnedArr3 = ArrayUtils.applyMask(testArr3, testMask3);
        String res3 = Arrays.toString(returnedArr3);

        testID = methodName + "-" + counter;
        description = "Sending in a different sized Boolean array.";
        input1 = Arrays.toString(testArr3);
        input2 = Arrays.toString(testMask3);
        expectedRes = "[]";
        actualRes = res3;

        String testThree = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
        System.out.println(testThree);
        counter++;
        
        //test 4
        //mismatched lengths should return an empty array
        int[] testArr4 = {1, 2, 5, 4};
        boolean[] testMask4 = {true, true, true, true};
        int[] returnedArr4 = ArrayUtils.applyMask(testArr4, testMask4);
        String res4 = Arrays.toString(returnedArr4);

        testID = methodName + "-" + counter;
        description = "All elements of Boolean array are true.";
        input1 = Arrays.toString(testArr4);
        input2 = Arrays.toString(testMask4);
        expectedRes = "[1, 2, 5, 4]";
        actualRes = res4;

        String testFour = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", "");
        System.out.println(testFour);
        counter++;
        
        //test 5
        //mismatched lengths should return an empty array
        int[] testArr5 = {1, 2, 5, 4};
        boolean[] testMask5 = {false, false, false, false};
        int[] returnedArr5 = ArrayUtils.applyMask(testArr5, testMask5);
        String res5 = Arrays.toString(returnedArr5);

        testID = methodName + "-" + counter;
        description = "All elements of Boolean array are false.";
        input1 = Arrays.toString(testArr5);
        input2 = Arrays.toString(testMask5);
        expectedRes = "[]";
        actualRes = res5;

        String testFive = makeTable("", testID, description, input1, input2, expectedRes, actualRes, "", end);
        System.out.println(testFive);


        // Add on to table with testTwo, testThree, etc
        String table = testOne + testTwo + testThree + testFour + testFive;

        return table;
    }

    public static String testConcatenate() {
        int[] testArrA = {1, 2, 3},
                testArrB = {4, 5, 6};

        // Catching concatenated array from method
        int[] returnedArr = ArrayUtils.concatenate(testArrA, testArrB);
        // Converting array to string
        String result = Arrays.toString(returnedArr);

        System.out.println("Concatenate: " + result);
        return result;
    }

    public static boolean testContains() {
        int[] arr = {1, 2, 3, 4, 5, 4, 2, 0};
        int key = 5;

        boolean result = ArrayUtils.contains(arr, key);
        System.out.println("Contains: " + result);
        return result;
    }

    public static String testCumulativeSums() {
        int[] testArr = {7, 2, 8, 3, 6};
        String result = "";

        int[] returnedArr = ArrayUtils.cumulativeSums(testArr);

        result = Arrays.toString(returnedArr);
        System.out.println("Cumulative Sum: " + result);
        return result;
    }

    public static String testElementwiseSums() {
        int[] testArrA = {7, 2, 8, 3, 6}, testArrB = {8, 3, 2, 6, 9};

        String result = "";

        int[] returnedArr = ArrayUtils.elementwiseSums(testArrA, testArrB);

        result = Arrays.toString(returnedArr);
        System.out.println("Elementwise Sums: " + result);
        return result;
    }

    public static int testFrequency() {
        int[] testArr = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        int testKey = 4;

        int matches = ArrayUtils.frequency(testArr, testKey);

        System.out.println("Frequency: " + matches);
        return matches;
    }

    public static boolean testIsSorted() {
        int[] testArr = {1, 2, 3, 4, 5};

        boolean result = ArrayUtils.isSorted(testArr);

        System.out.println("Is Sorted: " + result);
        return result;
    }

    public static int[] testReverse() {
        int[] var = new int[4];
        var[0] = 4;
        var[1] = 3;
        var[2] = 2;
        var[3] = 1;
        System.out.println("Pre-Reverse: " + Arrays.toString(var));

        int[] varReverse;
        varReverse = ArrayUtils.reverse(var);
        System.out.println("Post-Reverse: " + Arrays.toString(varReverse));
        return varReverse;
    }

    public static String testMerge() {
        int[] testArr = {10, 80, 30, 90, 40, 50};
        int[] testArr2 = {13, 85, 33, 70, 43, 52, 79};

        int[] returnedArr = ArrayUtils.merge(testArr, testArr2);
        System.out.println("Merge: " + Arrays.toString(returnedArr));
        return Arrays.toString(returnedArr);
    }

    public static int[] testScale() {
        int[] testArr = {7, 2, 8, 3};
        int testScalar = 5;

        int[] scaledArr = ArrayUtils.scale(testArr, testScalar);
        System.out.println("Scale " + Arrays.toString(scaledArr));
        return scaledArr;
    }

}
