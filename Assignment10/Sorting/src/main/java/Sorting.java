// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 10
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.util.function.Consumer;
import javax.swing.JOptionPane;

/** Code for Sorting Experiment
 *   - testing code
 *   - sorting algorithms
 *   - utility methods for creating, testing, printing, copying arrays
 */

public class Sorting{

    /* Example method for testing and timing sorting algorithms.
     *  You will need to modify and extend this heavily to do your
     *  performance testing. It should probably run tests on each of the algorithms,
     *  on different sized arrays, and multiple times on each size.
     *  Make sure you create a new array each time you sort - it is not a good test if
     *  you resort the same array after it has been sorted.
     *  Hint: if you want to copy an array, use copyArray (below)
     */
    private int size = 500; // 10000;
    private String[] data;
    private void testSorts() {
        String[] names = {"selection", "insertion", "quick", "quick2", "merge"};
        String[] types = {"random","sorted","reversed"};
        long start;
        ArrayList<Consumer<String[]>> sorts = new ArrayList<>();
        sorts.add(this::selectionSort);
        sorts.add(this::insertionSort);
        sorts.add(this::quickSort);
        sorts.add(this::quickSort2);
        sorts.add(this::mergeSort);

        for (int j = 0; j < names.length; j++) {
            UI.println(names[j] + " :");

            for (int x = 0; x < types.length; x++) {
                UI.println(types[x]);

                    for (size = 10; size < 100001; size *= 10) {
                        UI.println("Size="+size);
                        sortTypeArray(x);

                        long count = 0;
                        for (int i = 0; i < 5; i++) {
                        start = System.currentTimeMillis();
                        sorts.get(j).accept(data);
                        count += System.currentTimeMillis() - start;
                    }

                    count /= 5;
                    UI.println("Average: " + count + " milliseconds");
                    UI.println(testSorted(data) + "\n");
                }
            }

        }
    }

    private void sortTypeArray(int i){
       data = createArray(size);
       if(i==0){
            return;
       }
       Arrays.sort(data);
       if(i==1){
           return;
       }
       reverseArray(data);
    }


    /* =============== SWAP ================= */

