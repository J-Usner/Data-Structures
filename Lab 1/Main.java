//import java.util.*;
/*
 * IT-2660 - Lab 1
 * Student Name: Joe Usner
 */

public class Main {
  public static void main(String[] args) {
    System.out.println("hello, world!");

    Lab1 lab = new Lab1();
    System.out.println(lab.increment(1));

    int[] array = {5, 9, 3, 12, 7, 3, 11, 5};


    System.out.println("Array in order, using while loop: ");
    int i = 0;
    while (i < array.length){
      System.out.print(array[i] + " ");
      i++;
    }
    System.out.println();


    System.out.println("Array in reverse order, using for loop: ");
      for (int j = array.length - 1; j >= 0; j--){
        System.out.print(array[j] + " ");
      }
    System.out.println();


    System.out.println("First value in array: " + array[0]);
    System.out.println("Last value in array: " + array[array.length - 1]);

    System.out.println("Maximum of 2 and 9 " + lab.max(2, 9));
    System.out.println("Minimum of 6 and 25 " + lab.min(6, 25));
    System.out.println("Sum of array equals: " + lab.sum(array));
    System.out.println("The average of the array is: " + lab.average(array));
    System.out.println("Maximum value in the array is: " + lab.max(array));
    System.out.println("Minimum value in the array is: " + lab.min(array));


  }
}     

// Add all of the methods here
class Lab1 {
  public int increment(int num) {
    return ++num;
  }

  public int max(int a, int b){
    if (a > b){
      return a;
    }else{
      return b;
     }
    }

    public int min(int a, int b){
      if (a < b){
      return a;
    }else{
      return b;
     }
    }

    public int sum(int [] nums){
      int total = 0;
      for(int num : nums) {
        total += num;
      }
      return total;
    }

    public double average(int[] nums){
      int total = 0;
      for (int num : nums){
        total += num;
      }
      return(double) total / nums.length;
    }

    public int max(int[] nums){
      int maximum = nums[0];
      for (int i = 1; i< nums.length; i++){
        if (nums[i] > maximum){
          maximum = nums[i];
        }
      }
      return maximum;
    }

    public int min(int[] nums){
      int minimum = nums[0];
      for (int i = 1; i < nums.length; i++){
        if (nums[i] < minimum){
          minimum = nums[i];
        }
      }
      return minimum;
    }


  }