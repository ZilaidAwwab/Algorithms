public class BinarySearch {

  public static int binarySearch(int[] a, int key) {
    int low = 0, high = a.length - 1;
    
    
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (a[mid] > key) {
        high = mid - 1;
      } else if (a[mid] < key) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }
  public static void main(String[] args) {
    int[] array = {1, 4, 6, 8, 19, 31, 90, 165, 224, 989};
    int key = 6;
    int result = binarySearch(array, key);

    if (result == -1) {
      System.out.println("Number not found.");
    } else {
      System.out.println("Number was found.");
    }
  }
}

/*

*This formula can cause an integer overflow when low and high are both large integers.
mid = (low + high) / 2;

*This formula prevents overflow by ensuring that the addition operation does not exceed the maximum value that can be stored in an integer.
mid = low + (high - low) / 2;

*/
