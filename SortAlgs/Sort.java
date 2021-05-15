package SortAlgs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class Sort implements Runnable, ActionListener {
    protected JBars jbars;
    protected Thread sortThread;
    protected String name;

    protected void beforeSort() {
        this.jbars.shuffle();
        this.jbars.repaint();
        this.jbars.drawWait(1000);
    }
    
    protected void afterSort() {
        this.jbars.repaint();
        this.sortThread = null;
    }

    public Sort(JBars jbars, String name) {
        this.jbars = jbars;
        this.sortThread = null;
        this.name = name;
    }

    public String getName() { return this.name; }

    abstract public void run();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.sortThread == null) {
            this.jbars.setAlgrorithmName(this.name);
            this.jbars.resetComparisonsAndSwaps();
            this.sortThread = new Thread(this);
            this.sortThread.start();
        }
    }
}
