public class BitonicArray {
  public static int firstHalf(int[] array, int key) {
    int length = array.length;
    int low = 0, high = length - 1;
    
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (array[mid] > key) {
        high = mid - 1;
      } else if (array[mid] < key) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static int SecondHalf(int[] array, int key) {
    int length = array.length;
    int low = 0, high = length - 1;
    
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (array[mid] > key) {
        low = mid + 1;
      } else if (array[mid] < key) {
        high = mid - 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static int[] splitFirstHalf(int[] array, int pos) {
    int arrayLength = array.length - 1;
    int size = arrayLength - pos;
    int[] first = new int[size];

    for (int i = 0; i < size; i++) {
      first[i] = array[i];
    }
    return first;
  }
  
  public static int[] splitSecondHalf(int[] array, int pos) {
    int arrayLength = array.length - 1;
    int size = arrayLength - pos;
    int[] second = new int[size];

    for (int i = 0; i < size; i++) {
      second[i] = array[pos+1];
      pos++;
    }
    return second;
  }

  public static int binarySearch(int[] array, int key) {
    int length = array.length;
    int low = 0, high = length - 1;
    
    int mid = low + (high - low) / 2;
    if (key == array[mid]) {
      return mid;
    } else {
      int[] splitFirstHalf = splitFirstHalf(array, mid);
      int firstHalf = firstHalf(splitFirstHalf, key);

      if (firstHalf != -1) {
        return firstHalf;
      } else {
        int[] splitSecondHalf = splitSecondHalf(array, mid);
        int secondHalf = SecondHalf(splitSecondHalf, key);

        if (secondHalf != -1) {
          return mid + 1 + secondHalf;
        } else {
          return -1;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = {1, 4, 6, 9, 12, 19, 16, 15, 11, 8, 2};
    int key = 4;
    int ans = binarySearch(arr, key);
    System.out.println(ans);
  }
}
