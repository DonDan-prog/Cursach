package SortAlgs;

/** I couldn't implement smooth sort by myself due to it's difficulty */
/** So I copied code from https://github.com/zoneXcoding/Mineworld/blob/49060accd9c1b96f5602d51ccc5e9da13df76ef5/src/main/java/org/terasology/utilities/Sorting.java */
/** I have modified code to fit in my needs and API, but main part is kepp unmodified. Thanks to this coder */
public class SmoothSort extends Sort {

    public SmoothSort(JBars jbars) {
        super(jbars, "Smooth Sort");
    }

    @Override
    public void run() {
        this.beforeSort();
        int[] array = this.jbars.getArray();
        smoothSort(array, 0, array.length - 1);
        this.afterSort();
    }

    static final int smoothSortLP[] = { 1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177 };

    private void smoothSort(int[] m, int lo, int hi) {
        int head = lo;
        int p = 1;
        int pshift = 1;

        while (head < hi) {
            if ((p & 3) == 3) {
                smoothSortSift(m, pshift, head);
                p >>>= 2;
                pshift += 2;
            } else {
                if (smoothSortLP[pshift - 1] >= hi - head) {
                    smoothSortTrinkle(m, p, pshift, head, false);
                } else {
                    smoothSortSift(m, pshift, head);
                }

                this.jbars.increaseComparisons();

                if (pshift == 1) {
                    p <<= 1;
                    pshift--;
                } else {
                    p <<= (pshift - 1);
                    pshift = 1;
                }
            }
            p |= 1;
            head++;

            this.jbars.repaint();
            this.jbars.drawWait(10);
        }

        smoothSortTrinkle(m, p, pshift, head, false);
        this.jbars.repaint();
        this.jbars.drawWait(10);

        while (pshift != 1 || p != 1) {
            if (pshift <= 1) {
                int trail = Integer.numberOfTrailingZeros(p & ~1);
                p >>>= trail;
                pshift += trail;
            } else {
                p <<= 2;
                p ^= 7;
                pshift -= 2;

                smoothSortTrinkle(m, p >>> 1, pshift + 1, head - smoothSortLP[pshift] - 1, true);
                this.jbars.repaint();
                this.jbars.drawWait(10);

                smoothSortTrinkle(m, p, pshift, head - 1, true);
                this.jbars.repaint();
                this.jbars.drawWait(10);
            }

            head--;
        }
    }

    private void smoothSortSift(int[] m, int pshift, int head) {
        int val = m[head];

        while (pshift > 1) {
            int rt = head - 1;
            int lf = head - 1 - smoothSortLP[pshift - 2];

            this.jbars.increaseComparisons();
            this.jbars.increaseComparisons();

            if (Integer.compare(val, m[lf]) >= 0 && Integer.compare(val, m[rt]) >= 0)
                break;
            if (Integer.compare(m[lf], m[rt]) >= 0) {
                m[head] = m[lf];
                head = lf;
                pshift -= 1;
            } else {
                m[head] = m[rt];
                head = rt;
                pshift -= 2;
            }

            this.jbars.increaseComparisons();
            this.jbars.increaseSwaps();
        }

        m[head] = val;
        this.jbars.increaseSwaps();
    }

    private void smoothSortTrinkle(int[] m, int p, int pshift, int head, boolean isTrusty) {
        int val = m[head];

        while (p != 1) {
            int stepson = head - smoothSortLP[pshift];

            this.jbars.increaseComparisons();
            if (Integer.compare(m[stepson], val) <= 0)
                break;
            
            if (!isTrusty && pshift > 1) {
                int rt = head - 1;
                int lf = head - 1 - smoothSortLP[pshift - 2];

                this.jbars.increaseComparisons();
                this.jbars.increaseComparisons();
                if (Integer.compare(m[rt], m[stepson]) >= 0 || Integer.compare(m[lf], m[stepson]) >= 0)
                    break;
            }

            m[head] = m[stepson];
            this.jbars.increaseSwaps();

            head = stepson;
            int trail = Integer.numberOfTrailingZeros(p & ~1);
            p >>>= trail;
            pshift += trail;
            isTrusty = false;
        }

        if (!isTrusty) {
            m[head] = val;
            this.jbars.increaseSwaps();
            smoothSortSift(m, pshift, head);
        }
    }
}
