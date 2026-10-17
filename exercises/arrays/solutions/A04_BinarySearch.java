package arrays.solutions;

/**
 * 【参考答案】Q04_BinarySearch：二分查找
 *
 * 解题思路：
 * 每次与中间元素比较，丢弃一半区间；时间复杂度 O(log n)。
 *
 * 运行：java -cp out arrays.solutions.A04_BinarySearch
 */
public class A04_BinarySearch {
    static int search(int[] a, int target) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) return mid;
            if (a[mid] < target) low = mid + 1; else high = mid - 1;
        }
        return -1;
    }

    static int searchRec(int[] a, int target, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (a[mid] == target) return mid;
        return a[mid] < target ? searchRec(a, target, mid + 1, high) : searchRec(a, target, low, mid - 1);
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        System.out.println(search(a, 7) + " " + search(a, 4));                    // 3 -1
        System.out.println(searchRec(a, 9, 0, a.length - 1));                     // 4
    }
}
