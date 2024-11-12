import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      System.out.println("Unsorted Array ---------------------------------------------------");
      ArrayList<Integer> integerList = Lab4.getList();
      Lab4.outputList(integerList);

      //Bubble Time
      System.out.println("\n\nBubble sort results ----------------------------------------------");
      ArrayList<Integer> bubbleSortedList = new ArrayList<>(integerList);
      long bubbleStartTime = System.nanoTime();
      bubbleSortedList = Lab4.bubbleSort(integerList);
      long bubbleEndTime = System.nanoTime();
      Lab4.outputList(bubbleSortedList);
      System.out.println("\n Bubble sort time: " + (bubbleEndTime - bubbleStartTime) + " nanoseconds.");

      System.out.println("\n\nInsertion sort results -------------------------------------------");
      ArrayList<Integer> insertionSortedList = new ArrayList<>(integerList);
      long insertionStartTime = System.nanoTime();
      insertionSortedList = Lab4.bubbleSort(insertionSortedList);
      long insertionEndTime = System.nanoTime();
      Lab4.outputList(insertionSortedList);
      System.out.println("\n Insertion sort time: " + (insertionEndTime - insertionStartTime + " nanoseconds."));
    }
}

class Lab4 {
  public static ArrayList<Integer> insertionSort(ArrayList<Integer> integerList) {
    // Step 1 - Implement insertion sort algorithm here
    for (int i = 1;i < integerList.size(); i++){
      int key = integerList.get(i);
      int j = i - 1;

    while (j >= 0 && integerList.get(j) > key){
      integerList.set(j + 1, integerList.get(j));
      j = j - 1;
    }
    integerList.set(j + 1, key);
    }
    return integerList;
  }

  public static ArrayList<Integer> bubbleSort(ArrayList<Integer> integerList) {
    // Step 2 - Implement the bubble sort algorithm here
    int n = integerList.size();
    for (int i = 0; i < n - 1; i++){
      for (int j = 0; j < n - i - 1; j++){
        if (integerList.get(j) > integerList.get(j+1)){
          int temp = integerList.get(j);
          integerList.set(j, integerList.get(j + 1));
          integerList.set(j + 1, temp);
        }
      }
    }
    return integerList;
  }

  public static ArrayList<Integer> getList() {
    ArrayList<Integer> integerList = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("integers.txt"))) {
        while ((line = br.readLine()) != null) {
            integerList.add(Integer.parseInt(line));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return integerList;
  }

  public static void outputList(ArrayList<Integer> integerList) {
    for (int i = 0; i < integerList.size(); i++) {
        System.out.print(integerList.get(i) + " ");
    }
  }
}


//1. If I were choosing a sort algorithm to implement I would probably go with mergesort because it has better average performance (O(n log n)) compared to bubble or insertion (both O(n^2)).

//2. There was a difference in the time it took for bubble and insertion sort to run, about 0.00968224 seconds with insertion sort being the quicker algorithm. This does make sense with their given time complexities. Bubble has a worst-case time complexity of O(n^2), needing to make multiple passes of the list to "bubble"
// the largest unsorted elements to the end. Insertion also has a worst-case time complexity of O(n^2), but performs fewer operations than bubble on smaller data sets--making it quicker.

//3. For me, insertion sort was easier to understand and implement. It takes one element at a time and places it in its correct position within the sorted portion of the list, which is similar to how one would go about sorting something like a deck of cards naturally. 


//Joe Usner