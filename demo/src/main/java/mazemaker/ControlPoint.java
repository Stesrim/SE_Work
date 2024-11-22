package mazemaker;

import java.awt.Color;

import javax.swing.JPanel;
;

public class ControlPoint {
    public Obstables parent;
    JPanel E,W,S,N,SW,SE,NE,NW;
    ControlPoint(Obstables parent){
        SE = new JPanel();
        SE.setBackground(Color.BLACK);
        SE.setSize(9,9);

        
    }
    public void show(){
        // SE.setLocation(parent.getLocation().x+parent.getSize().width-5,parent.getLocale().y+parent.getSize().height-5);

    }
}
