/**
 * Ex14.java is a class for mmn 14 Ex' in "intro to cs and java" course in the open university.
 *
 * @author : Daniel Leibner
 * @class: Ex14
 * @authorId : #########
 * @version: 28/05/21
 */
public class Ex14 {

//code for Sub-exercise 1.

    /**
     * maxDrop is a method that takes an array with numbers representing heights.
     * The method returns the maximum fall between two numbers (not necessarily in consecutive cells)
     * so that the highest number is before the lowest number in the array.
     * complexity of O(n) .
     *
     * @param a array with numbers representing heights,
     * @return maximum fall between two numbers (not necessarily in consecutive cells so that the highest number is before the lowest number in the array.
     */
    public static int maximalDrop(int[] a) {
        if (a.length < 3) {
            if (a.length < 2) {
                return -1;
            } else if (a[0] > a[1]) {//using if to avoid negative numbers.
                return (a[0] - a[1]);
            } else {
                return -1;
            }
        } else if (a[0] >= a[1]) {//using realDrop to avoid negative numbers.
            return maximalDrop(a, 0, 0, a[0] - a[1]);
        }
        return maximalDrop(a, 0, 0, -1);
    }

    /**
     * maximalDrop is a overriding method that takes an array with numbers representing heights.
     * The method returns the maximum fall between two numbers (not necessarily in consecutive cells)
     * so that the highest number is before the lowest number in the array.
     *
     * @param a           array with numbers representing heights
     * @param indexMaxNum the index of the maximum number in the array.
     * @param index       the index to check the drop from.
     * @param maxDrop     the maximum drop until the index below.
     * @return the max drop in the array from index to end of the array
     */
    private static int maximalDrop(int[] a, int indexMaxNum, int index, int maxDrop) {
        if (index >= a.length) //bewaring out of bounds problem
            return maxDrop;
        else if (a[indexMaxNum] > a[index]) {
            if (maxDrop < Math.abs((a[indexMaxNum] - a[index]))) {//found larger drop.
                maxDrop = Math.abs(a[indexMaxNum] - a[index]);
            }
          //recursive call to the rest of the array.
        }if (a[indexMaxNum] < a[index])  //found larger max number in the array
            indexMaxNum = index;
        return maximalDrop(a, indexMaxNum, index + 1, maxDrop);
    }

    //end of code to Sub-exercise 1.

    //code for Sub-exercise 2.


    /**
     * isSink is a method that finds "sink" in a two-dimensional quadratic array. We define that k is a sink
     * (if in the k-row all the values are 0,and in the k-column all the values are 1 except
     * the member [k] [k [which is 0).
     *  complexity of O(n) .
     *
     * @param mat A nXn square two-dimensional array filled with zeros and ones only
     * @return number representing the "sink" place in the array (if it exists) if it does not exist will return -1.
     */
    public static int isSink(int[][] mat) {
        int rowIndex = 0;
        int colIndex = 0;
        while (rowIndex < mat.length && colIndex < mat[0].length) { //bewaring out of bounds problem
            if (mat[rowIndex][colIndex] == 0) {
                colIndex++; //checking if the row on rowIndex is all zeros.
            } else {
                rowIndex++; //moving to next row that may be the zeros row we need to find.
            }
        }
        if (rowIndex == mat.length) { //we didn't find row that have zeros to all her line or the
            // row eliminated because the corresponding column to the  k node.
            return -1;
        }
        //checking the column of the row/Index indicator we found above:
        for (int indexIndicator = 0; indexIndicator < mat.length; indexIndicator++) {
            if (mat[rowIndex][indexIndicator] != 0) { //mean it is one and not fit for "sink" pattern.
                return -1;
            }
            if (indexIndicator != rowIndex && mat[indexIndicator][rowIndex] != 1) { //mean it is one and not fit for "sink" pattern.
                return -1;
            }
        }
        return rowIndex; //mean we found a sink in place rowIndex.
    }

    //end of code to Sub-exercise 2.

    //code for Sub-exercise 3.

    /**
     * size is a method that receives as input a two-dimensional Boolean array of size n X m,
     * (that each of the cells in the array can be empty or full))
     * and a pair of integers representing a cell in the array.
     * The function will return the size of the stain containing this cell.
     * If the cell is not part of a stain, zero will be returned.
     * A stain is called a sequence of solid squares with a common side or a common vertex.
     *
     * @param mat Boolean array of size n X m each of the array cell can be empty or full
     * @param x   the "row" cartesian value of the checked cell.
     * @param y   the "column" cartesian value of the checked cell.
     * @return the size of the stain containing this cell
     */
    public static int size(boolean[][] mat, int x, int y) {

        //All arrays in Java are initialized to the default value for the type,so:
        //      arrays of booleans are initialised to false
        boolean[][] alreadyChecked = new boolean[mat.length][mat[0].length];
        return size(mat, x, y, alreadyChecked); //call a recursive manhood to check the array with a overloading methode.
    }

