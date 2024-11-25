
package mazemaker;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class mazedesignwin extends JFrame {

    

    mazedesignwin(makemap parent) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }
}