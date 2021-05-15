package SortAlgs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.util.Random;

public class JBars extends JPanel {
    private final int[] array;

    private int width;
    private int comparisons;
    private int swaps;

    private String algorithmName;
    
    private final int algorithmNameOffset = 20;
    private final int comparisonsOffset = this.algorithmNameOffset + 20;
    private final int swapsOffset = this.comparisonsOffset + 20;
    private final int barsOffsetY = this.swapsOffset + 20;
    private final int barsOffsetX = 10;

    public JBars(int amount, int width) {
        this.swaps = 0;
        this.comparisons = 0;

        this.width = width;

        this.algorithmName = "";

        this.array = new int[amount];
        for (int i = 0; i < this.array.length; i++)
            this.array[i] = i + 1;

        setPreferredSize(new Dimension(amount * this.width + this.barsOffsetX, amount + this.barsOffsetY));
    }

    protected void shuffle() {
        Random random = new Random();
        for (int i = 0; i < this.array.length * 2; i++) {
            int index1 = random.nextInt(this.array.length - 1);
            int index2 = random.nextInt(this.array.length - 1);

            int temp = this.array[index1];
            this.array[index1] = this.array[index2];
            this.array[index2] = temp;

            this.repaint();
            this.drawWait(5);
        }
    }

    protected void drawWait(int milis) {
        try {
            Thread.sleep(milis);
        } catch (Exception e) {
        }
    }

    protected int[] getArray() {
        return this.array;
    }
    protected void setAlgrorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    protected void increaseComparisons() { this.comparisons++; }
    protected void increaseSwaps() { this.swaps++; }
    protected void resetComparisonsAndSwaps() { this.comparisons = 0; this.swaps = 0; }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString(this.algorithmName, this.barsOffsetX, this.algorithmNameOffset);
        g.drawString("Comparisons: " + this.comparisons, this.barsOffsetX, this.comparisonsOffset);
        g.drawString("Swaps: " + this.swaps, this.barsOffsetX, this.swapsOffset);

        for (int i = 0; i < this.array.length; i++) {
            float newColor = this.array[i] / (float) this.array.length;
            int rgb = Color.HSBtoRGB(newColor, 1f, 1f);
            g.setColor(new Color(rgb));
            g.fillRect(i * this.width + this.barsOffsetX, this.array.length - this.array[i] + this.barsOffsetY, this.width, this.array[i]);
        }
    }
}