package arrayUtils;

import java.util.Arrays;

/**
 *
 * @author shawn
 */
public class Tester {

    public static void main(String[] args) {
        String head = makeHeader();

        testApplyMask();
        testConcatenate();
        testContains();
        testCumulativeSums();
        testElementwiseSums();
        testFrequency();
        testIsSorted();
        testReverse();
        testMerge();
        testScale();
        htmlListBuilder("[1, 2, 3, 4, 5]");
    }

    public static String makeHeader() {
        String head = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Array Utils</title>"
                + "<style>"
                + "table, tr, th, td {"
                + "border: thin black solid;"
                + "border-collapse: collapse;"
                + "}"
                + "ul {"
                + "float: left;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>";

        return head;
    }

    public static String makeTable(String methodName, String description, String input1, String input2, String expectedRes, String actualRes) {
        boolean pass = false;
        if (expectedRes == actualRes) {
            pass = true;
        }
        String table = "<h1> Method: " + methodName + "</h1>";
        table += "<table><tr><th>Test ID</th><th>Description</th><th>Inputs</th><th>Expected Result</th><th>Actual Result</th><th>Pass?</th></tr>";
        table += "<tr><td>" + methodName + "</td><td>" + description + "</td><td>";

        //if input 2 == null or 0
        //only call the array listify for input1
        //else call for both
        table += "</td><td>expectedRes</td><td>" + actualRes + "</td><td>" + pass + "</td></tr>";
        return "";
    }

    public static String htmlListBuilder(String str) {
        String subStr = str.substring(1, str.length() - 1);
        System.out.println(subStr);
        String[] splitStr = subStr.split(", ");
        
        String list = "<ul>";
        
        for (int i = 0; i < splitStr.length; i++) {
            list += "<li>" + splitStr[i] + "</li>";
        }
        list += "</ul>";
        System.out.println(list);
        //list += "<li>" ++  "<  / li > <li>2</li > <li>3</li > <li>4</li > <li>5</li > <     / ul > <ul> < li > true <  / li > <li>false</li > <li>true</li > <li>true</li > <li>";
        //false</li >
        //list += "</ul>";
        return list;
    }

    public static String testApplyMask() {
        int[] testArr = {1, 2, 3, 4, 5};
        boolean[] testMask = {true, false, true, true, false};
        String result = "",
                methodName = "applyMask";

        int[] returnedArr = ArrayUtils.applyMask(testArr, testMask);

        String listArr = "",
                maskArr = "";

        for (int i = 0, length = testArr.length; i < length; i++) {
            listArr += "<li>" + testArr[i] + "</li>";
            // Might have to put in it's own loop since testArr/maskArr could have diff lengths
            maskArr += "<li>" + testMask[i] + "</li>";
        }

        result = "<h1>Method: " + methodName + "<h1>";
        result += "<table><tr><th>Test ID</th><th>Description</th><th>Inputs</th><th>Expected Result</th><th>Actual Result</th><th>Pass?</th></tr>";
        result += "<tr><td>" + methodName + "</td><td>Testing shit?</td><td>"
                + "<ul>" + listArr + "</ul>"
                + "<ul>" + maskArr + "</ul>"
                + "</td><td>Something</td><td>Something2</td><td>OfCourse</td></tr>";
        System.out.println(result);
        System.out.println("Apply Mask: " + Arrays.toString(returnedArr));
        return result;
    }

    public static String testConcatenate() {
        int[] testArrA = {1, 2, 3},
                testArrB = {4, 5, 6};
        String result = "";

        // Catching concatenated array from method
        int[] returnedArr = ArrayUtils.concatenate(testArrA, testArrB);
        // Converting array to string
        result = Arrays.toString(returnedArr);

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
