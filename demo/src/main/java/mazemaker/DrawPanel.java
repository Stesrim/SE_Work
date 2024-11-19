package mazemaker;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    public ArrayList< ArrayList <Portal>> portals;
    public ArrayList< ArrayList <Obstable>> obstables;
    public DrawPanel() {
        this.portals = new ArrayList<>();
        this.obstables = new ArrayList<>();
        setLayout(new BorderLayout());
    }
}
