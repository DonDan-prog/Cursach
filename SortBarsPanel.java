import SortAlgs.*;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;

public final class SortBarsPanel extends JPanel {
    private final int BAR_WIDTH = 7;
    private final JBars sortBars;
    private final Sort[] sortAlgorithms;

    private Box createSortButtons()
    {
        Box sortButtons = Box.createHorizontalBox();
        sortButtons.add(Box.createHorizontalStrut(5));

        for(Sort sortButton: this.sortAlgorithms)
        {
            sortButtons.add(sortButton);
            sortButtons.add(Box.createHorizontalStrut(5));
        }
        return sortButtons;
    }

    @Override
    public void setEnabled(boolean isEnabled)
    {
        super.setEnabled(isEnabled);
        
        for(Sort sortButton: this.sortAlgorithms)
            sortButton.setEnabled(isEnabled);
    }

    public SortBarsPanel(int amount) {
        this.sortBars = new JBars(amount, BAR_WIDTH);
        this.sortAlgorithms = new Sort[] {
            new BubbleSort(this.sortBars),
            new InsertionSort(this.sortBars),
            new ShellSort(this.sortBars),
            new MergeSort(this.sortBars),
            new ShakerSort(this.sortBars),
            new SmoothSort(this.sortBars),
            new BinarySort(this.sortBars),
            new QuickSort(this.sortBars),
            new RadixSort(this.sortBars)
        };

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(createSortButtons());
        this.add(this.sortBars);
    }
}