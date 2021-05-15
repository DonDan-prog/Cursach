package SortAlgs;

public class ShakerSort extends Sort {
    public ShakerSort(JBars bars) {
        super(bars, "Shaker Sort");
    }

    @Override
    public void run() {
        this.beforeSort();

        int[] array = this.jbars.getArray();

        int buff;
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    buff = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = buff;

                    this.jbars.increaseSwaps();
                    this.jbars.repaint();
                    this.jbars.drawWait(20);
                }
                this.jbars.increaseComparisons();
            }
            right--;
            for (int i = right; i > left; i--) {
                if (array[i - 1] > array[i]) {
                    buff = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = buff;

                    this.jbars.increaseSwaps();
                    this.jbars.repaint();
                    this.jbars.drawWait(20);
                }
                this.jbars.increaseComparisons();
            }
            left++;
        }
        
        this.afterSort();
    }
}
