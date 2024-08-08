public class BitonicArrayEfficient {
  public static int binarySearch(int[] array, int key) {
    int length = array.length;
    int low = 0, high = length - 1;
  
    // Find the peak element index
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (array[mid] < array[mid + 1]) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    int peak = low; // Peak element index
  
    // Search in the increasing part
    low = 0;
    high = peak;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (array[mid] == key) {
            return mid;
        } else if (array[mid] < key) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
  
    // Search in the decreasing part
    low = peak + 1;
    high = length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (array[mid] == key) {
            return mid;
        } else if (array[mid] > key) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return -1; // Key not found in either half
  }
  
  public static void main(String[] args) {
    int[] array = {1, 4, 6, 9, 12, 19, 16, 15, 11, 8, 2};
    int key = 11;
    int ans = binarySearch(array, key);
    System.out.println(ans); // Should print the index of '11' in the array
  }
}  
