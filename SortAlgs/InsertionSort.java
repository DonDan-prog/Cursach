package SortAlgs;

public class InsertionSort extends Sort {
    public InsertionSort(JBars bars) {
        super(bars, "Insertion Sort");
    }

    @Override
    public void run() {
        this.jbars.shuffle();
        this.jbars.repaint();
        this.jbars.drawWait(1000);

        int[] array = this.jbars.getArray();
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    this.jbars.increaseSwaps();
                    this.jbars.drawWait(50);
                    this.jbars.repaint();
                } else
                    break;
                
                this.jbars.increaseComparisons();
            }
        }
        this.jbars.repaint();
        this.sortThread = null;
    }
}
