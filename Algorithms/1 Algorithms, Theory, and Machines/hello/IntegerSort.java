public class IntegerSort {


    public IntegerSort() {

    }

    public static void merge(int[] a, int[] aux, int lo, int mid, int hi) {

        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) aux[k] = a[j++];
            else if (j == hi) aux[k] = a[i++];
            else if (a[j] < (a[i])) aux[k] = a[j++];
            else aux[k] = a[i++];
        }

        // copy back
        for (int k = lo; k < hi; k++)
            a[k] = aux[k];
    }

    public static int[] integerSort(int[] a) {

        sort(a);
        int[] aux = new int[a.length];
        for (int i = 0; i < a.length; i++) {
        }
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0 && a[i] <= 99) {
                aux[count] = a[i];
                count++;
            }
        }

        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = aux[i];
        }
        return arr;
    }

    public static void sort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        sort(a, aux, 0, n);
    }

    public static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi - lo <= 1) return;

        // sort each half, recursively
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid, hi);

        // merge back together
        merge(a, aux, lo, mid, hi);
    }


    public static void main(String[] args) {
        int[] test = { 98, 2, 3, 1, 0, 0, 0, 3, 98, 98, 2, 2, 2, 0, 0, 0 };
        int[] result = integerSort(test);
        for (int i : result) System.out.print(i + " ");
    }

    public static class Parentheses {
        public static void main(String[] args) {

        }
    }
}
