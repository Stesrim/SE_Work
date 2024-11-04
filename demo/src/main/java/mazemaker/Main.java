package mazemaker;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
public class Main {
    public static void main(String[] args) {
        Portal holl1 = new Portal();
        holl1.setId(20);
        holl1.setPortalId(10);
        holl1.setBounds(300, 200, 50, 50);
        holl1.setBackground(Color.yellow);
        holl1.setOpaque(true);
        holl1.setType(1);
        
        Portal holl2 = new Portal();
        holl2.setId(10);
        holl2.setPortalId(20);
        holl2.setBounds(400, 200, 50, 50);
        holl2.setBackground(Color.pink);
        holl2.setOpaque(true);
        holl2.setType(1);

        Obstable label = new Obstable();
        label.setBounds(0, 0, 20, 20);
        label.setOpaque(false);
        label.setPassable(true);
        label.setType(0);
        ImageIcon a = new ImageIcon(Main.class.getResource("/images/playerandendspot/player.png"));
        a.setImage(a.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        label.setIcon(a);
        Obstable label2 = new Obstable();
        label2.setBounds(105, 200, 50, 50);
        label2.setBackground(Color.blue);
        label2.setOpaque(true);
        label2.setPassable(true);
        label2.setType(1);

        Obstable label3 = new Obstable();
        label3.setBounds(100, 100, 50, 50);
        label3.setBackground(Color.green);
        label3.setOpaque(true);
        label3.setPassable(false);
        label3.setType(1);
        Obstable label4 = new Obstable();
        label4.setBounds(300, 300, 75, 75);
        label4.setBackground(Color.black);
        label4.setOpaque(true);
        label4.setPassable(true);
        label4.setType(2);
        GameMap window = new GameMap();
        window.setBG(2);
        window.setTimeleft(10);
        window.addObstable(label, label.getType());
        window.addObstable(label2, label2.getType());
        window.addObstable(label3, label3.getType());
        window.addObstable(label4, label4.getType());
        window.addPortal(holl1);
        window.addPortal(holl2);
        
    }
}
