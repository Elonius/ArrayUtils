package arrayUtils;

import java.util.Arrays;

public class ArrayUtils {

    /**
     * Creates a new array by applying a boolean mask to an input array.
     *
     * @param arr an array of numbers (input array)
     * @param mask an array of boolean values (mask array)
     * @return a new array containing each element of the input array whose
     * corresponding mask value is <code>true</code>, or an empty array if the
     * two input arrays are different lengths.
     *
     * <br><strong>Example:</strong><br>
     * If <var>arr</var> is [7,2,8,3,9,4], and <var>mask</var> is [1,1,0,1,0,0],
     * then the result should be [7,2,3].
     */
    public static int[] applyMask(int[] arr, boolean[] mask) {
        // Declare empty global String   
        String str = "";
        boolean allFalse = true;

        // Checking if sent in arguments lengths match
        if (arr.length != mask.length) {
            // Should return empty array/false
            //System.out.println("Argument lengths don't match..");
            return new int[0];
        } else {

            for (int i = 0, length = mask.length; i < length; i++) {
                int arrValue = arr[i];
                boolean maskValue = mask[i];
                // If mask[] has a true element, put that arr[] element in a string
                if (maskValue) {
                    str += arrValue + ",";
                    allFalse = false;
                }
            }
        } // End of if/else
        int[] intArr;
        //if not all elements of the boolean array are false, parse ints
        if (!allFalse) {
            // Creates new array from split string
            String[] strArr = str.split(",");
            // Creates new int array with size of string array
            intArr = new int[strArr.length];

            // Converting each string element to an int and storing in int array
            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }
        } //all elements of the array are false, the returned array will be empty
        else {
            return new int[0];
        }

