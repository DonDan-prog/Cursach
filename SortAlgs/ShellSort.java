package SortAlgs;

public class ShellSort extends Sort {
    public ShellSort(JBars bars) {
        super(bars, "Shell Sort");
    }

    @Override
    public void run() {
        this.jbars.shuffle();
        this.jbars.repaint();
        this.jbars.drawWait(1000);

        int[] array = this.jbars.getArray();
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int key = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > key) {
                    array[j] = array[j - gap];
                    j -= gap;

                    this.jbars.increaseSwaps();
                    this.jbars.increaseComparisons();

                    this.jbars.repaint();
                    this.jbars.drawWait(10);
                }
                array[j] = key;
                this.jbars.increaseComparisons();
                this.jbars.increaseSwaps();

                this.jbars.repaint();
                this.jbars.drawWait(10);
            }

            this.jbars.repaint();
            this.jbars.drawWait(50);
        }
        this.jbars.repaint();
        this.sortThread = null;
    }
}
