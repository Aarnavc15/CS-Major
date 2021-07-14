public class ThreeSum {

    public static int binarySearch(int[] a, int num) {
        return binarySearch(a, num, 0, a.length);
    }

    public static int binarySearch(int[] a, int num, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (lo >= hi) return -1;
        else if (a[mid] < num) {
            return binarySearch(a, num, mid + 1, hi);
        }
        else if (a[mid] > num) {
            return binarySearch(a, num, lo, mid);
        }
        else return mid;
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

    public static void threeSum(int[] a) {
        sort(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = binarySearch(a, -(a[i] + a[j]));
                if (k >= 0 && k > j) {
                    StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                }
            }
        }
    }

    public static int count(int[] a) {
        int n = a.length;
        sort(a);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = binarySearch(a, -(a[i] + a[j]));
                if (k >= 0 && k > j) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int[] generator(int size, int bounds) {
        int[] arr = new int[size];
        int boundL = -1 * bounds;
        int boundU = bounds + 1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(boundL, boundU);
        }
        return arr;
    }


    public static void main(String[] args) {
        int n = 40000;
        int bound = 10;
        int[] c = generator(n, bound);
        int[] b = generator(2 * n, bound);
        long temp = System.nanoTime();
        int countc = count(c);
        double tc = (System.nanoTime() - temp) / (Math.pow(10, 9));
        temp = System.nanoTime();
        int countb = count(b);
        double tb = (System.nanoTime() - temp) / (Math.pow(10, 9));

        StdOut.println("Test\tFound\tTime");
        StdOut.println(n + "\t" + countc + "\t" + tc);
        StdOut.println(2 * n + "\t" + countb + "\t" + tb);
        System.out.println("Lim n--> inf (T(2n)/T(n)): " + (tb / tc));
    }
}