        return intArr;
    }

    /**
     * Creates a new array containing all of the elements of two input arrays.
     *
     * @param arrA an input array
     * @param arrB another input array
     * @return a new array containing all the elements from both arrays
     */
    public static int[] concatenate(int[] arrA, int[] arrB) {
        // Get length of both arrays
        int arrASize = arrA.length,
                arrBSize = arrB.length,
                counter = 0;

        // Make new array with combined length
        int[] newArr = new int[arrASize + arrBSize];

        // Loop through each array and add element using counter variable
        for (int i = 0; i < arrASize; i++) {
            newArr[counter] = arrA[i];
            counter++;
        }
        for (int i = 0; i < arrBSize; i++) {
            newArr[counter] = arrB[i];
            counter++;
        }

        return newArr;
    }

    /**
     * Determines whether an array of numbers contains a certain key.
     *
     * @param arr an array of numbers
     * @param key the key to match
     * @return <strong>true</strong> if the key is in the array;
     * <strong>false</strong> otherwise
     */
    public static boolean contains(int[] arr, int key) {
        boolean res = false;

        for (int i = 0, length = arr.length; i < length; i++) {
            int temp = arr[i];

            if (temp == key) {
                res = true;
                break;
            }
        }

        return res;
    }

    /**
     * Creates an array containing the cumulative sums of the input array.
     *
     * @param arr the input array
     * @return a new array containing the cumulative sums
     *
     * <br><strong>Example:</strong><br>
     * If <var>arr</var> is [7,2,8,3,6], then the result should be
     * [7,9,17,20,26].
     */
    public static int[] cumulativeSums(int[] arr) {
        // New array with size equal to sent in array
        int[] newArr = new int[arr.length];

        if (arr.length == 0) {
            return new int[0];
        } else {
            newArr[0] = arr[0]; // Add first index of array to new array
            int firstTwo = arr[0] + arr[1]; // Sum first two numbers of sent in array
            newArr[1] = firstTwo; // Add that summed value to second spot in new array
        }

        // Loop array starting at the third number
        for (int i = 2, length = arr.length; i < length; i++) {
            int temp = arr[i];
            // Sum [i] of sent in array with [i - 1] of new array
            int num = temp + newArr[i - 1];
            newArr[i] = num; // Add that summed number to new array
        }

        return newArr;
    }

    /**
     * Creates an array containing the element-wise sums of two input arrays.
     *
     * @param arrA one input array
     * @param arrB the second input array
     * @return a new array containing the element-wise sums, or an empty array
     * if the two input arrays are different lengths.
     *
     * <br><strong>Example:</strong><br>
     * If <var>arrA</var> is [7,2,8,3,6], and <var>arrB</var> is [8,3,2,6,9],
     * then the result should be [15,5,10,9,15].
     */
    public static int[] elementwiseSums(int[] arrA, int[] arrB) {
        // Getting length of each array
        int lengthA = arrA.length,
                lengthB = arrB.length;
        // Creating new array with size of first sent in array
        int[] newArr = new int[lengthA];

        // If lengths match
        if (lengthA == lengthB) {
            for (int i = 0; i < lengthA; i++) {
                // Put element from each array into a variable
                int numA = arrA[i],
                        numB = arrB[i];
                // Sum elements and add element to new array
                newArr[i] = numA + numB;
            }
            // If they don't match, return an empty array
        } else {
            newArr = new int[0];
        }

        return newArr;
    }

    /**
     * Counts how many elements of an array are equal to a certain key.
     *
     * @param arr an array of numbers
     * @param key the key to match
     * @return the number of matches
     */
    public static int frequency(int[] arr, int key) {
        int counter = 0;

        for (int i = 0, length = arr.length; i < length; i++) {
            int temp = arr[i];
            if (temp == key) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Determines whether an array of numbers is sorted in ascending order.
     *
     * @param arr an array of numbers
     * @return <strong>true</strong> if the array is sorted in ascending order;
     * <strong>false</strong> otherwise.
     */
    public static boolean isSorted(int[] arr) {
        boolean result = true;
        if (arr.length == 0) {
            result = false;
        } else {
            // arr.length - 1 stops loop so it doesn't check last element with nothing
            for (int i = 0, length = arr.length - 1; i < length; i++) {
                int temp = arr[i],
                        nextTemp = arr[i + 1];
                // If left side number is greater than right side number - false
                if (temp > nextTemp) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Merges two sorted arrays.
     *
     * @param arrA an sorted array in ascending order
     * @param arrB another array sorted in ascending order
     * @return the array that results from merging the two arrays while
     * preserving sorting, or an empty array if either input array is not sorted
     *
     * <br><strong>Example:</strong><br>
     * If <var>arrA</var> is [2,7,9,15,18], and <var>arrB</var> is [5,12,24,26],
     * then the result should be [2,5,7,9,12,15,18,24,26].
     */
    public static int[] merge(int[] arrA, int[] arrB) {
        int[] combinedArr = new int[arrA.length + arrB.length];

        for (int i = 0; i < arrA.length; i++) {
            combinedArr[i] = arrA[i];
        }
        for (int j = arrA.length; j < combinedArr.length; j++) {
            combinedArr[j] = arrB[j - arrA.length];
        }

        quickSort(combinedArr, 0, combinedArr.length - 1);

        return combinedArr;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is now
           at right place */
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);  // Before pi
            quickSort(arr, pi + 1, high); // After pi
        }
    }

    public static int partition(int[] arr, int low, int high) {
        // pivot (Element to be placed at right position)
        int pivot = arr[high];

        int i = (low - 1);  // Index of smaller element

        for (int j = low; j <= high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;    // increment index of smaller element
                // Swapping index i and j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swapping index high for (i + 1)
        int temp1 = arr[high];
        arr[high] = arr[i + 1];
        arr[i + 1] = temp1;

        return (i + 1);
    }

    /**
     * Creates a new array with the input array's elements reversed.
     *
     * @param arr the input array
     * @return a new array with the input array's elements in reverse order
     */
    public static int[] reverse(int[] arr) {
        int[] reverseArr = new int[arr.length];
        int posCounter = 0;

        for (int i = arr.length - 1; i > -1; i--) {
            reverseArr[posCounter] = arr[i];
            //System.out.println(Arrays.toString(reverseArr));
            posCounter++;
        }

        return reverseArr;
    }

    /**
     * Multiplies each element of an array by a scalar.
     *
     * @param arr an array of numbers
     * @param scalar the scalar to multiply each array element by
     * @return a new array of numbers such that each element is the original
     * element multiplied by the scalar.
     *
     * <br><strong>Example:</strong><br>
     * If <var>arr</var> is [7,2,8,3] and <var>scalar</var> is 5, then the
     * result should be [35,10,40,15].
     */
    public static int[] scale(int[] arr, int scalar) {
        int[] scaledArr = new int[arr.length];

        for (int i = 0, length = arr.length; i < length; i++) {
            int temp = arr[i];
            temp *= scalar;
            scaledArr[i] = temp;
        }

        return scaledArr;
    }

} // end class ArrayUtils
