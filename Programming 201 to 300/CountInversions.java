public class CountInversions {
    static long mergeAndCount(int[] arr, int[] temp, int l, int m, int r) {
        int i = l, j = m, k = l;
        long count = 0;
        while (i <= m - 1 && j <= r) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else {
                temp[k++] = arr[j++];
                count += (m - i);
            }
        }
        while (i <= m - 1)
            temp[k++] = arr[i++];
        while (j <= r)
            temp[k++] = arr[j++];
        for (i = l; i <= r; i++)
            arr[i] = temp[i];
        return count;
    }

    static long mergeSort(int[] arr, int[] temp, int l, int r) {
        long count = 0;
        if (l < r) {
            int m = l + (r - l) / 2;
            count += mergeSort(arr, temp, l, m);
            count += mergeSort(arr, temp, m + 1, r);
            count += mergeAndCount(arr, temp, l, m + 1, r);
        }
        return count;
    }

    public static long countInversions(int[] arr) {
        return mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = { 8, 4, 2, 1 };
        System.out.println(countInversions(arr)); // 6
    }
}