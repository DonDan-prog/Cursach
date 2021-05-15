import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Box;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualSort extends JFrame {
    public static void main(String[] args) {
        new VisualSort();
    }

    private class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public VisualSort() {
        super("Sort Visualisation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Box newContentPane = Box.createVerticalBox();

        newContentPane.add(Box.createVerticalStrut(10));
        newContentPane.add(new SortBarsPanel(130));
        newContentPane.add(Box.createVerticalStrut(10));
        newContentPane.add(addExitButton());
        newContentPane.add(Box.createVerticalStrut(10));

        setContentPane(newContentPane);
        pack();
        setVisible(true);
    }

    private JButton addExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new CloseListener());
        return exitButton;
    }
}