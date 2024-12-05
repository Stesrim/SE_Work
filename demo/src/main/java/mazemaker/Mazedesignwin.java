
package mazemaker;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Mazedesignwin extends JFrame {

    Mazedesignwin(Makemap parent) {
        // 將視窗設置為螢幕大小
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}