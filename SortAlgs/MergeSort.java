package SortAlgs;

public class MergeSort extends Sort {

    public MergeSort(JBars jbars) {
        super(jbars, "Merge Sort");
    }

    @Override
    public void run() {
        this.beforeSort();

        int[] array = this.jbars.getArray();
        int[] scratch = new int[array.length];
        mergeSort(array, 0, array.length - 1, scratch);

        this.afterSort();
    }

    private void mergeSort(int[] a, int lo, int hi, int[] scratch) {
        if (lo >= hi)
            return;

        int mid = (lo + hi) / 2;
        mergeSort(a, lo, mid, scratch);
        mergeSort(a, mid + 1, hi, scratch);

        // Merge sorted sublists into temporary storage
        for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
            if ((i <= mid) && ((j > hi) || (a[i] < a[j]))) {
                scratch[k] = a[i++];

                this.jbars.repaint();
                this.jbars.drawWait(5);
            } else {
                scratch[k] = a[j++];

                this.jbars.repaint();
                this.jbars.drawWait(5);
            }

            this.jbars.increaseComparisons();
            this.jbars.increaseComparisons();
            this.jbars.increaseComparisons();
        }

        // Copy back from temporary storage
        for (int k = lo; k <= hi; k++) {
            a[k] = scratch[k];

            this.jbars.increaseSwaps();

            this.jbars.repaint();
            this.jbars.drawWait(5);
        }
    }
}