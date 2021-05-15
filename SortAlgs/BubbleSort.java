package SortAlgs;

public class BubbleSort extends Sort {
    public BubbleSort(JBars bars) {
        super(bars, "Bubble Sort");
    }

    @Override
    public void run() {
        this.beforeSort();

        int[] array = this.jbars.getArray();
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - 1; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    this.jbars.increaseSwaps();
                }
                
                this.jbars.increaseComparisons();
                this.jbars.repaint();
                this.jbars.drawWait(5);
            }
        }

        this.afterSort();
    }
}