    /**
     * size is a method that receives as input a two-dimensional Boolean array of size n X m,
     * (that each of the cells in the array can be empty or full))
     * and a pair of integers representing a cell in the array.
     * The function will return the size of the stain containing this cell.
     * If the cell is not part of a stain, zero will be returned.
     * A stain is called a sequence of solid squares with a common side or a common vertex.
     *
     * @param mat            Boolean array of size n X m each of the array cell can be empty or full
     * @param x              the "row" cartesian value of the checked cell.
     * @param y              the "column" cartesian value of the checked cell.
     * @param alreadyChecked Boolean array of size n X m represents the cells was already checked in the mat array
     * @return the size of the stain containing this cell
     */
    private static int size(boolean[][] mat, int x, int y, boolean[][] alreadyChecked) {
        int stainCounter = 0; //count the unAlreadyChecked cells make the checked stain
        if (entryCheck(x, y, alreadyChecked)) { //check is valid cell.
            alreadyChecked[x][y] = true;
            if (mat[x][y]) {
                stainCounter++;
                if (entryCheck(x - 1, y - 1, alreadyChecked) && mat[x - 1][y - 1]) {
                    stainCounter += size(mat, x - 1, y - 1, alreadyChecked);
                }
                if (entryCheck(x - 1, y, alreadyChecked) && mat[x - 1][y]) {
                    stainCounter += size(mat, x - 1, y, alreadyChecked);
                }
                if (entryCheck(x - 1, y + 1, alreadyChecked) && mat[x - 1][y + 1]) {
                    stainCounter += size(mat, x - 1, y + 1, alreadyChecked);
                }
                if (entryCheck(x, y + 1, alreadyChecked) && mat[x][y + 1]) {
                    stainCounter += size(mat, x, y + 1, alreadyChecked);
                }
                if (entryCheck(x + 1, y + 1, alreadyChecked) && mat[x + 1][y + 1]) {
                    stainCounter += size(mat, x + 1, y + 1, alreadyChecked);
                }
                if (entryCheck(x + 1, y, alreadyChecked) && mat[x + 1][y]) {
                    stainCounter += size(mat, x + 1, y, alreadyChecked);
                }
                if (entryCheck(x + 1, y - 1, alreadyChecked) && mat[x + 1][y - 1]) {
                    stainCounter += size(mat, x + 1, y - 1, alreadyChecked);
                }
                if (entryCheck(x, y - 1, alreadyChecked) && mat[x][y - 1]) {
                    stainCounter += size(mat, x, y - 1, alreadyChecked);
                }
            }
        }
        return stainCounter;
    }


    /**
     * check if the given cell is valid for check.
     * valid cell will be in bounds and a cell that never checked before
     *
     * @param x              the "row" cartesian value of the checked cell.
     * @param y              the "column" cartesian value of the checked cell.
     * @param alreadyChecked Boolean array of size n X m represents the cells was already checked in the mat array
     * @return true if the (x,y) place is valid for check.
     */
    private static boolean entryCheck(int x, int y, boolean[][] alreadyChecked) {
        return x >= 0 && x < alreadyChecked.length && y >= 0 && y < alreadyChecked[0].length && !alreadyChecked[x][y];
    }


    //end of code to Sub-exercise 3.

    //code for Sub-exercise 4.

    /**
     * isPermutation is a method that accepts two int arrays filled with
     * integers and returns true if they are a permutation of each other.
     * they contain exactly the same members but may appear in different order.
     *
     * @param a first int arrays filled with integers
     * @param b second int arrays filled with integers
     * @return true if they are a permutation of each other.
     */
    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length == b.length) {//not same length mean not same number of member in the group.
            boolean[] booleanUsedB = new boolean[b.length];//array represent the already used number in the B array.
            //call a recursive manhood to check the array with a overloading methode:
            return isPermutation(a, b, booleanUsedB, 0, 0);
        }
        return false; //not same length mean not same number of member in the group,breakpoint to recursion
    }

    /**
     * isPermutation is a overloading method that accepts two int arrays filled with
     * integers and returns true if they are a permutation of each other.
     * they contain exactly the same members but may appear in different order.
     *
     * @param a             first int arrays filled with integers
     * @param b             second int arrays filled with integers
     * @param booleanUsedB  array represent the already used number in the a array
     * @param indexToArrayA index of the cell checked in the a array
     * @param indexToArrayB index of the cell checked in the b array
     * @return true if array a and b are a permutation of each other.
     */
    private static boolean isPermutation(int[] a, int[] b, boolean[] booleanUsedB, int indexToArrayA, int indexToArrayB) {
        if (indexToArrayB >= b.length) { //mean the number in a[indexToArrayA] not found unused in the b array.
            return false; //breakpoint to recursion
        }
        if (indexToArrayA >= a.length) { //mean all the numbers in array a found unused in the b array.
            return true;  //breakpoint to recursion
        }
        if (a[indexToArrayA] == b[indexToArrayB]) {//found attachment in two array.
            if (booleanUsedB[indexToArrayB]) { // check if is unused.
                indexToArrayB++;  //make index to the recursive call so be start after the used b cell
                return isPermutation(a, b, booleanUsedB, indexToArrayA, indexToArrayB);//the recursive call
            } else {
                indexToArrayA++; //move the a index to next cell in the a array.
                booleanUsedB[indexToArrayB] = true; //mark the cell as used
                return isPermutation(a, b, booleanUsedB, indexToArrayA, 0);//the recursive call
            }
        }
        indexToArrayB++; //make index to the recursive call so be start after the unmatched b cell
        return isPermutation(a, b, booleanUsedB, indexToArrayA, indexToArrayB);//the recursive call
    }

}


