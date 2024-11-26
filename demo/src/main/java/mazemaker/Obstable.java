package mazemaker;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Obstable extends JLabel{
    
    private int type;
    private boolean passable;

    public Obstable(){
        super();
        type = 0;
        passable = false;
        this.setOpaque(true);
    }
    public Obstable(ImageIcon name, int type, boolean passable) {
        this.type = type;
        this.passable = passable;

        this.setIcon(name);
        this.setOpaque(true);
    }
    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
