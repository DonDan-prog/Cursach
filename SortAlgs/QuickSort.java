package SortAlgs;

public class QuickSort extends Sort {
    public QuickSort(JBars jbars) {
        super(jbars, "Quick Sort");
    }

    @Override
    public void run()
    {
        this.beforeSort();

        int[] array = this.jbars.getArray();
        int left = 0;
        int right = array.length - 1;

        this.quickSort(array, left, right);

        this.afterSort();
    }

    private void quickSort(int[] array, int left, int right)
    {
        if(array.length == 0)
            return;
        
        if(left >= right)
            return;
        
        int mid = left + (right - left) / 2;
        int key = array[mid];

        int i = left;
        int j = right;
        while(i <= j)
        {
            while(array[i] < key)
            {
                i++;

                this.jbars.increaseComparisons();
                this.jbars.repaint();
                this.jbars.drawWait(5);
            }
            while(array[j] > key)
            {
                j--;

                this.jbars.increaseComparisons();
                this.jbars.repaint();
                this.jbars.drawWait(5);
            }

            if(i <= j)
            {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
                this.jbars.increaseSwaps();
            }

            this.jbars.repaint();
            this.jbars.drawWait(5);
        }

        if(left < j)
            this.quickSort(array, left, j);
        if(right > i)
            this.quickSort(array, i, right);
    }
}
