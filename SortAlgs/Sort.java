package SortAlgs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class Sort implements Runnable, ActionListener {
    protected JBars jbars;
    protected Thread sortThread;
    protected String name;

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
