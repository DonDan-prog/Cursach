package SortAlgs;

public class BinarySort extends Sort {
    public BinarySort(JBars jbars) {
        super(jbars, "Binary Sort");
    }

    @Override
    public void run() {
        this.beforeSort();

        int[] array = this.jbars.getArray();
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int insertedPosition = this.binarySearch(array, 0, i - 1, key);

            for (int j = i - 1; j >= insertedPosition; --j)
                array[j + 1] = array[j];

            array[insertedPosition] = key;

            this.jbars.increaseSwaps();
            this.jbars.repaint();
            this.jbars.drawWait(50);
        }
        
        this.afterSort();
    }

    private int binarySearch(int[] array, int start, int end, int key) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < array[mid])
                end = mid - 1;
            else
                start = mid + 1;
            this.jbars.increaseComparisons();
        }
        return start;
    }
}
