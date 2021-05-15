package SortAlgs;

import java.util.Arrays;

public class RadixSort extends Sort {
    public RadixSort(JBars jbars) {
        super(jbars, "Radix Sort");
    }

    @Override
    public void run() {
        this.beforeSort();

        int[] array = this.jbars.getArray();

        int maxNumber = Arrays.stream(array).max().getAsInt();
        for (int divisor = 1; maxNumber / divisor > 0; divisor *= 10)
            this.countSort(array, divisor);

        this.afterSort();
    }

    private void countSort(int[] array, int divisor) {
        int[] out = new int[array.length];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        /** Complicated one; minified in lack of explanations */
        for (int i = 0; i < array.length; i++)
            count[(array[i] / divisor) % 10]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (int i = array.length - 1; i >= 0; i--)
            out[--count[(array[i] / divisor) % 10]] = array[i];

        for (int i = 0; i < array.length; i++) {
            array[i] = out[i];

            this.jbars.increaseSwaps();
            this.jbars.repaint();
            this.jbars.drawWait(10);
        }
    }
}