    /** Swaps the specified elements of an array.
     *  Used in several of the sorting algorithms
     */
    private  void swap(String[ ] data, int index1, int index2){
        if (index1==index2) return;
        String temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    /* ===============SELECTION SORT================= */

    /** Sorts the elements of an array of String using selection sort */
    private void selectionSort(String[] data){
        // for each position, from 0 up, find the next smallest item 
        // and swap it into place
        for (int place=0; place<data.length-1; place++){
            int minIndex = place;
            for (int sweep=place+1; sweep<data.length; sweep++){
                if (data[sweep].compareTo(data[minIndex]) < 0)
                    minIndex=sweep;
            }
            swap(data, place, minIndex);
        }
    }


    /* ===============INSERTION SORT================= */
    /** Sorts the  elements of an array of String using insertion sort */
    private void insertionSort(String[] data){
        // for each item, from 0, insert into place in the sorted region (0..i-1)
        for (int i=1; i<data.length; i++){
            String item = data[i];
            int place = i;
            while (place > 0  &&  item.compareTo(data[place-1]) < 0){
                data[place] = data[place-1];       // move up
                place--;
            }
            data[place]= item;
        }
    } 


    /* ===============MERGE SORT================= */
    /** non-recursive, wrapper method
     *  copy data array into a temporary array 
     *  call recursive mergeSort method     
     */
    private void mergeSort(String[] data) {
        String[] other = new String[data.length];
        System.arraycopy(data, 0, other, 0, data.length);
        mergeSort(data, other, 0, data.length); //call to recursive merge sort method
    }

    /** Recursive mergeSort method */
    private void mergeSort(String[] data, String[] temp, int low, int high) {
        if(low < high-1) {
            int mid = ( low + high ) / 2;
            mergeSort(temp, data, low, mid);
            mergeSort(temp, data, mid, high);
            merge(temp, data, low, mid, high);
        }
    }


    /** Merge method
     *  Merge from[low..mid-1] with from[mid..high-1] into to[low..high-1]
     *  Print data array after merge using printData
     */
    private void merge(String[] from, String[] to, int low, int mid, int high) {
        int index = low;      //where we will put the item into "to"
        int indexLeft = low;   //index into the lower half of the "from" range
        int indexRight = mid; // index into the upper half of the "from" range
        while (indexLeft<mid && indexRight < high) {
            if ( from[indexLeft].compareTo(from[indexRight]) <=0 )
                to[index++] = from[indexLeft++];
            else
                to[index++] = from[indexRight++];
        }
        // copy over the remainder. Note only one loop will do anything.
        while (indexLeft<mid)
            to[index++] = from[indexLeft++];
        while (indexRight<high)
            to[index++] = from[indexRight++];
    }

    /*===============QUICK SORT=================*/
    /* Sort data using QuickSort
       Print time taken by Quick sort
       Print number of times partition gets called
     */

    /** Quick sort recursive call */
    private void quickSort(String[] data) {
        quickSort(data, 0, data.length);
    }

    private void quickSort(String[] data, int low, int high) {
        if (high-low < 2)      // only one item to sort.
            return;
        else {     // split into two parts, mid = index of boundary
            int mid = partition(data, low, high);
            quickSort(data, low, mid);
            quickSort(data, mid, high);
        }
    }

    /** Partition into small items (low..mid-1) and large items (mid..high-1) 
     *  Print data array after partition
     */
    private int partition(String[] data, int low, int high) {
        String pivot = data[(low+high)/2];
        int left = low-1;
        int right = high;
        while( left < right ) {
            do { 
                left++;       // just skip over items on the left < pivot
            } 
            while (left<high && data[left].compareTo(pivot) < 0);

            do { 
                right--;     // just skip over items on the right > pivot
            } 
            while (right>=low &&data[right].compareTo(pivot) > 0);

            if (left < right) 
                swap(data, left, right);
        }
        return left;
    }



    /** Quick sort, second version:  simpler partition method
     *   faster or slower?  */
    private void quickSort2(String[] data) {
        quickSort2(data, 0, data.length);
    }

    private void quickSort2(String[] data, int low, int high) {
        if (low+1 >= high) // no items to sort.
            return;
        else {     // split into two parts, mid = index of pivot
            int mid = partition2(data, low, high);
            quickSort2(data, low, mid);
            quickSort2(data, mid+1, high);
        }
    }

    private int partition2(String[] data, int low, int high){
        swap(data, low, (low+high)/2);    // choose pivot and put at position low
        String pivot = data[low];
        int mid = low;
        for(int i = low+1; i < high; i++){  // for each item after the pivot
            if ( data[i].compareTo(pivot) <0 ){
                mid++;                      // move mid point up
                swap(data, i, mid);
            }
        }
        swap(data, low, mid);   // move pivot to the mid point
        return mid;
    }


    /* =====   Utility methods ============================================ */

    /** Tests whether an array is in sorted order
     */
    private boolean testSorted(String[] data) {
        for (int i=1; i<data.length; i++){
            if (data[i].compareTo(data[i-1]) < 0)
                return false;
        }
        return true;
    }

    public void printData(String[] data){
        for (String str : data){
            UI.println(str);
        }
    }

    /** Constructs an array of Strings by making random String values */
    private String[] createArray(int size) {
        Random randGenerator = new Random();
        String[] data = new String[size];
        for (int i=0; i<size; i++){
            char[] chars = new char[5];
            for (int c=0; c<chars.length; c++){
                chars[c] = (char) ('a' + randGenerator.nextInt(26));
            }
            String str = new String(chars);
            data[i] = str;
        }
        return data;
    }

    /** Constructs an array of Strings by reading a file
     * The size of the array will be the specified size, unless the
     * file is too short, or size is -ve, in which cases the array will
     * contain all the tokens in the file.
     */
    public String[] readArrayFromFile(int size, String filename) {
        File file = new File(filename);
        if (!file.exists()){
            UI.println("file "+filename+" does not exist");
            return null;
        }
        List<String> temp = new ArrayList<String> ();
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNext()) 
                temp.add(scan.next());
            scan.close();
        }
        catch(IOException ex) {   // what to do if there is an io error.
            UI.println("File reading failed: " + ex);
        }
        if (temp.size() < size || size<0)
            size = temp.size();

        String[] data = new String[size];
        for (int i =0; i<size; i++){
            data[i] = temp.get(i);
        }
        return data;
    }

    /** Create a new copy of an array of data */
    public String[] copyArray(String[] data){
        String[] newData = new String[data.length];
        System.arraycopy(data, 0, newData, 0, data.length);
        return newData;
    }

    /** Create a new copy of the first size elements of an array of data */
    public String[] copyArray(String[] data, int size){
        if (size> data.length) size = data.length;
        String[] newData = new String[size];
        System.arraycopy(data, 0, newData, 0, size);
        return newData;
    }

    private void reverseArray(String[] data){
        int bot = 0;
        int top = data.length-1;
        while (bot<top){
            swap(data, bot++, top--);
        }
    }

    public static void main(String[] args){
        Sorting sorter = new Sorting();
        sorter.testSorts();
    }

}
